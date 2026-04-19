## Pattern: Polynomial Rolling Hash + Prefix Sums

Every problem in this pattern follows this exact flow:

found it on: LC 3756 Concatenate Non-Zero Digits and Multiply by Sum II,
             LC 1316 Distinct Echo Substrings, LC 1044 Longest Duplicate Substring,
             LC 187 Repeated DNA Sequences, LC 28 Find First Occurrence (Rabin-Karp)

```
STEP 1: Recognize that a substring is being treated as a NUMBER.
        
        "123" is not three characters. It is one hundred twenty-three.
        The positional value of a digit depends on its distance from
        the RIGHT end of the substring.
        
        e.g. s = "1234"
             value = 1×10³ + 2×10² + 3×10¹ + 4×10⁰
        
        Ask: "is the problem treating a substring as an integer,
              a hash, or any value where POSITION of a character matters?"

STEP 2: Precompute the POWER array.
        
        pow[i] = BASE^i % MOD
        
        Fill in ONE forward pass. You will need pow[len] during every
        stitch query, so precompute up to pow[n] at the start.
        
        BASE = 10  for decimal digit problems (value IS the integer)
        BASE = 31 or 131  for character-based hash problems
        MOD  = 1_000_000_007  (standard prime)
               use TWO mods to reduce collisions in contests
        
        Ask: "what is my base? (what does one positional step multiply by?)"

STEP 3: Build the PREFIX HASH array.
        
        prefix[0] = 0   (empty prefix, identity element)
        prefix[i] = (prefix[i-1] * BASE + value(s[i])) % MOD
        
        This encodes the entire left portion of the string as a single
        number, where earlier characters carry higher positional weight.
        
        Ask: "what single number represents everything from index 0 to i?"

STEP 4: Answer SUBSTRING QUERIES in O(1) with THE MASTER FORMULA.
        
        hash(l, r) = (prefix[r] - prefix[l-1] * pow[r - l + 1] % MOD + MOD) % MOD
        
        WHY THIS WORKS:
            prefix[r]              = s[0..r] encoded as a positional number
            prefix[l-1]            = s[0..l-1] (the "left garbage")
            prefix[l-1] * pow[len] = shift the garbage left by len digits
                                     so it perfectly overlaps with prefix[r]
            subtracting them       = cancels the left part, leaving s[l..r]
        
        The +MOD before final % prevents negative results in Java/C++.
        Python's % is always non-negative, so +MOD is optional there.

STEP 5: Use the hash as an O(1) equality check or exact value.
        
        Equality (string matching):
            hash(l1,r1) == hash(l2,r2)  →  s[l1..r1] == s[l2..r2]  (high probability)
        
        Exact value (digit problems):
            hash(l,r) with BASE=10  →  IS the integer value of the digit substring
        
        Ask: "am I checking equality (hash proxy) or computing a numeric value
              (hash is the exact answer)?"
```

**That's it. Every "substring as number / hash + range queries" problem. No exceptions.**

---

## The Master Key Formula: Visualized

```
                ┌─────────────────────────────────────────────────────────┐
                │                                                         │
                │  hash(l, r) =                                           │
                │    (prefix[r]                                           │
                │     - prefix[l-1] * pow[r - l + 1] % MOD               │
                │     + MOD) % MOD                                        │
                │                                                         │
                └─────────────────────────────────────────────────────────┘

VISUAL PROOF:

prefix[r]   encodes:  [ s0  s1 ... s(l-1) |  sl  s(l+1) ... sr ]
                        ← left garbage  →   ←   what we want   →

prefix[l-1] encodes:  [ s0  s1 ... s(l-1) ]
                        ← left garbage  →

prefix[l-1] * pow[len] shifts it left by len positions:
                      [ s0  s1 ... s(l-1) |  00    00  ...  00 ]
                        ← left garbage  →   ←  zeroed out    →

subtraction cancels the left garbage:
                      [ 00  00 ...   00   |  sl  s(l+1) ... sr ]
                                            ←  what we want   →

The +MOD before final % is a guard against negative values in Java/C++.
```

