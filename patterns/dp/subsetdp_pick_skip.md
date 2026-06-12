## Pattern 9: DP Pick/Skip — Subset Selection with State

Every problem in this pattern follows this exact flow:

found it on: 3877. Minimum Removals to Achieve Target XOR, LC 416 Partition Equal Subset Sum,
             LC 474 Ones and Zeroes, LC 494 Target Sum, LC 1049 Last Stone Weight II,
             LC 2787 Ways to Express as Product

```
STEP 1: Recognize the "subset with a property" shape.
        
        You are given an array. You must CHOOSE a subset
        (keep some elements, discard others). The chosen subset
        must satisfy some condition on its AGGREGATE (sum, XOR,
        product, count). You want to maximize, minimize, or count
        the number of valid subsets.
        
        The two decisions per element: PICK it or SKIP it.
        These two branches cover all 2^N possible subsets.
        Brute force = O(2^N). DP collapses it to O(N × states).
        
        Ask: "for each element, do I have exactly two choices:
              include it or exclude it from my subset?"

STEP 2: Define the DP state.
        
        dp[i][s] = the BEST VALUE achievable considering
                   elements 0..i and having aggregate state s.
        
        "Best value" is problem-specific:
            MAXIMIZE elements kept  →  dp[i][s] = max count/sum kept
            MINIMIZE elements kept  →  dp[i][s] = min count kept
            COUNT subsets           →  dp[i][s] = number of subsets
            BOOLEAN reachability    →  dp[i][s] = true/false
        
        "Aggregate state s" is what the picked elements contribute:
            XOR of picked elements  →  s ∈ [0, maxXorValue]
            Sum of picked elements  →  s ∈ [0, totalSum]
            (count of 0s, count of 1s) → 2D state  (LC 474)
        
        Ask: "what single number describes the 'running total'
              of elements I have picked so far?"

STEP 3: Write the recurrence — SKIP branch and PICK branch.
        
        SKIP element i:
            dp[i][s] from dp[i-1][s]
            (aggregate unchanged, just move to next element)
        
        PICK element i:
            dp[i][s] from dp[i-1][s'] where s' is the "reverse"
            (what state did I need to be in BEFORE picking i
             so that picking i brings me to state s?)
        
        REVERSE STATE — the critical step:
            If aggregate is XOR:  s' = s ^ nums[i]   (XOR is its own inverse)
            If aggregate is SUM:  s' = s - nums[i]    (subtraction)
            If aggregate is AND:  not invertible → use forward direction instead
        
        Ask: "if I am now in state s and I just picked nums[i],
              what state was I in BEFORE picking it?"

STEP 4: Identify the transition direction (PULL vs PUSH).
        
        PULL (backward): compute dp[i][s] by looking at dp[i-1][...]
            dp[i][s] = best(skip: dp[i-1][s], pick: f(dp[i-1][s ^ nums[i]]))
            ← this is the standard form. Use when reverse state is cheap.
        
        PUSH (forward): compute what dp[i][...] receives from dp[i-1][s]
            for each (i-1, s): propagate to (i, s) (skip) and (i, s^nums[i]) (pick)
            ← use when the reverse is hard to compute.
        
        RULE: XOR and SUM problems → PULL (inverse is trivial)
              Problems without clean inverse → PUSH

STEP 5: Initialize, fill, and read the answer.
        
        INITIALIZATION:
            dp[0][0]        = 0 kept  (empty prefix, zero aggregate, nothing picked)
            dp[0][nums[0]]  = 1 kept  (first element picked)
            All others      = -INF (or INF or 0 depending on what you're optimizing)
        
        FILL: outer loop = elements (i = 1..n-1)
              inner loop = all possible aggregate states s
        
        ANSWER: read dp[n-1][target]
            if value == -INF (or invalid sentinel) → impossible → return -1
            else → convert to the asked quantity (e.g. n - maxKept = removals)
        
        Ask: "what sentinel means UNREACHABLE? (-INF for max, +INF for min, false for bool)"
```

**That's it. Every subset-selection DP problem. No exceptions.**

---

