## The Peak-Valley State Machine

Every problem in this pattern follows this exact flow:

found it on: LC 122 Best Time to Buy/Sell Stock II, LC 376 Wiggle Subsequence, LC 845 Longest Mountain

```
STEP 1: Forget the array. Think only in DELTAS.
        delta = nums[i] - nums[i-1]
        
        delta > 0  →  ASCENDING  (climbing to a peak)
        delta < 0  →  DESCENDING (falling to a valley)
        delta == 0 →  PLATEAU    (trend is broken/paused)
        
        Ask: "what does a single step UP or DOWN mean
              for the quantity I am optimizing?"

STEP 2: Define your STATE MACHINE.
        Pick the minimum number of state variables that
        capture everything you need to know about the
        sequence so far — nothing else.
        
        e.g. Wiggle: (up_length, down_length)
        e.g. Mountain: (up_length, down_length, in_mountain?)
        e.g. Stock II: (running_profit)  ← single accumulator
        
        Ask: "what is the smallest set of numbers such that,
              if you told me only those numbers, I could
              continue the problem correctly?"

STEP 3: Write the TRANSITION TABLE.
        For each (current_state, delta_sign) pair, define:
            → new state values
            → any accumulator updates
        
        This table IS the algorithm. The code is just
        a transcription of this table.
        
        Ask: "what happens at the EXACT moment the delta
              changes sign? That inflection point is where
              all the interesting math lives."

STEP 4: Handle PLATEAUS explicitly.
        delta == 0 is almost always a reset or a no-op.
        It is the most common source of off-by-one bugs.
        
        Ask: "does a flat step break my current streak?
              does it count as part of a valley or peak?
              should I reset one counter but not the other?"

STEP 5: Prove O(1) space is valid (exchange argument).
        You only ever look at nums[i] and nums[i-1].
        The state variables are updated in-place.
        No suffix/prefix arrays. No recursion stack.
        
        Ask: "am I storing anything proportional to n?
              if yes, can I derive it on-the-fly instead?"
```

**That's it. Every peak-valley / alternating / trend problem. No exceptions.**

---

## The Transition Table: Your Algorithm On One Page

```
╔══════════════════╦═══════════════╦═════════════════════════════════════════╗
║ PROBLEM          ║ STATE         ║ TRANSITION RULES                        ║
╠══════════════════╬═══════════════╬═════════════════════════════════════════╣
║ Stock II         ║ profit        ║ delta>0 → profit += delta               ║
║ (sum pos deltas) ║               ║ delta≤0 → do nothing                    ║
╠══════════════════╬═══════════════╬═════════════════════════════════════════╣
║ Wiggle Subseq    ║ (up, down)    ║ delta>0 → up   = down + 1               ║
║ (alt. lengths)   ║               ║ delta<0 → down = up   + 1               ║
║                  ║               ║ delta=0 → no change                     ║
╠══════════════════╬═══════════════╬═════════════════════════════════════════╣
║ Longest Mountain ║ (up, down)    ║ delta>0 AND down>0 → reset: up=1,down=0 ║
║ (shape detector) ║               ║ delta>0 AND down=0 → up++               ║
║                  ║               ║ delta<0 AND up>0   → down++             ║
║                  ║               ║ delta=0            → reset: up=0,down=0 ║
║                  ║               ║ valid mountain: up>0 AND down>0         ║
║                  ║               ║ length = up + down + 1                  ║
╚══════════════════╩═══════════════╩═════════════════════════════════════════╝
```

---

## Watch Me Apply This To All 3 Problems

### LC 122: Best Time to Buy and Sell Stock II
```
nums = [7, 1, 5, 3, 6, 4]

STEP 1: deltas = [-6, +4, -2, +3, -2]
STEP 2: state  = (profit = 0)
STEP 3: transitions:
        delta=-6 → skip (negative)
        delta=+4 → profit += 4  →  profit=4
        delta=-2 → skip
        delta=+3 → profit += 3  →  profit=7
        delta=-2 → skip

Output: 7  ✅

WHY IT WORKS: buying at valley and selling at peak gives the same
profit as summing every positive delta along the way.
No need to find actual peaks and valleys — just sum the uphill.
```

### LC 376: Wiggle Subsequence
```
nums = [1, 7, 4, 9, 2, 5]

STEP 1: deltas = [+6, -3, +5, -7, +3]
STEP 2: state  = (up=1, down=1)  ← base: single element is both
STEP 3: transitions:
        delta=+6 → up   = down+1 = 2   state=(2,1)
        delta=-3 → down = up+1   = 3   state=(2,3)
        delta=+5 → up   = down+1 = 4   state=(4,3)
        delta=-7 → down = up+1   = 5   state=(4,5)
        delta=+3 → up   = down+1 = 6   state=(6,5)

Output: max(up, down) = 6  ✅

WHY IT WORKS: up and down track the longest alternating subsequence
ending in each direction. They leapfrog each other.
up can only grow after a downward step. down only after an upward step.
The cross-dependency IS the alternating constraint.
```

