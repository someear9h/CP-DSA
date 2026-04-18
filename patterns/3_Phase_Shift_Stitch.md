## The Peak-Making Greedy + Prefix/Suffix Template

Every problem in this pattern follows this exact flow:

found it on: https://leetcode.com/problems/minimum-operations-to-make-array-elements-equal-to-target (variant of LC peak problems)

```
STEP 1: Identify the "peak" structure
        A valid peak at index i requires:
            nums[i] > nums[i-1]  AND  nums[i] > nums[i+1]
        
        Cost to make index i a peak:
            target = max(nums[i-1], nums[i+1]) + 1
            cost[i] = max(0, target - nums[i])
        
        Ask: "what is the minimum I need to increase nums[i] to
              satisfy BOTH its neighbors simultaneously?"

STEP 2: Recognize the independence structure
        
        CRITICAL INSIGHT: two adjacent indices i and i+1
        CANNOT BOTH be peaks (a peak needs to beat both neighbors,
        so having peaks at i and i+1 is contradictory).
        
        → Peaks must be placed at NON-ADJACENT indices.
        → This is the "independent set on a path" structure.
        
        Ask: "can two of my chosen positions conflict with each other?
              if yes, I must partition the positions."

STEP 3: Realize the two valid configurations
        
        For array of length n (0-indexed, valid peaks at 1..n-2):
        
        ODD  indices only:  positions 1, 3, 5, ...
        EVEN indices only:  positions 2, 4, 6, ...
        
        These two sets are guaranteed non-adjacent.
        Any max independent set must be a SUBSET of one of these.
        
        EXCEPTION: if n is odd, the last valid index (n-2) is odd,
        so odd-only always covers all possible peaks → only one
        case needed, return odd cost directly.

STEP 4: Precompute prefix (odd) and suffix (even) costs
        
        oddPrefix[i]  = total cost to make ALL odd  positions 1..i peaks
        evenSuffix[i] = total cost to make ALL even positions i..n-2 peaks
        
        Then the answer is min over all split points:
            oddPrefix[split-1] + evenSuffix[split+1]
        
        This covers: "take odd peaks on the left half,
                      even peaks on the right half."

STEP 5: Prove greedy is safe (exchange argument)
        
        For each candidate peak position, the cost formula
        max(0, max(left,right)+1 - nums[i]) is fixed and independent
        of other positions in the same parity class.
        
        → No interaction between same-parity peaks.
        → We must take ALL peaks in a chosen parity class
          (taking fewer never helps — more peaks is the goal).
        → Only decision: which parity class for which half.
        → Enumerate the split. ✅
```

**That's it. Every "maximize non-adjacent peaks with min cost" problem. No exceptions.**

---

## Watch Me Apply This To The Problem

```
nums = [5, 2, 1, 4, 3]   n=5

STEP 1: cost to make each index a peak:
        i=1: max(nums[0],nums[2])+1 = max(5,1)+1 = 6  → cost = max(0, 6-2) = 4
        i=2: max(nums[1],nums[3])+1 = max(2,4)+1 = 5  → cost = max(0, 5-1) = 4
        i=3: max(nums[2],nums[4])+1 = max(1,3)+1 = 4  → cost = max(0, 4-4) = 0

STEP 2: valid peaks at {1,3} OR {2}  (non-adjacent)
        → odd class:  i=1 and i=3  (positions 1,3,5,... for 1-indexed middles)
        → even class: i=2          (positions 2,4,6,...)

STEP 3: n=5 is odd → only odd-parity class is possible (covers all positions)
        total cost = cost[1] + cost[3] = 4 + 0 = 4  ✅

Output: 4
```

---

## The Decision Tree

```
After computing per-element peak cost:

Q1: What is n mod 2?
    
    n is ODD  → only one configuration (odd-indexed peaks)
               → return sum of all odd-position costs directly
    
    n is EVEN → two configurations possible
               → must try all split points between the two parity classes


Q2: How do I enumerate split points?
    
    Precompute:
        oddPrefix[i]  running from left  (i = 1,3,5,...)
        evenSuffix[i] running from right (i = n-2, n-4, ...)
    
    For each even split index i in [2, n-3]:
        candidate = oddPrefix[i-1] + evenSuffix[i+2]
    
    Answer = min(candidate for all splits, full-odd cost, full-even cost)


Q3: What is the validity condition for a peak?
    
    nums[i] > nums[i-1]  AND  nums[i] > nums[i+1]
    → target = max(nums[i-1], nums[i+1]) + 1
    → cost   = max(0, target - nums[i])


Q4: Is the greedy safe? (always ask this)
    
    Same-parity peaks don't interact → take all of them.
    Cross-parity interaction → resolved by the split enumeration.
    Taking ALL peaks in a chosen region is always optimal
    (more peaks = better, and cost per peak is independent). ✅
```

---

## The Classic Problems Mapped To This Template

```
This problem (maximize peaks, min cost)   → parity split + prefix/suffix
LC 845  Longest Mountain in Array         → find peaks, two pointers expand
LC 1095 Find in Mountain Array            → binary search on peak
LC 162  Find Peak Element                 → binary search for any peak
LC 2210 Count Hills and Valleys          → count direction changes (peaks+valleys)
LC 941  Valid Mountain Array              → single peak verification
"Max non-adjacent sum"  (House Robber)    → DP (not greedy, values vary)
```