## The PULL Recurrence: One Mental Model For All Problems

```
              ┌─────────────────────────────────────────────────────────┐
              │                                                         │
              │  dp[i][s] = best(                                       │
              │      SKIP: dp[i-1][s],                                  │
              │      PICK: f( dp[i-1][ reverse(s, nums[i]) ] )         │
              │  )                                                      │
              │                                                         │
              │  where reverse(s, v) = "state before picking v         │
              │                         to end up at state s"          │
              │                                                         │
              └─────────────────────────────────────────────────────────┘

REVERSE STATE BY AGGREGATE TYPE:
    XOR sum:   reverse(s, v) = s ^ v        (XOR is self-inverse: a^b^b = a)
    Integer sum: reverse(s, v) = s - v      (subtraction)
    Product:   reverse(s, v) = s / v        (only if v divides s)
    Count:     reverse(s, v) = s - 1        (just decrement count)

f() IS the combination with the current element:
    Maximize kept:  f(prev) = 1 + prev      (pick adds 1 to kept count)
    Minimize kept:  f(prev) = 1 + prev      (same, but take min not max)
    Count subsets:  f(prev) = prev          (add the number of ways)
    Reachability:   f(prev) = prev || true  (OR)
```

---

## The Master Matrix: All 5 Problems On One Page

```
╔═══════════════════════╦══════════════════════════╦═════════════════════╦═══════════════════════════════════╗
║ PROBLEM               ║ dp[i][s] means            ║ AGGREGATE s         ║ RECURRENCE                        ║
╠═══════════════════════╬══════════════════════════╬═════════════════════╬═══════════════════════════════════╣
║ Min Removals XOR=T    ║ max elements kept with    ║ XOR of kept = s     ║ max(skip, 1+dp[i-1][s^nums[i]])   ║
║ (this problem)        ║ XOR of kept = s           ║ s ∈ [0, 16383]      ║ answer = n - dp[n-1][target]      ║
╠═══════════════════════╬══════════════════════════╬═════════════════════╬═══════════════════════════════════╣
║ LC 416                ║ can we pick subset        ║ sum of picked = s   ║ dp[i][s] = dp[i-1][s] ||          ║
║ Partition Equal Sum   ║ with sum = s? (boolean)   ║ s ∈ [0, total/2]   ║            dp[i-1][s-nums[i]]     ║
╠═══════════════════════╬══════════════════════════╬═════════════════════╬═══════════════════════════════════╣
║ LC 494                ║ number of ways to assign  ║ running sum         ║ dp[i][s] = dp[i-1][s-nums[i]]     ║
║ Target Sum (+/-)      ║ +/- to reach sum s        ║ s ∈ [-total,+total] ║           + dp[i-1][s+nums[i]]    ║
╠═══════════════════════╬══════════════════════════╬═════════════════════╬═══════════════════════════════════╣
║ LC 1049               ║ max sum achievable with   ║ sum of kept = s     ║ max(skip, dp[i-1][s-nums[i]])     ║
║ Last Stone Weight II  ║ kept elements sum = s     ║ s ∈ [0, total/2]   ║ answer = total - 2*dp[n-1][best]  ║
╠═══════════════════════╬══════════════════════════╬═════════════════════╬═══════════════════════════════════╣
║ LC 474                ║ max items kept where      ║ (zeros, ones) used  ║ max(skip,                         ║
║ Ones and Zeroes       ║ zeros ≤ m, ones ≤ n       ║ 2D state            ║  1+dp[i-1][z-z0][o-o1])           ║
╚═══════════════════════╩══════════════════════════╩═════════════════════╩═══════════════════════════════════╝
```

---

## Watch Me Apply The Template To The XOR Problem