### LC 845: Longest Mountain in Array
```
nums = [2, 1, 4, 7, 3, 2, 5]

STEP 1: walk from index 1, compute delta with previous
STEP 2: state = (up=0, down=0, best=0)
STEP 3: transitions:
        i=1: delta=-1 (down, but up=0 → no valid mountain yet) → reset up=0,dn=0
        i=2: delta=+3 (ascending, dn=0) → up=1
        i=3: delta=+3 (ascending, dn=0) → up=2
        i=4: delta=-4 (descending, up>0) → down=1  check: up=2,dn=1 → len=4
        i=5: delta=-1 (descending, up>0) → down=2  check: up=2,dn=2 → len=5
        i=6: delta=+3 (ascending, but dn>0 → new mountain starts) → up=1,dn=0

Output: best = 5  ✅

KEY TRANSITION: going uphill after a downhill → the previous mountain ended.
Reset up=1 (current step IS the first uphill of new mountain), down=0.
```

---

## The Decision Tree

```
After identifying this is a Peak-Valley problem:

Q1: What am I optimizing?
    
    SUM of something         → accumulator pattern (Stock II)
    LENGTH of something      → counter pair (up, down)
    COUNT of something       → event-driven counter


Q2: What triggers an "event"?
    
    Sign change in delta     → the inflection point (peak or valley)
    Specific sign of delta   → each positive step (Stock II)
    Specific sequence        → up then down (Mountain)


Q3: How do plateaus (delta=0) behave?
    
    Treat as reset           → Mountain (flat breaks the shape)
    Treat as no-op           → Wiggle, Stock II (flat just continues)
    Treat as valley or peak  → problem-specific (read carefully)


Q4: Is the current state enough to make the next decision?
    
    YES → O(1) space state machine is valid
    NO  → you need more history → consider DP or prefix arrays
    
    Test: "if I told you only [state variables], could you
           correctly process the next element?"
    If yes → your state is complete. ✅


Q5: Is O(1) space valid? (always verify)
    
    Do I look at any element beyond nums[i] and nums[i-1]? → if YES, reconsider
    Do I store any array proportional to n?               → if YES, eliminate it
    Can every past decision be summarized in my state?    → if YES, you're done ✅
```

---

## The Muscle Memory Drill

Every time you see a trend/shape/alternating problem, **immediately write these on paper:**

```
delta[i]   = nums[i] - nums[i-1]   (always start here, never skip this)

states     = ???   (minimum variables: what do I need to continue correctly?)

transitions = ???
    delta > 0 : ___
    delta < 0 : ___
    delta = 0 : ___   ← never forget this case

event moment = ???   (what happens at the EXACT sign change? that's the math)

plateau rule = ???   (reset? no-op? counts as something?)
```

Do this for 10 problems and it becomes a reflex.
The moment you see "1D array + trend/shape/alternating"
your hand automatically writes `delta = nums[i] - nums[i-1]`
and draws the three-row transition table.

---

## The Trigger Signals Cheatsheet

```
SIGNAL IN PROBLEM                          → PATTERN
"buy and sell, unlimited transactions"     → sum all positive deltas
"alternating subsequence"                  → (up, down) leapfrog counters
"mountain / hill / valley shape"           → (up, down) reset on plateau
"longest zigzag"                           → wiggle variant
"contiguous subarray, specific shape"      → state machine with reset
"O(1) space" constraint hinted             → this pattern over DP
"you can only hold one item at a time"     → positive delta accumulation
"strictly increasing then decreasing"      → mountain detector
```

---

## The Core Code Templates (Java)

### Template 1 — Stock II (Sum Positive Deltas)
```java
// Capture profit from every uphill slope
// Time: O(n)  Space: O(1)
public int maxProfit(int[] prices) {
    int profit = 0;
    for (int i = 1; i < prices.length; i++) {
        int delta = prices[i] - prices[i - 1];
        if (delta > 0) profit += delta;   // only climb uphill
    }
    return profit;
}
// WHY: buying at i-1 and selling at i on every positive day
// is equivalent to finding every valley-to-peak profit.
// The math works because profit(A→C) = profit(A→B) + profit(B→C).
```

