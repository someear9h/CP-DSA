## The Universal Recursion + Backtracking Template

Every problem in this pattern follows this exact flow:

found it on: https://leetcode.com/problems/create-grid-with-exactly-k-paths-i/
found new ones:
1. https://leetcode.com/problems/combination-sum/
2. https://leetcode.com/problems/combination-sum-ii/
3. https://leetcode.com/problems/subsets-ii/

```
STEP 1: Define the "choice" at each node
        At every recursive call, what are the options available
        right now? (pick a number, place a char, place an obstacle...)

        e.g. grid problem: choice = place '#' or leave '.' at (i,j)
        e.g. combination sum: choice = include candidates[i] or skip it

STEP 2: Define the state carried through recursion
        What minimal info do you need to know where you are and
        what's still possible?

        e.g. grid: (i, j) position in the grid
        e.g. combination sum: (start index, remaining target, current path)

STEP 3: Define the base case / goal check
        When do you stop recursing and either record an answer
        or bail out?

        VALID answer found  → record it (add path to result, return true)
        DEAD end / overshoot → prune, return false / return immediately

        e.g. grid: paths == k → true, paths < k → false (prune)
        e.g. combination sum: remaining == 0 → record, remaining < 0 → prune

STEP 4: Loop through choices at this level
        for each option:
            make the choice   (place '#', add candidates[i] to path)
            recurse into next state
            undo the choice   (place '.', remove candidates[i] from path)
        This make → recurse → undo triad IS backtracking.

STEP 5: Prune aggressively
        Sort first so pruning becomes possible.
        Skip a branch the moment it can't possibly lead anywhere.

        e.g. combination sum: if candidates[i] > remaining → break (sorted, rest bigger too)
        e.g. subsets II / combo sum II: if i > start && nums[i] == nums[i-1] → skip (dup at same depth)

STEP 6: Decide "reuse vs advance" for the index
        CAN reuse same element again    → recurse with same index i
        CANNOT reuse (each used once)   → recurse with index i+1
        This single fork explains combination sum vs combination sum II.
```

**That's it. Every problem. No exceptions.**

---

## Watch Me Apply This To The Grid Problem

```
STEP 1: choice = place '#' or leave '.' at cell (i, j)
STEP 2: state = (i, j) — current cell being decided
STEP 3: base case:
        paths == k  → true (found valid grid)
        paths < k   → false (this branch overshot, obstacles only reduce paths)
STEP 4: for each cell, try '.', recurse; try '#', recurse; undo
STEP 5: prune: start/end cell can never be '#', skip straight to next cell
STEP 6: no "reuse" concept here — always advances to next cell (i, j+1)
```

---

## The Decision Tree

```
Q1: What are we generating?

    ALL SUBSETS               → include/exclude each element (binary choice)
    ALL COMBINATIONS (size k)  → choose without order, advance index after pick
    ALL PERMUTATIONS           → choose without repetition, order matters, use "used[]"
    PARTITIONS (of string/array)→ choose a prefix cut point, recurse on remainder


Q2: Are there duplicate values in the input?

    NO duplicates    → no dedup logic needed
    YES duplicates   → SORT first, then at each level:
                       if i > start && nums[i] == nums[i-1] → skip
                       (this prevents the same value being chosen twice
                        at the SAME recursion depth, which would
                        produce duplicate subsets/combinations)


Q3: Can the same element be reused multiple times?

    YES (unlimited supply) → recurse with same index i (don't advance)
    NO  (each used once)   → recurse with index i+1 (advance past it)


Q4: What is the stopping / pruning condition?

    exact target sum       → remaining == 0 (found), remaining < 0 (prune)
    fixed length k          → path.size() == k (found)
    end of input            → index == n (done exploring this branch)
    invalid partial state   → validate as you go (e.g. N-Queens attack check,
                              word search boundary/visited check) and prune
                              immediately rather than building the full
                              invalid structure first


Q5: Is the "undo" step actually needed?

    Mutating shared state (path list, grid, visited[])
        → YES, must undo after recursing (backtrack)
    Passing new state by value each call (new list/string built fresh)
        → undo happens automatically, no explicit undo needed
```

---

## The Classic Problems Mapped To This Template

```
Grid with K paths        place/unplace '#', prune via path count → backtrack + dp check
Combination Sum          reuse allowed, advance only on skip      → same-index recursion
Combination Sum II       no reuse, has dups                       → sort + skip-dup + i+1
Subsets                  no dups, binary include/exclude          → simple i+1 recursion
Subsets II               has dups                                 → sort + skip-dup + i+1
Permutations             no dups, order matters                   → used[] array
Permutations II          has dups, order matters                   → sort + used[] + skip-dup
Word Search              grid, visited marking                     → mark/unmark on grid
Palindrome Partitioning  cut points, validate prefix                → prune non-palindrome prefix
N-Queens                 place queen per row, validate attacks      → prune invalid placement
Generate Parentheses     choice = '(' or ')' with count constraints → prune via open/close counts
```