```
nums = [1, 2, 3],  target = 2

STEP 1: subset problem. pick/skip each element.
        want: max elements kept such that XOR of kept = target

STEP 2: dp[i][s] = max elements kept from nums[0..i] with XOR = s
        s ∈ [0, 16383].  sentinel = -INF (unreachable)

STEP 3: reverse state for XOR: s' = s ^ nums[i]
        PICK branch: dp[i][s] = 1 + dp[i-1][s ^ nums[i]]
        SKIP branch: dp[i][s] = dp[i-1][s]

STEP 4: PULL direction. Take max of both branches.

STEP 5: Initialize:
        dp[0][0]        = 0   (don't pick nums[0]=1, XOR=0, 0 kept)
        dp[0][nums[0]]  = 1   (pick nums[0]=1, XOR=1, 1 kept)
        dp[0][1]        = 1

Fill i=1 (nums[1]=2):
    s=0: skip=dp[0][0]=0,  pick=1+dp[0][0^2]=1+dp[0][2]=-INF  → dp[1][0]=0
    s=1: skip=dp[0][1]=1,  pick=1+dp[0][1^2]=1+dp[0][3]=-INF  → dp[1][1]=1
    s=2: skip=dp[0][2]=-INF, pick=1+dp[0][2^2]=1+dp[0][0]=1   → dp[1][2]=1
    s=3: skip=dp[0][3]=-INF, pick=1+dp[0][3^2]=1+dp[0][1]=2   → dp[1][3]=2

Fill i=2 (nums[2]=3):
    s=0: skip=dp[1][0]=0,  pick=1+dp[1][0^3]=1+dp[1][3]=3     → dp[2][0]=3
    s=1: skip=dp[1][1]=1,  pick=1+dp[1][1^3]=1+dp[1][2]=2     → dp[2][1]=2
    s=2: skip=dp[1][2]=1,  pick=1+dp[1][2^3]=1+dp[1][1]=2     → dp[2][2]=2  ← TARGET
    s=3: skip=dp[1][3]=2,  pick=1+dp[1][3^3]=1+dp[1][0]=1     → dp[2][3]=2

dp[2][target=2] = 2  (max 2 elements kept with XOR=2)
answer = n - maxKept = 3 - 2 = 1 removal  ✅

VERIFY: keep nums[0]=1 and nums[2]=3. XOR=1^3=2. ✓ Removed nums[1]=2. ✓
```

---

## Watch Me Apply To 2 More Problems

### LC 416: Partition Equal Subset Sum
```
nums = [1, 5, 11, 5],  total = 22,  target = 11

STEP 1: can we pick a subset that sums to total/2?
STEP 2: dp[i][s] = boolean: can we pick subset from 0..i summing to s?
STEP 3: reverse(s, v) = s - v  (subtraction for sum problems)
        PICK: dp[i][s] = dp[i-1][s - nums[i]]   (if s >= nums[i])
        SKIP: dp[i][s] = dp[i-1][s]
        combine with OR

INIT: dp[0][0] = true, dp[0][nums[0]] = true

Fill... dp[n-1][11] = true  →  return true  ✅

NOTE: this is the "knapsack reachability" variant. Same skeleton,
boolean instead of max/min, OR instead of max().
```

### LC 494: Target Sum
```
nums = [1, 1, 1, 1, 1],  target = 3

STEP 1: assign + or - to each element. count ways to reach sum = target.
STEP 2: dp[i][s] = number of ways to assign signs to 0..i reaching sum s
        s can be negative → offset: store s + total as index to avoid negatives
STEP 3: TWO pick branches (assign + or assign -):
        PLUS  branch: dp[i][s] += dp[i-1][s - nums[i]]
        MINUS branch: dp[i][s] += dp[i-1][s + nums[i]]
        SKIP doesn't exist here — every element must be assigned a sign

INIT: dp[0][+nums[0]] += 1,  dp[0][-nums[0]] += 1

Fill... dp[n-1][target] = 5  ✅

NOTE: no SKIP branch here — every element is used, just + or -.
This is still pick/skip at its core: "pick + sign" or "pick - sign."
```

---

## The Decision Tree

