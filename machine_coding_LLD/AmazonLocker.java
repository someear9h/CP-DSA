package machine_coding_LLD;

import java.security.SecureRandom;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

/**
 * =====================================================================================
 * AMAZON LOCKER - LOW LEVEL DESIGN
 * =====================================================================================
 *
 * REQUIREMENTS RECAP (from the clarifying-questions round)
 *  1. Driver deposits a package by SIZE (SMALL / MEDIUM / LARGE). Exact size match only.
 *     No compartment of that size free -> error.
 *  2. Deposit returns ONE access token per package.
 *  3. Customer picks up with the token. Wrong / already used / expired -> specific error.
 *  4. Tokens expire after 7 days. Package stays in the compartment until staff removes it.
 *  5. Staff can open all compartments whose tokens expired.
 *
 * Out of scope: delivery logistics, notifications, lockout after failed attempts,
 * UI, multiple locker stations, payments.
 *
 * -------------------------------------------------------------------------------------
 * HOW I DECIDED THE CLASSES  (say this out loud in the interview: "nouns -> classes,
 * verbs -> methods, and I split by *reason to change*")
 * -------------------------------------------------------------------------------------
 *  Size (enum)        : fixed small set of values. An enum gives type safety and lets me
 *                       use EnumMap (array-backed, fastest map for enum keys).
 *
 *  Compartment        : the physical box. Immutable (id + size). I deliberately did NOT
 *                       store an "occupied" flag inside it: free-ness is already
 *                       represented by "is it in the free queue?". Two sources of truth
 *                       for the same fact = bugs under pressure.
 *
 *  AccessToken        : the code + which compartment it opens + when it expires + whether
 *                       it was already used. This is the link between a customer and a
 *                       compartment. Expiry is DERIVED from (expiresAt vs now), not
 *                       stored as a status, so no background job is needed to flip states.
 *
 *  Locker             : owns the physical inventory (compartments) and answers only
 *                       "give me a free box of size X" / "this box is free again".
 *                       Single Responsibility: hardware inventory.
 *
 *  LockerService      : orchestrates the business flow (deposit, pickup, staff clearing),
 *                       owns tokens. Single Responsibility: business rules.
 *
 *  TokenGenerator     : interface. Code generation is a *policy* that may change (6-digit
 *                       random, UUID, QR payload) and I want to inject a deterministic one
 *                       in tests. Open/Closed + Dependency Inversion.
 *
 *  Clock (java.time)  : injected so I can test "7 days later" without sleeping.
 *                       Never call Instant.now() directly inside business logic.
 *
 *  Exceptions         : one base LockerException + one subclass per failure so callers
 *                       (and the interviewer) get the SPECIFIC error the requirements ask for.
 *
 * -------------------------------------------------------------------------------------
 * SOLID MAP
 *  S : Locker (inventory) vs LockerService (rules) vs TokenGenerator (code policy).
 *  O : new token style = new TokenGenerator class, no edits to LockerService.
 *  L : any TokenGenerator / Clock can replace another without breaking the service.
 *  I : TokenGenerator has exactly one method - nobody depends on what they don't use.
 *  D : LockerService depends on the TokenGenerator interface and Clock abstraction,
 *      not on SecureRandom or the system time.
 * =====================================================================================
 */

/** ------------------- Enum & Models ------------------------------- */
enum Size {
    SMALL, MEDIUM, LARGE
}

/**
 * Immutable: a physical box never changes its id or size, so there is nothing to mutate
 * and nothing to synchronize.
 */
class Compartment {
    private final String id;
    private final Size size;

    public Compartment(String id, Size size) {
        this.id = id;
        this.size = size;
    }

    public String getId() { return id; }
    public Size getSize() { return size; }

    /** Simulates the hardware unlocking the door. Real system would call a device driver. */
    public void open() {
        System.out.println("  [HARDWARE] Compartment " + id + " (" + size + ") opened");
    }
}

/**
 * Links a customer's code to exactly one compartment (1 token : 1 package : 1 compartment).
 *
 * Fields are final except 'redeemed' - the only thing that changes in a token's life.
 * 'expired' is NOT a field: it is computed from the clock, so it can never be stale.
 */