---

## Watch Me Apply To 3 Leetcode Problems

### 39. Combination Sum
```
candidates can be reused unlimited times. Find all combos summing to target.

STEP 1: choice = include candidates[i] (can pick it again) or move to i+1
STEP 2: state = (start index, remaining target, current path)
STEP 3: base case: remaining == 0 → record path
                    remaining < 0  → prune (return)
STEP 4: sort candidates first (enables pruning)
        for i from start to n-1:
            if candidates[i] > remaining → break (rest are bigger too)
            path.add(candidates[i])
            recurse(i, remaining - candidates[i])   ← SAME i, reuse allowed
            path.removeLast()                        ← undo
STEP 6: reuse allowed → pass i (not i+1) into recursion
```

### 40. Combination Sum II
```
candidates CANNOT be reused, contains duplicates, each used once.
Find all unique combos summing to target.

STEP 1: choice = include candidates[i] or skip it, then always move to i+1
STEP 2: state = (start index, remaining target, current path)
STEP 3: base case: remaining == 0 → record path
                    remaining < 0  → prune
STEP 4/5/6: sort candidates first (for both pruning AND dedup)
        for i from start to n-1:
            if i > start && candidates[i] == candidates[i-1] → skip
                (this is the KEY line: prevents duplicate combos by only
                 letting the FIRST occurrence of a repeated value be
                 chosen at each recursion depth)
            if candidates[i] > remaining → break
            path.add(candidates[i])
            recurse(i+1, remaining - candidates[i])   ← i+1, no reuse
            path.removeLast()
```

### 90. Subsets II
```
nums contains duplicates. Find all unique subsets.

STEP 1: choice = include nums[i] (extend current subset) or don't
STEP 2: state = (start index, current path)
STEP 3: base case: NONE needed to "stop" — every path prefix is itself
                    a valid subset, so record path at EVERY call, then
                    keep exploring further (unlike combination sum where
                    only remaining==0 counts)
STEP 4/5/6: sort nums first (dedup requirement)
        record current path as one valid subset
        for i from start to n-1:
            if i > start && nums[i] == nums[i-1] → skip
                (same dedup trick: only first occurrence at this depth
                 can start a new branch)
            path.add(nums[i])
            recurse(i+1, path)     ← i+1, each element used once
            path.removeLast()
```

---

## The Muscle Memory Drill

Every time you see a "generate all X" or "find all valid Y" problem,
**immediately write these on paper:**

```
choice at each node     = ???   (include/exclude? pick a char? place a queen?)

state carried           = ???   (index, remaining target, path, visited[])

base case / goal        = ???   (when do I record an answer or prune?)

reuse or advance?        = ???   (same index i, or i+1?)

dedup needed?            = ???   (sort + skip i>start && a[i]==a[i-1]?)

undo step                = ???   (what exactly do I remove/unmark after recursing?)
```

Do this for 15 problems and it becomes a reflex.
The moment you see "generate all", "find all combinations/subsets/permutations",
or "construct any valid X", your hand automatically writes
`choice = ...`, `state = ...`, then sorts and recurses.

---

## The Trigger Signals Cheatsheet

```
SIGNAL IN PROBLEM                       → PATTERN
"return all possible combinations"      → backtrack, advance index (no reuse)
"unlimited use of each element"         → backtrack, same index (reuse allowed)
"contains duplicates, no dup results"   → sort + skip i>start && a[i]==a[i-1]
"return all subsets"                    → record at every node, i+1 recursion
"return all permutations"               → used[] array, order matters
"construct/build any valid grid/board"  → place choice, validate, undo if invalid
"partition string/array"                → try every cut point, validate prefix
"exactly k paths / exactly k ways"      → backtrack + count function to check == k
```

---

## Sister Problems To Solidify This Pattern

| Problem | Reuse allowed? | Has duplicates? | Key recursion move |
|---------|----------------|------------------|---------------------|
| LC 39 Combination Sum | Yes | No | same index i on include |
| LC 40 Combination Sum II | No | Yes | i+1, sort + skip-dup |
| LC 78 Subsets | No | No | record every node, i+1 |
| LC 90 Subsets II | No | Yes | record every node, sort + skip-dup |
| LC 46 Permutations | No | No | used[] array, all positions |
| LC 47 Permutations II | No | Yes | used[] + sort + skip-dup |
| **Grid With Exactly K Paths** | N/A (fixed cells) | N/A | place/unplace '#', dp-count to validate |

Master these 6 and you **own** the recursion + backtracking pattern forever. 🔱
