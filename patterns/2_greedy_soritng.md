## The Universal Greedy + Sorting Template

Every problem in this pattern follows this exact flow:

found it on: https://codeforces.com/problemset/problem/1729/D

```
STEP 1: Reduce to a per-element value
        Transform the problem so each element has a single number
        that captures its "contribution" to any group it joins.
        
        e.g. friends problem: contribution = y[i] - x[i] (surplus/deficit)
        Ask: "what single number represents how this element
              helps or hurts a group?"

STEP 2: Restate the group condition using that value
        Original condition on group → condition on SUM of contributions
        
        e.g. sum(y) >= sum(x)  →  sum(y[i]-x[i]) >= 0
        Ask: "what does a valid group look like in terms of
              the sum of my transformed values?"

STEP 3: Sort and apply the right greedy strategy
        
        MAXIMIZE groups/pairs:
        → Sort, then Two Pointers (lo=0, hi=n-1)
        → pair most negative with most positive
        → if pair valid → count it, lo++, hi--
        → if not → discard most negative (lo++)
        
        MINIMIZE cost / MAXIMIZE value in one group:
        → Sort, then scan greedily
        → take elements in order that satisfies condition earliest

STEP 4: Prove greedy is safe (exchange argument)
        Ask: "if I swap two elements between groups,
              does the answer get better or worse?"
        If swapping can never improve → greedy is correct
```

**That's it. Every problem. No exceptions.**

---

## Watch Me Apply This To The Friends Problem

```
STEP 1: contribution[i] = y[i] - x[i]
        positive = this person can subsidize others
        negative = this person needs help from others

STEP 2: group is valid if sum(contribution) >= 0
        + group size >= 2

STEP 3: sort ascending, two pointers
        pair most deficit (lo) with most surplus (hi)
        if diff[lo] + diff[hi] >= 0 → valid pair, lo++, hi--
        else → hi can't save lo even at its best → discard lo, lo++

STEP 4: exchange argument
        if lo pairs with someone other than hi, the sum can only
        be worse (hi is the best possible partner for lo).
        so always pair lo with hi first. greedy is safe. ✅
```

---

## The Decision Tree

```
After computing per-element contribution value:

Q1: What is the group size constraint?
    
    EXACTLY 2 (pairs)     → two pointers on sorted array
    AT LEAST 2            → two pointers still works (each pointer
                            move = 1 person, count groups of 2+)
    EXACTLY k             → sliding window of size k on sorted array
    ANY size              → greedy scan, take greedily until condition met


Q2: What are we optimizing?
    
    MAXIMIZE number of groups → two pointers, count valid pairs
    MINIMIZE group size       → sort, two pointers, shrink from inside
    MAXIMIZE total value      → sort descending, greedy take


Q3: What is the validity condition on a group?
    
    sum >= 0              → pair most negative with most positive
    sum <= k              → pair smallest with largest
    product >= k          → sort, think log space
    all elements equal    → sort, count frequencies


Q4: Is the greedy safe? (always ask this)
    
    Prove via exchange argument:
    "if I swap element A (in group 1) with element B (in group 2),
     does total answer improve?"
    If NO → greedy order is optimal
```

---

## The Classic Problems Mapped To This Template

```
Friends restaurant    diff=y-x, sum>=0, pairs    → sort asc, two pointers
LC 455  Assign Cookies   greedily satisfy child  → sort both, two pointers
LC 881  Rescue Boats      sum<=limit, pairs       → sort asc, two pointers
LC 2410 Match Players     diff<=k, pairs          → sort, two pointers
LC 1877 Minimize Max Pair sum<=target, pairs      → sort, two pointers
LC 976  Largest Perimeter pick 3, a+b>c           → sort desc, greedy triple
LC 1029 Two City Scheduling  split n people 2 cities min cost → sort by diff
LC 406  Queue Reconstruction sort by height desc, insert by index
```

---

## Watch Me Apply To 3 Leetcode Problems

### LC 881: Rescue Boats
```
Each boat carries at most 2 people, weight limit = limit.
Maximize boats used (minimize boats needed).

STEP 1: contribution = weight[i] (no transform needed)
STEP 2: valid pair = weight[lo] + weight[hi] <= limit
STEP 3: sort asc, two pointers
        if weight[lo]+weight[hi] <= limit → pair them, lo++,hi--
        else → hi goes alone (heaviest can't pair), hi--
STEP 4: exchange argument → heaviest person either pairs with
        lightest or goes alone. pairing with anyone heavier only
        makes it worse. ✅
```

### LC 2410: Match Players With Trainers
```
Each player needs a trainer. Match player[i] with trainer[j]
if player[i] <= trainer[j]. Maximize matches.

STEP 1: values are player[i] and trainer[j] directly
STEP 2: valid match = player[i] <= trainer[j]
STEP 3: sort both asc, two pointers
        if player[lo] <= trainer[hi] → match, lo++, hi--
        else → trainer[hi] can't satisfy weakest unmatched player
               discard trainer (hi--)... 

        Actually: sort both asc, use greedy forward scan
        for each trainer (sorted), match with smallest
        unmatched player that fits. lo pointer on players.
STEP 4: always satisfy weakest remaining player with
        cheapest capable trainer. ✅
```

### LC 1029: Two City Scheduling
```
Send n people to city A, n to city B.
cost[i] = [costA[i], costB[i]]. Minimize total cost.

STEP 1: contribution = costA[i] - costB[i]
        (how much EXTRA it costs to send person i to A vs B)
STEP 2: start by sending everyone to B.
        to send person i to A instead: pay extra diff[i]
        pick the n people with SMALLEST diff (cheapest to redirect to A)
STEP 3: sort by diff = costA-costB ascending
        first n people → send to A
        last n people  → send to B
STEP 4: exchange argument → swapping any A-person with any
        B-person can only increase cost since we already
        picked minimum extra-cost people for A. ✅
```

---

## The Muscle Memory Drill

Every time you see a grouping/pairing/matching problem, **immediately write these on paper:**

```
contribution[i] = ???     (what single value represents each element?)

group condition = ???     (restate using sum/diff of contributions)

strategy = ???            (two pointers? greedy scan? sort which way?)

exchange argument = ???   (why can't swapping improve the answer?)
```

Do this for 15 problems and it becomes a reflex.
The moment you see "group", "pair", "match", "split" your hand
automatically writes `contribution[i] = ...` and sorts.

---

## The Trigger Signals Cheatsheet

```
SIGNAL IN PROBLEM                    → PATTERN
"pair elements, condition on sum"    → sort + two pointers
"split into groups, minimize cost"   → sort by difference
"match A with B, maximize matches"   → sort both, greedy forward
"k elements, condition on sum/max"   → sort + sliding window
"condition on largest + smallest"    → sort + two pointers
"each element used at most once"     → sort first, always
"exchange one element between groups"→ sort by marginal cost diff
```

---

## Sister Problems To Solidify This Pattern

| Problem | What to sort by | Greedy move |
|---------|----------------|-------------|
| LC 881 Rescue Boats | weight asc | pair lightest+heaviest if fits |
| LC 455 Assign Cookies | both asc | match smallest cookie to smallest child |
| LC 2410 Match Players | both asc | match weakest player to cheapest capable trainer |
| LC 1029 Two City | costA-costB | send cheapest-to-redirect to city A |
| LC 976 Largest Perimeter | desc | check consecutive triple a[i]+a[i+1]>a[i+2] |
| **Friends Restaurant** | diff asc | pair most deficit with most surplus |

Master these 6 and you **own** the greedy sorting pattern forever. 🔱