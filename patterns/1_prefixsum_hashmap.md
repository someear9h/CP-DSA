## The Universal Prefix Sum + HashMap Template

Every single problem in this pattern follows this exact flow:

found it on: https://leetcode.com/problems/stable-subarrays-with-equal-boundary-and-interior-sum/description/

```
STEP 1: Express the subarray condition using prefix sums
        condition on subarray[l..r] 
        → rewrite using prefix[r] and prefix[l]

STEP 2: Isolate prefix[l] on one side
        prefix[l] = f(prefix[r], current values at r)
                     ↑
                     this is your TARGET to query

STEP 3: Store (what you need to match at l) → in the map
        query (what target looks like) → from the map

STEP 4: Handle ordering (insert before or after query?)
        + any gap/length constraints (delayed insertion)
```

**That's it. Every problem. No exceptions.**

---

## Watch Me Apply This To Both Problems

### Problem 1: LC 1124 (Longest WPI)

```
STEP 1: condition = subarray sum > 0
        sum[l+1..r] = prefix[r] - prefix[l] > 0
        → prefix[r] > prefix[l]

STEP 2: isolate prefix[l]
        prefix[l] < prefix[r]
        → for minimum valid difference: prefix[l] = prefix[r] - 1
        (since scores change by ±1, smallest positive sum = 1)

STEP 3: STORE  → prefix[l] mapped to its earliest index
        QUERY  → does (prefix[r] - 1) exist in map?

STEP 4: store FIRST occurrence (maximize length)
        query BEFORE insert (don't use current index as l)
```

---

### Problem 2: This Problem (Stable Subarrays)

```
STEP 1: condition = capacity[l] = sum(l+1..r-1) AND capacity[l]=capacity[r]
        capacity[l] = prefix[r-1] - prefix[l]

STEP 2: isolate prefix[l]
        prefix[l] = prefix[r-1] - capacity[l]
        prefix[l] = prefix[r-1] - capacity[r]   ← since cap[l]=cap[r]
                    ↑
                    this is your TARGET

STEP 3: STORE  → (capacity[l], prefix[l]) mapped to count
        QUERY  → (capacity[r], prefix[r-1] - capacity[r])

STEP 4: delayed insert by 2 steps (length >= 3 constraint)
        query AFTER insert of l=r-2
```

---

## The Decision Tree For What To Store

```
After isolating prefix[l] = f(r):

Q1: Is there any OTHER condition on l besides prefix[l]?
    
    NO  → store just prefix[l]
          e.g. LC 560: prefix[l] = prefix[r] - k
               store map[prefix[l]] = count
    
    YES → store (prefix[l] AND the other condition) as combined key
          e.g. This problem: cap[l] must also equal cap[r]
               store map[{cap[l], prefix[l]}] = count


Q2: Do we want longest subarray or count of subarrays?

    COUNT   → store frequencies (map[key]++)
              add map[key] to answer at each r

    LONGEST → store first index where key appeared (map[key] = i)
              answer = max(answer, r - map[key])


Q3: Is there a minimum length constraint?

    NO  → insert current index immediately after querying
    YES → delayed insertion
          if min length = k, insert l = r-(k-1) at step r
          e.g. length >= 3 → insert l = r-2 at step r
```

---

## The Classic Problems Mapped To This Template

```
LC 303  prefix[r] - prefix[l] = query      → store prefix[l]
LC 560  prefix[r] - prefix[l] = k          → store prefix[l], query prefix[r]-k  
LC 525  prefix[r] - prefix[l] = 0          → store prefix[l], query prefix[r]
LC 974  (prefix[r] - prefix[l]) % k = 0    → store prefix[l]%k, query prefix[r]%k
LC 1124 prefix[r] - prefix[l] >= 1         → store first index of prefix[l]
THIS    prefix[l] = prefix[r-1] - cap[r]   → store {cap[l], prefix[l]}
  AND   cap[l] = cap[r]
```

---

## The Muscle Memory Drill

Next time you see a subarray problem, **immediately write these 3 lines on paper:**

```
sum[l..r]   = prefix[r] - prefix[l]   (or prefix[r+1] - prefix[l])
condition   = ??? (translate problem into this)
prefix[l]   = ??? (isolate it)
             ↑ THIS IS WHAT YOU STORE AND QUERY
```

If you do this drill for 15-20 problems, it becomes a reflex. You won't even think about it. You'll just see "subarray condition" and your hand will automatically write those 3 lines. 