class AccessToken {
    private final String code;
    private final String compartmentId; // store the ID, not the object: loose coupling, cheap
    private final Instant expiresAt;
    private boolean redeemed;

    public AccessToken(String code, String compartmentId, Instant expiresAt) {
        this.code = code;
        this.compartmentId = compartmentId;
        this.expiresAt = expiresAt;
        this.redeemed = false;
    }

    public String getCode() { return code; }
    public String getCompartmentId() { return compartmentId; }
    public Instant getExpiresAt() { return expiresAt; }
    public boolean isRedeemed() { return redeemed; }

    public void markRedeemed() { this.redeemed = true; }

    /** Token is expired at the exact expiry instant and after (not-before semantics). */
    public boolean isExpired(Instant now) {
        return !now.isBefore(expiresAt);
    }
}

/** ------------------- Exceptions ------------------------------- */
/**
 * Unchecked (RuntimeException) because these are business-rule failures the caller
 * decides how to present; forcing try/catch on every call adds noise in a 60-min interview.
 */
class LockerException extends RuntimeException {
    public LockerException(String message) { super(message); }
}

class NoCompartmentAvailableException extends LockerException {
    public NoCompartmentAvailableException(Size size) {
        super("No free " + size + " compartment available");
    }
}

class InvalidTokenException extends LockerException {
    public InvalidTokenException() { super("Invalid access token"); }
}

class TokenAlreadyUsedException extends LockerException {
    public TokenAlreadyUsedException() { super("Access token has already been used"); }
}

class TokenExpiredException extends LockerException {
    public TokenExpiredException() { super("Access token has expired"); }
}

/** ------------------- Strategy: token generation ------------------------------- */
/**
 * Why an interface? Code format is a policy that changes independently of locker logic.
 * Also lets tests inject predictable codes. One method = Interface Segregation.
 */
interface TokenGenerator {
    String generate();
}

/** 6-digit numeric code: easy for a human to type on a locker keypad. */
class RandomTokenGenerator implements TokenGenerator {
    // SecureRandom, not Random: access codes guard physical goods, so they must be unpredictable.
    private final SecureRandom random = new SecureRandom();

    @Override
    public String generate() {
        return String.format("%06d", random.nextInt(1_000_000));
    }
}

/** ------------------- Locker: physical inventory ------------------------------- */
/**
 * Knows ONLY about compartments: which are free, and how to hand one out / take one back.
 * It knows nothing about tokens, expiry, or customers.
 *
 * DATA STRUCTURE CHOICES
 *
 *  Map<Size, Deque<Compartment>> freeBySize
 *    KEY   = Size      -> the only thing a deposit request specifies (exact-match rule),
 *                         so size is the natural lookup key.
 *    VALUE = Deque     -> a pool of free boxes of that size. poll() = O(1) allocate,
 *                         offer() = O(1) release. No scanning through all compartments.
 *    MAP   = EnumMap   -> array-backed and ordered by enum; faster/lighter than HashMap
 *                         when keys are an enum.
 *    Being "in the deque" == being free. That is the single source of truth.
 *
 *  Map<String, Compartment> compartmentById
 *    KEY   = compartment id -> a token stores only a compartmentId; pickup needs to go
 *                              from that id back to the box in O(1).
 *    VALUE = Compartment
 */
class Locker {
    private final Map<String, Compartment> compartmentById = new HashMap<>();
    private final Map<Size, Deque<Compartment>> freeBySize = new EnumMap<>(Size.class);

    public Locker(List<Compartment> compartments) {
        // Pre-create a pool for every size so allocate() never hits a null deque.
        for (Size size : Size.values()) {
            freeBySize.put(size, new ArrayDeque<>());
        }
        for (Compartment c : compartments) {
            compartmentById.put(c.getId(), c);
            freeBySize.get(c.getSize()).offer(c); // everything starts free
        }
    }

    /** Removes and returns a free compartment of EXACTLY this size, or throws. */
    public Compartment allocate(Size size) {
        Compartment c = freeBySize.get(size).poll();
        if (c == null) {
            throw new NoCompartmentAvailableException(size);
        }
        return c;
    }