---

## Watch Me Apply To 2 Variant Problems

### Variant: What if we want exactly k peaks, minimize cost?
```
STEP 1: compute cost[i] for each valid index i
STEP 2: separate odd-indexed and even-indexed costs
STEP 3: for each possible split (j odd peaks from left, k-j even from right):
        use prefix sums to find cheapest j peaks from odd set
        use suffix sums to find cheapest k-j peaks from even set
STEP 4: min over all j from 0 to k
NOTE: now you also need to sort within each parity class and
      greedily take cheapest ones → classic "top-k by cost" subproblem
```

### Variant: What if you can also DECREASE values?
```
STEP 1: cost[i] = |target - nums[i]|  (absolute difference)
STEP 2: target is still max(left,right)+1
        BUT now decreasing neighbors is also allowed:
        target = min over valid peak values (median of neighbors trick)
        → this becomes harder; DP might be needed
STEP 3: same parity structure applies, but cost per peak is no longer
        independent (lowering a neighbor changes adjacent costs)
→ SIGNAL: if decreases allowed AND adjacent peaks share neighbors,
  the independence breaks → switch to DP
```

---

## The Muscle Memory Drill

Every time you see a "maximize peaks/local maxima with minimum operations" problem,
**immediately write these on paper:**

```
cost[i]   = ???    (what does it cost to make index i a peak/local max?)

conflict? = ???    (can two chosen indices interfere? → check adjacency)

structure = ???    (non-adjacent → parity classes → prefix/suffix)

split?    = ???    (odd n → one config; even n → enumerate split point)

exchange  = ???    (why is taking all peaks in a parity region optimal?)
```

Do this for 10 problems and it becomes a reflex.
The moment you see "local maximum", "peak", "greater than both neighbors"
your hand automatically writes `cost[i] = max(0, max(L,R)+1 - arr[i])`
and draws the parity split diagram.

---

## The Trigger Signals Cheatsheet

```
SIGNAL IN PROBLEM                         → PATTERN
"nums[i] > nums[i-1] AND nums[i+1]"      → peak / local max problem
"maximize number of peaks"                → parity split greedy
"minimum operations to create peaks"      → prefix/suffix cost arrays
"non-adjacent elements satisfy condition" → parity classes (odd/even index)
"increase elements only"                  → cost = max(0, target - val)
"condition involves both neighbors"       → target = f(left, right) + 1
"split array into two halves"             → prefix + suffix enumeration
```

---

## Why The Prefix/Suffix Propagation Trick Works

```
oddPrefix[i] stores the total cost of ALL odd peaks seen so far.
But the loop only visits odd i values (1, 3, 5...).

The line:
    if (i + 1 < n) oddPrefix[i + 1] = currOddCost;

propagates the value to the NEXT index (which is even),
so that when the split enumeration queries oddPrefix[i-1]
at an even split point i, it always finds a valid (non-zero) answer
instead of 0 (the default).

Think of it as: "fill in the blanks so every index knows
the running cost up to the last odd peak before it."

Same logic applies to evenSuffix propagating leftward.
```

---

## Sister Problems To Solidify This Pattern

| Problem | What's the "peak" condition | Key insight |
|---------|----------------------------|-------------|
| **This problem** | `a[i] > a[i-1] AND a[i] > a[i+1]` | parity split + prefix/suffix |
| LC 2210 Count Hills | direction changes | track prev direction |
| LC 845 Longest Mountain | expand from each peak | two-pointer expand |
| LC 1855 Max Distance | `a[i] <= b[j]` across two arrays | two pointers on sorted |
| LC 376 Wiggle Subseq | alternating peaks/valleys | greedy, count direction flips |
| "Max non-adjacent sum" | no two adjacent chosen | House Robber DP (not greedy) |

Master the top 3 and you **own** the peak-greedy pattern forever. 🔱

---

## Common Mistakes & How To Avoid Them

```
MISTAKE 1: Forgetting the n is odd shortcut
    → Always check: if (n % 2 != 0) return currOddCost;
    → When n is odd, index n-2 (last valid peak) is always odd,
      so the odd class already covers the maximum number of peaks.

MISTAKE 2: Off-by-one in the split loop
    → Split loop: for (int i = 2; i < n-2; i += 2)
    → i starts at 2 (first even valid peak index)
    → i ends before n-2 (need at least one odd peak after the split)
    → query: oddPrefix[i-1] + evenSuffix[i+2]

MISTAKE 3: Not propagating prefix/suffix to adjacent slots
    → Without propagation, queries at even indices return 0 (wrong!)
    → Always add: oddPrefix[i+1] = currOddCost after each odd step
    → Always add: evenSuffix[i-1] = currEvenCost after each even step

MISTAKE 4: Using int instead of long for costs
    → nums[i] up to 1e9, n up to 1e5 → total cost up to ~1e14
    → Always use long for target, cost accumulation, and return type

MISTAKE 5: Thinking "I can skip cheap peaks to save cost"
    → Wrong! More peaks = better (we maximize count first).
    → Within a valid parity region, ALWAYS take all peaks.
    → The only choice is the split point, not which peaks to skip.
```