---

## The Two Flavors: Numeric Value vs String Equality

```
╔═══════════════════╦══════════════════════════════╦════════════════════════════════════╗
║                   ║ NUMERIC VALUE                ║ STRING EQUALITY (Hash Proxy)       ║
╠═══════════════════╬══════════════════════════════╬════════════════════════════════════╣
║ BASE              ║ 10 (decimal digits)          ║ 31, 131, or 1_000_003              ║
║ MOD               ║ 1e9+7 (prevent overflow)     ║ 1e9+7 AND 1e9+9 (double hash)      ║
║ Result            ║ hash == exact integer value  ║ hash ≈ equality proxy              ║
║ Collision risk    ║ ZERO (math is exact)         ║ ~1/MOD per pair → use double hash  ║
║ Use case          ║ "treat substring as number"  ║ "are these two substrings equal?"  ║
║ Examples          ║ LC 3756, LC 179              ║ LC 1044, LC 1316, LC 28            ║
╚═══════════════════╩══════════════════════════════╩════════════════════════════════════╝

RULE: "multiply / sum / numeric value"   → BASE=10, no collision risk.
      "compare / duplicate / repeated"   → string hashing + double MOD.
```

---

## The Master Matrix: All 5 Problems On One Page

```
╔══════════════════════╦══════════════╦════════════════════════╦══════════════════════════════════════════╗
║ PROBLEM              ║ BASE         ║ QUERY TYPE             ║ HOW THE FORMULA IS USED                  ║
╠══════════════════════╬══════════════╬════════════════════════╬══════════════════════════════════════════╣
║ LC 187 Repeated DNA  ║ 4 (A/C/G/T)  ║ fixed window = 10      ║ slide hash, flag duplicates in HashSet   ║
╠══════════════════════╬══════════════╬════════════════════════╬══════════════════════════════════════════╣
║ LC 28 First Occur.   ║ 31           ║ fixed window = |needle| ║ match when windowHash == needleHash      ║
║ (Rabin-Karp)         ║              ║                        ║                                          ║
╠══════════════════════╬══════════════╬════════════════════════╬══════════════════════════════════════════╣
║ LC 1316 Echo Substr  ║ 31           ║ arbitrary [l, r]       ║ hash(i,mid) == hash(mid+1,j)             ║
╠══════════════════════╬══════════════╬════════════════════════╬══════════════════════════════════════════╣
║ LC 1044 Longest Dup  ║ 31           ║ binary search on len   ║ slide hash + HashSet inside checker      ║
╠══════════════════════╬══════════════╬════════════════════════╬══════════════════════════════════════════╣
║ LC 3756 Concat Digit ║ 10           ║ numeric value of range ║ hash(l,r) IS the integer — exact answer  ║
╚══════════════════════╩══════════════╩════════════════════════╩══════════════════════════════════════════╝
```

---

## Watch Me Apply The Template To Each Problem

### LC 187: Repeated DNA Sequences
```
s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

STEP 2: BASE=4 (A→1, C→2, G→3, T→4),  build pow[]
STEP 3: build prefix[]

STEP 4: for each window of length 10 starting at l:
        h = hash(l, l+9)
        if h in seen → add substring to result
        seen.add(h)

SLIDING OPTIMIZATION (O(1) per step instead of O(1) formula):
        newHash = (oldHash - val(s[l-1]) * pow[9]) * BASE + val(s[l+9])
        (drop leftmost character, shift, add new rightmost character)

Output: ["AAAAACCCCC", "CCCCCAAAAA"]  ✅

KEY INSIGHT: storing hash integers in a HashSet is O(1) per lookup.
Storing actual 10-char strings is O(10) per lookup = 10× slower.
```