### Template 2 — Wiggle Subsequence (Leapfrog Counters)
```java
// Longest alternating subsequence
// Time: O(n)  Space: O(1)
public int wiggleMaxLength(int[] nums) {
    int up = 1, down = 1;   // single element qualifies as both
    for (int i = 1; i < nums.length; i++) {
        int delta = nums[i] - nums[i - 1];
        if (delta > 0) up   = down + 1;  // uphill extends a sequence ending downhill
        if (delta < 0) down = up   + 1;  // downhill extends a sequence ending uphill
        // delta == 0 → no change (plateau doesn't extend either direction)
    }
    return Math.max(up, down);
}
// WHY: up and down are mutually dependent.
// up can only grow when we just went down (and vice versa).
// This cross-dependency encodes the alternating constraint in O(1).
```

### Template 3 — Longest Mountain (Shape Detector with Reset)
```java
// Longest subarray that forms a strict mountain (up then down)
// Time: O(n)  Space: O(1)
public int longestMountain(int[] arr) {
    int up = 0, down = 0, best = 0;
    for (int i = 1; i < arr.length; i++) {
        int delta = arr[i] - arr[i - 1];

        // ← KEY: going uphill AFTER a downslope = old mountain ended, new one starts
        if (delta > 0 && down > 0) { up = 1; down = 0; }
        else if (delta > 0)        { up++;               }
        else if (delta < 0 && up > 0) {
            down++;
            best = Math.max(best, up + down + 1);  // +1 for the peak element
        }
        else { up = 0; down = 0; }  // plateau OR descending with no up → full reset
    }
    return best;
}
// WHY: a valid mountain requires BOTH up>0 AND down>0.
// We only record a candidate length when we are on the downslope
// (because the mountain is only "proven" once it starts descending).
// The reset on plateau is critical — flat ground breaks the mountain shape.
```

---

## Why The Leapfrog Works (Wiggle Deep Dive)

```
up   = length of longest wiggle subsequence ending with an UPWARD move
down = length of longest wiggle subsequence ending with a DOWNWARD move

When delta > 0 (we go UP):
    → we can extend any subsequence that ended going DOWN
    → up = down + 1
    → down does NOT change (we didn't just go down)

When delta < 0 (we go DOWN):
    → we can extend any subsequence that ended going UP
    → down = up + 1
    → up does NOT change

When delta = 0 (PLATEAU):
    → we cannot extend either alternating direction
    → both stay the same

The genius: up and down are never incremented independently.
They always reference EACH OTHER. This is the alternating
constraint expressed as state dependency, not as a check.
```

---

## Common Mistakes & How To Avoid Them

```
MISTAKE 1: Forgetting delta == 0 entirely
    → Silent wrong answer on inputs like [1,1,2,3,3,2]
    → Always write the delta=0 case explicitly, even if it's a no-op
    → In Mountain: plateau resets BOTH up and down to 0

MISTAKE 2: Recording mountain length on the UPSLOPE
    → You don't know if it will come down yet
    → Only record best = up + down + 1 when delta < 0 AND up > 0
    → A mountain is only confirmed when it starts descending

MISTAKE 3: Not resetting up when delta > 0 after a downslope (Mountain)
    → Going uphill after downhill = a NEW mountain is starting
    → if (delta > 0 && down > 0) { up = 1; down = 0; }  ← this line is critical
    → Without it, you merge two separate mountains into one

MISTAKE 4: Initializing up=0, down=0 in Wiggle (should be 1,1)
    → A single element trivially qualifies as a wiggle subsequence of length 1
    → Initialize up=1, down=1 to account for the base case

MISTAKE 5: Using delta > 0 check for Stock II but forgetting equal prices
    → prices[i] == prices[i-1] contributes zero profit → correctly skipped
    → no special case needed, but be aware it's handled by the delta > 0 guard

MISTAKE 6: Confusing "subsequence" vs "subarray"
    → Wiggle: subsequence (elements need not be contiguous) → O(1) state is fine
    → Mountain: subarray (elements must be contiguous) → reset on any break
    → Wrong classification leads to wrong reset logic
```

---

## Sister Problems To Solidify This Pattern

| Problem | State Variables | Key Transition |
|---------|----------------|----------------|
| **LC 122 Stock II** | `profit` | `delta>0 → profit+=delta` |
| **LC 376 Wiggle Subseq** | `(up, down)` | leapfrog on sign change |
| **LC 845 Longest Mountain** | `(up, down)` | reset on plateau or up-after-down |
| LC 978 Longest Turbulent Subarray | `(inc, dec)` | reset on plateau, swap on sign flip |
| LC 1186 Max Subarray Sum w/ Removal | `(with, without)` | DP + state machine |
| LC 2269 Find K-Beauty of a Number | sliding window variant | different domain, same delta thinking |
| LC 135 Candy | `(up, down)` | two-pass or single-pass mountain logic |

Master the first three and you **own** the Peak-Valley State Machine forever. 🔱