```
After identifying a subset DP problem:

Q1: What am I optimizing over the subset?
    
    MAXIMIZE something  →  dp = max(...),  sentinel = -INF
    MINIMIZE something  →  dp = min(...),  sentinel = +INF
    COUNT subsets       →  dp = count (add branches), sentinel = 0
    REACHABILITY        →  dp = boolean (OR branches), sentinel = false


Q2: What is the aggregate state s?
    
    XOR of kept         →  s ∈ [0, 2^bits - 1]
    SUM of kept         →  s ∈ [0, totalSum]
    SIGNED sum (+/-)    →  s ∈ [-total, +total]  → offset by total
    2D (count A, count B) → dp is 2D in state  (LC 474)
    
    Size of s determines your space: O(N × |s|)


Q3: What is the reverse state for PICK?
    
    XOR aggregate:  reverse(s, v) = s ^ v         (XOR is self-inverse)
    SUM aggregate:  reverse(s, v) = s - v          (check s >= v first)
    PRODUCT:        reverse(s, v) = s / v          (check divisibility)
    No clean inverse → use PUSH direction instead


Q4: How many "pick" branches are there?
    
    ONE binary choice (include or exclude)
        → standard pick/skip: two branches per element
    
    TWO choices for included elements (+sign or -sign)
        → two pick branches, no skip  (LC 494)
    
    K choices per element
        → K pick branches  (e.g. pick any of k colors)


Q5: Can I reduce space from O(N × S) to O(S)?
    
    YES if: dp[i] depends only on dp[i-1]  (all subset DP problems)
    HOW: use a 1D array, iterate s in REVERSE order (for sum problems)
         to prevent using the same element twice
    
    REVERSE iteration rule:
        SUM/XOR with each element used AT MOST ONCE → iterate s from high to low
        Unbounded (element reusable) → iterate s from low to high
    
    CAUTION: for XOR problems the reverse trick still applies —
             iterate s from maxXor down to 0 in the 1D version
```

---

## The Space Optimization: From O(N×S) to O(S)

```
2D DP (original):
    for i in 0..n-1:
        for s in 0..maxS:
            dp[i][s] = max(dp[i-1][s], 1 + dp[i-1][s ^ nums[i]])

1D DP (optimized) — iterate s in REVERSE to prevent reuse:
    dp = new int[maxS]  (initialize same way)
    Arrays.fill(dp, -INF)
    dp[0] = 0
    dp[nums[0]] = 1
    
    for i in 1..n-1:
        for s from maxS-1 down to 0:      ← REVERSE ORDER critical
            if dp[s ^ nums[i]] != -INF:
                dp[s] = max(dp[s], 1 + dp[s ^ nums[i]])

WHY REVERSE? Forward order would let nums[i] be "picked twice":
    if s=5, nums[i]=3: we read dp[5^3]=dp[6]
    if dp[6] was already updated in THIS pass using nums[i],
    we'd effectively pick nums[i] again → wrong.
    Reverse order ensures we always read the "before this element" values.

EXCEPTION: if the problem allows reuse (unbounded knapsack) → forward order is correct.
```

---

## The Muscle Memory Drill

Every time you see a "choose a subset satisfying a condition" problem,
**immediately write these on paper:**

```
optimize   = ???   (max kept? min removed? count subsets? reachability?)

state s    = ???   (XOR of kept? sum of kept? signed sum?)
             s range = [0, ???]   (determines array size)

sentinel   = ???   (-INF for max, +INF for min, 0 for count, false for bool)

reverse(s) = ???   (s^v for XOR, s-v for sum, s/v for product)

recurrence:
    SKIP: dp[i][s] = dp[i-1][s]
    PICK: dp[i][s] = f(dp[i-1][reverse(s, nums[i])])
    combine with: max / min / + / ||

init:
    dp[0][0]       = identity  (nothing picked yet, state = zero)
    dp[0][nums[0]] = first pick value

answer:
    dp[n-1][target] — convert sentinel to -1 if needed
```

Do this for 10 problems and it becomes a reflex.
The moment you see "choose a subset with some aggregate property"
your hand automatically writes `dp[i][s] = max(skip, pick)`
and asks "what is reverse(s, nums[i])?"

---

## The Trigger Signals Cheatsheet