### LC 28: Find First Occurrence (Rabin-Karp)
```
haystack = "sadbutsad",  needle = "sad"

STEP 2+3: build prefix[] and pow[] for haystack
STEP 4:   compute needleHash once

slide window of length 3 across haystack:
    i=0: hash(0,2) = hash("sad") == needleHash → return 0  ✅

WHY RABIN-KARP BEATS NAIVE:
    Naive:       compare char-by-char at every position → O(N×M)
    Rabin-Karp:  compare ONE integer at every position  → O(N+M) expected
```

### LC 1316: Distinct Echo Substrings
```
s = "abcabcabc"

STEP 2+3: BASE=31, build prefix[] and pow[]

STEP 4: for each even-length substring s[i..j]:
        mid = (i + j) / 2
        if hash(i, mid) == hash(mid+1, j):
            → echo confirmed
            → add hash(i, j) to result HashSet (deduplicates by content)

Output: size of HashSet  ✅

THE O(1) CHECK: without prefix hashes, each comparison costs O(len).
With prefix hashes it costs O(1).
Total complexity: O(N³) naive → O(N²) with this pattern.
```

### LC 1044: Longest Duplicate Substring
```
s = "banana"

STEP 2+3: BASE=31, build prefix[] and pow[]

BINARY SEARCH on answer length L:
    lo=1, hi=n-1
    while lo <= hi:
        mid = (lo+hi)/2
        if hasDuplicateOfLength(mid) → lo = mid+1
        else                         → hi = mid-1

hasDuplicateOfLength(L):
    HashSet<Long> seen
    for i in 0..n-L:
        h = hash(i, i+L-1)
        if h in seen → return true
        seen.add(h)
    return false

Output: "anana"  ✅  (length=5, found via binary search)

COMPLEXITY: O(N log N)  vs  O(N² log N) without hashing
```

### LC 3756: Concatenate Non-Zero Digits × Sum
```
STEP 2: BASE=10  ← this is a decimal number problem, not string matching
STEP 3: prefix[i] = prefix[i-1]*10 + digit(s[i])
        (only process non-zero digit positions as specified by problem)

STEP 4: hash(l, r) = exact numeric value of the digit substring
        multiply by the precomputed range sum

KEY DIFFERENCE:
    String hashing:   hash = proxy for equality  (collision possible)
    BASE=10 problem:  hash = exact integer value  (no collision, ever)
```

---

## The Sliding Window Hash Optimization

```
For FIXED-LENGTH window problems (LC 187, LC 28, LC 1044):
you do NOT need the full prefix formula every step.

SLIDING FORMULA (O(1) per slide):
    hash(l+1, r+1) = (hash(l,r) - val(s[l]) * pow[len-1]) * BASE + val(s[r+1])

BREAKDOWN:
    val(s[l]) * pow[len-1]  →  removes leftmost character's contribution
    multiply by BASE         →  shifts all remaining characters left one position
    + val(s[r+1])            →  appends the new rightmost character

WHEN TO USE WHICH:
    Fixed window + sequential left-to-right scan  →  sliding hash (faster constant)
    Arbitrary [l, r] queries in any order          →  prefix array (required)
    Binary search on length (LC 1044)              →  sliding hash inside checker
```

---

## The Decision Tree