    /** Puts a compartment back in the free pool. Caller must guarantee it is not already free. */
    public void release(Compartment c) {
        freeBySize.get(c.getSize()).offer(c);
    }

    public Compartment getCompartment(String compartmentId) {
        return compartmentById.get(compartmentId);
    }
}

/** ------------------- Service: business rules + token store ------------------------------- */
/**
 * DATA STRUCTURE CHOICE
 *
 *  Map<String, AccessToken> tokenDb
 *    KEY   = token code -> pickup is THE hot path and the customer only gives us the code,
 *                          so lookup by code must be O(1).
 *    VALUE = AccessToken -> carries compartmentId, expiry and redeemed flag, so one lookup
 *                           gives us everything needed to validate.
 *    We keep redeemed tokens in the map (instead of deleting) so we can answer
 *    "already used" instead of the less helpful "invalid" - requirement #6.
 *
 * CONCURRENCY
 *  Two drivers could deposit at the same time and both poll() the same last compartment,
 *  or two people could redeem one code. The methods are 'synchronized' - simplest
 *  correct answer for an interview. Mention ConcurrentHashMap / per-size locks as the
 *  scale-up path if asked.
 */
class LockerService {
    // 7 days is a business rule; kept as a constant so it is visible and has one home.
    public static final Duration DEFAULT_TTL = Duration.ofDays(7);

    private final Locker locker;
    private final TokenGenerator tokenGenerator;
    private final Clock clock;
    private final Duration tokenTtl;
    private final Map<String, AccessToken> tokenDb = new HashMap<>();

    // Constructor injection: all collaborators are passed in (Dependency Inversion).
    public LockerService(Locker locker, TokenGenerator tokenGenerator, Clock clock, Duration tokenTtl) {
        this.locker = locker;
        this.tokenGenerator = tokenGenerator;
        this.clock = clock;
        this.tokenTtl = tokenTtl;
    }

    /**
     * Requirement 1 & 2: allocate a compartment, open it, issue a token.
     *
     * Order matters: allocate FIRST. If there is no space it throws before we have
     * generated or stored anything, so there is no cleanup/rollback to write.
     */
    public synchronized AccessToken deposit(Size size) {
        Compartment compartment = locker.allocate(size); // throws if full

        String code = generateUniqueCode();
        AccessToken token = new AccessToken(code, compartment.getId(), clock.instant().plus(tokenTtl));
        tokenDb.put(code, token);

        compartment.open(); // driver puts the package in
        System.out.println("Deposited " + size + " package in " + compartment.getId());
        return token;
    }

    /**
     * Requirement 3, 4 & 6: validate in the order that gives the most useful error,
     * then open the compartment and free it.
     */
    public synchronized void pickup(String code) {
        AccessToken token = tokenDb.get(code);

        if (token == null)                          throw new InvalidTokenException();
        if (token.isRedeemed())                     throw new TokenAlreadyUsedException();
        if (token.isExpired(clock.instant()))       throw new TokenExpiredException();

        Compartment compartment = locker.getCompartment(token.getCompartmentId());
        compartment.open();                         // customer takes the package
        token.markRedeemed();                       // one token = one pickup
        locker.release(compartment);                // box is reusable immediately
        System.out.println("Package picked up from " + compartment.getId());
    }

    /**
     * Requirement 5: staff opens every compartment whose token expired unredeemed.
     *
     * ASSUMPTION (state it in the interview): staff physically empties each box right
     * after it opens, so we free the compartment and drop the dead token here.
     * Dropping the token keeps tokenDb from growing forever with expired entries.
     *
     * Complexity: O(n) over tokens. Fine at locker scale (tens of compartments).
     * Scale-up: a PriorityQueue/TreeMap ordered by expiresAt makes this O(k) for k expired.
     *
     * @return ids of the compartments that were opened, so staff know which ones to clear
     */
    public synchronized List<String> openExpiredCompartments() {
        Instant now = clock.instant();
        List<String> opened = new ArrayList<>();

        // Iterator is required to remove from the map while looping (avoids ConcurrentModificationException).
        Iterator<AccessToken> it = tokenDb.values().iterator();
        while (it.hasNext()) {
            AccessToken token = it.next();
            // Only expired + unredeemed tokens still have a package sitting inside.
            if (!token.isRedeemed() && token.isExpired(now)) {
                Compartment compartment = locker.getCompartment(token.getCompartmentId());
                compartment.open();
                locker.release(compartment);
                opened.add(compartment.getId());
                it.remove();
            }
        }
        return opened;
    }