```
SIGNAL IN PROBLEM                              → PATTERN
"remove minimum elements so XOR/sum = target" → maximize kept (pick/skip DP)
"can we partition array into two equal parts"  → subset sum reachability DP
"count ways to assign +/- signs"               → count variant, two pick branches
"minimize difference of two subset sums"       → sum DP, target = totalSum/2
"0/1 knapsack" (each item used at most once)   → pick/skip with reverse iteration
"items have two costs (e.g. 0s and 1s)"        → 2D state dp[z][o]
"n ≤ 40 but 2^40 too large"                   → meet-in-the-middle variant
"subset with maximum XOR"                      → Gaussian elimination (not this pattern)
"each element must be used exactly once"       → no SKIP branch, only PICK variants
```

---

## The Core Code Template (Java)

```java
// TEMPLATE: Subset DP — Maximize Elements Kept With Aggregate = Target
// Time: O(N × S)   Space: O(N × S), reducible to O(S)

// ── 2D VERSION (clearer, easier to debug) ────────────────────────────────────
public int subsetDP_2D(int[] nums, int target, int maxState) {
    int n = nums.length;
    int INF = Integer.MIN_VALUE / 2;        // sentinel: unreachable

    int[][] dp = new int[n][maxState];
    for (int[] row : dp) Arrays.fill(row, INF);

    dp[0][0] = 0;                            // skip nums[0]: state=0, kept=0
    dp[0][nums[0]] = 1;                      // pick nums[0]: state=nums[0], kept=1

    for (int i = 1; i < n; i++) {
        for (int s = 0; s < maxState; s++) {
            // SKIP branch
            int skip = dp[i-1][s];

            // PICK branch: reverse state for XOR is s ^ nums[i]
            int prev = s ^ nums[i];          // ← change this line for other aggregates
            int pick = (dp[i-1][prev] == INF) ? INF : 1 + dp[i-1][prev];

            dp[i][s] = Math.max(skip, pick);
        }
    }

    int maxKept = dp[n-1][target];
    return maxKept == INF ? -1 : n - maxKept;
}

// ── 1D VERSION (O(S) space, same logic) ──────────────────────────────────────
public int subsetDP_1D(int[] nums, int target, int maxState) {
    int INF = Integer.MIN_VALUE / 2;
    int[] dp = new int[maxState];
    Arrays.fill(dp, INF);

    dp[0] = 0;
    if (nums[0] < maxState) dp[nums[0]] = 1;

    for (int i = 1; i < n; i++) {
        // REVERSE iteration prevents reusing nums[i] in the same pass
        for (int s = maxState - 1; s >= 0; s--) {
            int prev = s ^ nums[i];          // reverse state for XOR
            if (dp[prev] != INF)
                dp[s] = Math.max(dp[s], 1 + dp[prev]);
            // skip branch is implicit: dp[s] retains its old value
        }
    }

    return dp[target] == INF ? -1 : nums.length - dp[target];
}

// ── BOOLEAN REACHABILITY VARIANT (LC 416) ────────────────────────────────────
public boolean canPartition(int[] nums) {
    int total = Arrays.stream(nums).sum();
    if (total % 2 != 0) return false;
    int target = total / 2;

    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int v : nums)
        for (int s = target; s >= v; s--)  // reverse: each element used once
            dp[s] = dp[s] || dp[s - v];    // skip || pick

    return dp[target];
}

// ── COUNT VARIANT (LC 494) ───────────────────────────────────────────────────
public int findTargetSumWays(int[] nums, int target) {
    int total = Arrays.stream(nums).sum();
    // offset signed sum: index = s + total
    int[] dp = new int[2 * total + 1];
    dp[total] = 1;                          // offset 0 → index = total

    for (int v : nums) {
        int[] next = new int[2 * total + 1];
        for (int s = 0; s <= 2 * total; s++) {
            if (dp[s] == 0) continue;
            if (s + v <= 2 * total) next[s + v] += dp[s];  // assign +
            if (s - v >= 0)         next[s - v] += dp[s];  // assign -
        }
        dp = next;
    }

    int idx = target + total;
    return (idx < 0 || idx > 2 * total) ? 0 : dp[idx];
}
```

---

## Common Mistakes & How To Avoid Them