```
After identifying a substring-value / string-matching problem:

Q1: Is the substring being treated as a NUMBER or as a SEQUENCE?
    
    As a NUMBER (compute the integer value of digit substring)
        → BASE=10, result = exact integer, zero collision risk
    
    As a SEQUENCE (compare or match substrings)
        → BASE=31/131, result = hash proxy, use double hashing in contests


Q2: Are the queries fixed-length windows or arbitrary [l,r]?
    
    FIXED length    → sliding hash  (O(1) per step, O(1) extra space)
    ARBITRARY [l,r] → prefix array  (O(N) precompute, O(1) per query)


Q3: What is the outer algorithmic structure?
    
    "Find duplicates of a fixed length"    → HashSet + sliding hash
    "Longest duplicate substring"          → binary search + HashSet
    "Count valid (l,r) pairs"              → nested loop + O(1) equality check
    "Numeric value of a range"             → direct prefix formula


Q4: Do I need to guard against hash collisions?
    
    Numeric value problems   → NO  (BASE=10, result is mathematically exact)
    Competitive programming  → YES, always double hash
    FAANG interviews         → mention the risk; single hash usually accepted
    
    Double hash: compute with MOD1=1e9+7 AND MOD2=1e9+9
                 store pair (h1, h2) as h1 * MOD2 + h2 in HashSet<Long>
                 collision probability ≈ 1 / 10^18 per comparison


Q5: Are the intermediate products overflowing?
    
    In Java/C++: prefix[l] * pow[len] can reach (10^9)^2 = 10^18
                 → fits in long, but apply % MOD after EVERY multiplication
    Rule: (a * b % MOD) not (a * b) then %  — the latter overflows first
```

---

## The Muscle Memory Drill

Every time you see a substring-value or range-query string problem, **immediately write these on paper:**

```
BASE     = ???    (10 for digit-value, 31/131 for character hash)
MOD      = ???    (1_000_000_007, or two MODs for safety)

pow[i]     = BASE^i % MOD           (precompute forward, up to n)
prefix[i]  = prefix[i-1]*BASE + v   (precompute forward pass)

hash(l,r)  = (prefix[r] - prefix[l-1]*pow[r-l+1] % MOD + MOD) % MOD

outer loop = ???   (fixed sliding? binary search? nested?)
collision? = ???   (numeric → no; string match → double hash)
```

Do this for 10 problems and it becomes a reflex.
The moment you see "substring as integer" or "repeated/duplicate substrings"
your hand automatically writes `prefix[i] = prefix[i-1]*BASE + val`
and the master formula `hash(l,r) = prefix[r] - prefix[l-1]*pow[len]`.

---

## The Trigger Signals Cheatsheet

```
SIGNAL IN PROBLEM                              → PATTERN
"treat substring as a number"                  → prefix hash, BASE=10
"find repeated / duplicate substrings"         → rolling hash + HashSet
"are s[l..r] and s[l'..r'] equal? in O(1)"    → prefix hash array
"queries [l, r] on a string"                   → prefix hash array
"modulo 10^9+7" on a string problem            → polynomial rolling hash
"longest substring appearing at least twice"   → binary search + rolling hash
"substring equals its reverse"  (palindrome)   → forward + reverse prefix hash
"implement strStr / string search"             → Rabin-Karp rolling hash
"N ≤ 10^5, O(N²) is too slow for substrings"  → O(N²) outer + O(1) inner check
```

---

## The Core Code Template (Java)

```java
// TEMPLATE: Polynomial Rolling Hash with Prefix Array
// Time: O(N) precompute + O(1) per query    Space: O(N)

class RollingHash {
    private static final long MOD  = 1_000_000_007L;
    private static final long BASE = 31L;   // use 10L for digit-value problems

    long[] prefix, pow;

    RollingHash(int[] vals, int n) {        // vals[i] = numeric value of s[i]
        prefix = new long[n + 1];           // prefix[0] = 0 (empty string)
        pow    = new long[n + 1];
        pow[0] = 1;

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = (prefix[i] * BASE + vals[i]) % MOD;
            pow[i + 1]    = pow[i] * BASE % MOD;
        }
    }

    // hash of s[l..r], 0-indexed, inclusive
    long hash(int l, int r) {
        int len = r - l + 1;
        //         [value up to r]   [left garbage shifted into position]    [guard]
        return (prefix[r + 1] - prefix[l] * pow[len] % MOD + MOD) % MOD;
    }
}

// ── Arbitrary substring equality check ───────────────────────────────────────
RollingHash rh = new RollingHash(vals, n);
boolean equal = rh.hash(l1, r1) == rh.hash(l2, r2);  // true with high probability

// ── Sliding window (fixed length L, sequential scan) ─────────────────────────
long windowHash = rh.hash(0, L - 1);
for (int i = 1; i + L - 1 < n; i++) {
    // drop s[i-1], shift, append s[i+L-1]
    windowHash = (windowHash - vals[i-1] * rh.pow[L-1] % MOD + MOD) % MOD;
    windowHash = (windowHash * BASE + vals[i + L - 1]) % MOD;
    // windowHash now equals rh.hash(i, i + L - 1)
}

// ── Double hashing (collision-safe for contests) ─────────────────────────────
// Instantiate two RollingHash objects with different BASE/MOD.
// Store combined key: h1 * MOD2 + h2  as a single long in your HashSet.
```