    /** Retry on the (rare) collision so "one token per package" is guaranteed unique. */
    private String generateUniqueCode() {
        String code;
        do {
            code = tokenGenerator.generate();
        } while (tokenDb.containsKey(code));
        return code;
    }
}

/** ------------------- Test helper: controllable Clock ------------------------------- */
/**
 * Lets the driver code "travel" 8 days forward instantly. This is why Clock is injected.
 */
class MutableClock extends Clock {
    private Instant now;

    public MutableClock(Instant start) { this.now = start; }

    public void advance(Duration d) { now = now.plus(d); }

    @Override public ZoneId getZone() { return ZoneId.of("UTC"); }
    @Override public Clock withZone(ZoneId zone) { return this; }
    @Override public Instant instant() { return now; }
}

/** ------------------- Driver Code ------------------------------- */
public class AmazonLocker {
    public static void main(String[] args) {
        MutableClock clock = new MutableClock(Instant.parse("2026-10-25T10:00:00Z"));

        // Station layout: 2 small, 1 medium, 1 large
        Locker locker = new Locker(Arrays.asList(
            new Compartment("S1", Size.SMALL),
            new Compartment("S2", Size.SMALL),
            new Compartment("M1", Size.MEDIUM),
            new Compartment("L1", Size.LARGE)
        ));

        LockerService service = new LockerService(
            locker, new RandomTokenGenerator(), clock, LockerService.DEFAULT_TTL);

        System.out.println("--- 1. Happy path: deposit + pickup ---");
        AccessToken t1 = service.deposit(Size.SMALL);
        service.pickup(t1.getCode());

        System.out.println("\n--- 2. Already used token ---");
        expectError(() -> service.pickup(t1.getCode()));

        System.out.println("\n--- 3. Invalid token ---");
        expectError(() -> service.pickup("000000"));

        System.out.println("\n--- 4. No space of requested size ---");
        service.deposit(Size.LARGE);                       // takes the only large box
        expectError(() -> service.deposit(Size.LARGE));    // none left -> error

        System.out.println("\n--- 5. Expired token rejected ---");
        AccessToken t2 = service.deposit(Size.MEDIUM);
        clock.advance(Duration.ofDays(8));
        expectError(() -> service.pickup(t2.getCode()));

        System.out.println("\n--- 6. Staff opens expired compartments ---");
        List<String> opened = service.openExpiredCompartments();
        System.out.println("Staff cleared: " + opened);

        System.out.println("\n--- 7. Compartment reusable after staff clears it ---");
        service.deposit(Size.MEDIUM);
    }

    private static void expectError(Runnable action) {
        try {
            action.run();
            System.out.println("  !! expected an error but none was thrown");
        } catch (LockerException e) {
            System.out.println("  Expected error -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}

/**
 * =====================================================================================
 * EXTENSIBILITY - what to say when the interviewer asks "what if...?"
 * =====================================================================================
 *  - Fallback to a bigger compartment: extract an AllocationStrategy interface
 *    (ExactSizeStrategy, NextLargestStrategy). Locker.allocate() delegates to it. (OCP)
 *  - Lockout after N wrong attempts: add an AttemptTracker (Map<lockerSessionId, count + windowStart>)
 *    injected into LockerService; check it at the top of pickup().
 *  - Notifications: publish a PackageDeposited event; a NotificationService subscribes (Observer).
 *  - Multiple stations: LockerService gets a Map<stationId, Locker>; tokens store stationId.
 *  - Persistence: put tokenDb behind a TokenRepository interface; swap HashMap for a DB.
 *  - Many expired tokens: keep a TreeMap<Instant, AccessToken> to find expired ones in O(k).
 * =====================================================================================
 */