```
MISTAKE 1: Wrong sentinel — using 0 instead of -INF for "max kept" DP
    → dp[i][s] = 0 could mean "reachable with 0 elements kept"
      OR "this state was never reached" — ambiguous and silently wrong
    → always use -INF (or Integer.MIN_VALUE/2) as unreachable sentinel
    → before adding 1 in PICK branch: check dp[prev] != sentinel first

MISTAKE 2: Forgetting the guard before PICK (adding to unreachable state)
    → 1 + (-INF) overflows or gives a fake "reachable" value
    → always: if dp[prev] == INF then pick = INF else pick = 1 + dp[prev]
    → or equivalently: skip the PICK branch entirely when prev is unreachable

MISTAKE 3: Forward iteration in 1D DP for bounded (use-once) problems
    → forward order lets the same element be picked multiple times in one pass
    → for 0/1 knapsack (each element used at most once): REVERSE iteration
    → for unbounded knapsack (element reusable): FORWARD iteration
    → confusing these gives wrong answers on test cases with duplicates

MISTAKE 4: Not offsetting signed sums (LC 494 style)
    → sums can be negative → can't use as array index directly
    → offset: store at index s + total so index is always >= 0
    → total size of dp array = 2*total + 1

MISTAKE 5: Treating XOR DP like sum DP (wrong reverse state)
    → sum: reverse(s, v) = s - v  (check s >= v before using)
    → XOR: reverse(s, v) = s ^ v  (always valid, no range check needed)
    → mixing them produces wrong transitions silently

MISTAKE 6: Reading dp[n-1][target] without checking sentinel
    → always: if dp[n-1][target] == sentinel → return -1 (impossible)
    → then convert: removals = n - dp[n-1][target]

MISTAKE 7: n ≤ 40 → not recognizing meet-in-the-middle upgrade
    → 2D DP with S up to 10^4 and n=40 is fine here: 40 × 16384 = 655K
    → BUT if n=40 and S is huge (e.g. sums up to 10^18) → DP is infeasible
    → n≤40 + huge state space = meet in the middle signal
    → split array in half, enumerate all 2^20 subsets each half, combine
```

---

## The XOR Inverse Property (Why XOR DP Is Cleaner Than Sum DP)

```
For SUM problems:
    "I am at state s and just picked v → I was at state s-v before"
    GUARD NEEDED: s-v must be >= 0, else this transition is invalid

For XOR problems:
    "I am at state s and just picked v → I was at state s^v before"
    NO GUARD NEEDED: s^v is always a valid XOR state (never negative,
    never out of range as long as s and v are within the bit width)

This is why XOR subset DP code is slightly cleaner:
    pick = 1 + dp[i-1][s ^ nums[i]]   ← no range check
vs sum DP:
    if (s >= nums[i]) pick = 1 + dp[i-1][s - nums[i]]   ← must guard

XOR IS ITS OWN INVERSE: a ^ b ^ b = a
So to "undo" XOR-ing v into state s, you just XOR v again.
Same operation both ways. This symmetry is what makes XOR DP elegant.
```

---

## Sister Problems To Solidify This Pattern

| Problem | State s | Sentinel | Branches | Key Twist |
|---------|---------|----------|----------|-----------|
| **This problem (XOR = target)** | XOR of kept | -INF | skip / pick | reverse = s^v |
| **LC 416 Partition Equal Subset** | sum of kept | false | skip / pick | boolean, target=total/2 |
| **LC 494 Target Sum** | signed running sum | 0 | pick+ / pick- | no skip; offset index |
| **LC 1049 Last Stone Weight II** | sum of kept | -INF | skip / pick | answer=total-2×best |
| **LC 474 Ones and Zeroes** | (zeros used, ones used) | -INF | skip / pick | 2D state |
| LC 2787 Ways to Express as Product | product state | 0 (count) | skip / pick | count variant |
| LC 956 Tallest Billboard | difference of two sums | -INF | skip/+left/+right | 3 branches |
| LC 879 Profitable Schemes | (people, profit) | 0 (count) | skip / pick | 2D state, count |

Master the first five and you **own** the Pick/Skip Subset DP pattern forever. 🔱