---

## Common Mistakes & How To Avoid Them

```
MISTAKE 1: Forgetting +MOD before final % → negative hash values
    → prefix[r] - prefix[l]*pow[len] CAN be negative in Java/C++
    → always write (... - ... + MOD) % MOD, never skip the +MOD guard
    → Python only: % always returns non-negative, so +MOD is optional

MISTAKE 2: Multiplying two large values before taking % → overflow
    → prefix[l] and pow[len] are each up to ~10^9
    → their product is ~10^18 → fits in long, but apply % immediately
    → WRONG:  (prefix[r] - prefix[l] * pow[len]) % MOD
    → RIGHT:  (prefix[r] - prefix[l] * pow[len] % MOD + MOD) % MOD
                                      ^^^^^^^^^^^^ % here first

MISTAKE 3: Off-by-one between prefix[] indices and array indices
    → prefix[i+1] stores hash of s[0..i]  (most common convention)
    → query hash(l,r) uses prefix[r+1] and prefix[l], NOT prefix[r] and prefix[l-1]
    → pick ONE convention, comment it, never switch mid-solution

MISTAKE 4: Not precomputing pow[] → O(log n) per query instead of O(1)
    → computing BASE^len on-the-fly via Math.pow or fast exponentiation
      costs O(log len) per query, killing the O(1) guarantee
    → always precompute pow[0..n] in the constructor

MISTAKE 5: Using BASE=31 for a digit numeric-value problem
    → if the answer IS the integer "123" = 1×100 + 2×10 + 3, you need BASE=10
    → BASE=31 gives an arbitrary hash that happens to be computable fast,
      but its value has no relationship to the decimal integer "123"

MISTAKE 6: Skipping double hashing in a contest
    → single MOD: collision probability ≈ n²/MOD ≈ 1% for n=10^5
    → this is enough to fail ~1 in 100 test cases silently
    → always use two independent (BASE, MOD) pairs in competitive programming

MISTAKE 7: Using sliding hash when you need arbitrary [l,r] queries
    → sliding hash gives hash(i, i+L-1) ONLY for the current window position
    → it cannot answer "what is hash(3, 17)?" on demand
    → if queries are not sequential or not fixed-length → use prefix array
```

---

## Sister Problems To Solidify This Pattern

| Problem | BASE | Outer Structure | Key Trick |
|---------|------|-----------------|-----------|
| **LC 187 Repeated DNA** | 4 | sliding window L=10 | HashSet on hash integers |
| **LC 28 First Occurrence** | 31 | sliding window L=\|needle\| | match when hashes equal |
| **LC 1316 Distinct Echo** | 31 | nested loop all even substrings | hash(i,mid)==hash(mid+1,j) |
| **LC 1044 Longest Dup Sub** | 31 | binary search on length | sliding hash inside checker |
| **LC 3756 Concat Digits** | 10 | range queries | hash = exact numeric value |
| LC 214 Shortest Palindrome | 31 | compare forward vs reverse hash | two prefix arrays (fwd + rev) |
| LC 1392 Longest Happy Prefix | 31 | single pass | prefix hash == suffix hash |
| LC 2156 Find Substring With Given Hash | given | reverse-engineer | work backwards from formula |

Master the first five and you **own** the Polynomial Rolling Hash pattern forever. 🔱