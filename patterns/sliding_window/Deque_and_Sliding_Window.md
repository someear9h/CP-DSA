## Pattern: Deque and Sliding Window (Monotonic Queue)

Every problem in this pattern follows this exact flow:

found it on: LC 239 Sliding Window Maximum, LC 1438 Longest Subarray Abs Diff <= Limit,
             LC 1696 Jump Game VI, LC 862 Shortest Subarray Sum >= K,
             LC 1499 Max Value of Equation, Count Subarrays With Cost <= K

```
STEP 1: Identify the "range query inside a moving window" smell.
        
        You need to answer MIN or MAX (or best DP state) over a
        WINDOW that is either sliding or shrinking. Brute force
        recomputes from scratch each time → O(N·K). Too slow.
        
        Ask: "for each position, am I querying the best value
              inside some recent range [l..r]?"

STEP 2: Decide what goes IN the deque.
        
        The deque stores INDICES (never raw values).
        Storing indices lets you:
            (a) check staleness: if deque.front() < l → expired, pop it
            (b) look up the value: arr[deque.front()] is the current best
        
        Ask: "what array am I querying? (nums? dp? prefix?)"
             "index into THAT array is what the deque stores."

STEP 3: Choose your monotonicity direction.
        
        DECREASING deque  →  front = current MAXIMUM
            Maintain: before pushing i, pop from BACK while arr[back] <= arr[i]
            (a smaller value behind a larger value is forever useless)
        
        INCREASING deque  →  front = current MINIMUM
            Maintain: before pushing i, pop from BACK while arr[back] >= arr[i]
            (a larger value behind a smaller value is forever useless)
        
        Ask: "do I need the max or min of the window?"

STEP 4: Handle window expiry at the FRONT.
        
        Before reading the answer, check if the front is stale:
            while deque is not empty AND deque.front() < l:
                deque.pollFront()
        
        The window left boundary l advances when:
            Fixed window:   l = r - k + 1  (evict when index < l)
            Variable window: l advances until the constraint is satisfied
        
        Ask: "under what condition does an old index become invalid?"

STEP 5: Read the answer from the FRONT and push the new index at the BACK.
        
        Order within each iteration:
            1. Evict stale front (if index < l)
            2. Read answer: deque.peekFront() is the best valid index
            3. Maintain monotonicity: pop back while invariant violated
            4. Push current index i to the back
        
        NOTE: steps 2 and 3-4 sometimes swap order depending on
        whether the current index i is INCLUDED in the current query.
        
        Ask: "should the current index i be part of the answer for
              THIS iteration, or only for future iterations?"
```

**That's it. Every "best value in a moving range" problem. No exceptions.**

---

## The Deque Template: One Mental Model For All Problems

```
                         BACK (new arrivals)
                              ↓
    ┌─────────────────────────────────────────────────────┐
    │  BEST  ←──── strictly monotone ────→  most recent  │
    └─────────────────────────────────────────────────────┘
         ↑
        FRONT (answers)

PUSH RULE   (maintain monotonicity, runs at the BACK):
    while back value is WORSE than the new value:
        pop from back
    push new index to back

POP RULE    (handle expiry, runs at the FRONT):
    while front index is outside the current window:
        pop from front

READ RULE   (answer query):
    front of deque = index of current best value
```

---

## The Master Matrix: All 5 Problems On One Page

```
╔══════════════════════╦═══════════════════╦════════════════╦══════════════════════════════════════════╗
║ PROBLEM              ║ DEQUE STORES      ║ DIRECTION      ║ WHAT GOES IN, WHAT COMES OUT             ║
╠══════════════════════╬═══════════════════╬════════════════╬══════════════════════════════════════════╣
║ LC 239               ║ indices of nums[] ║ DECREASING     ║ push r, pop front when < l=r-k+1         ║
║ Sliding Window Max   ║                   ║ (max at front) ║ answer = nums[front] each step            ║
╠══════════════════════╬═══════════════════╬════════════════╬══════════════════════════════════════════╣
║ LC 1438              ║ indices of nums[] ║ TWO DEQUES     ║ maxD (decreasing) + minD (increasing)     ║
║ Abs Diff <= Limit    ║ (max AND min)     ║                ║ shrink l while max-min > limit            ║
╠══════════════════════╬═══════════════════╬════════════════╬══════════════════════════════════════════╣
║ LC 1696              ║ indices of dp[]   ║ DECREASING     ║ push i after computing dp[i]              ║
║ Jump Game VI         ║                   ║ (max dp)       ║ dp[i] = nums[i] + dp[front]               ║
╠══════════════════════╬═══════════════════╬════════════════╬══════════════════════════════════════════╣
║ LC 862               ║ indices of pre[]  ║ INCREASING     ║ push i when pre[i] is new min prefix      ║
║ Shortest Sum >= K    ║ (prefix sums)     ║ (min at front) ║ shrink front while pre[r]-pre[front] >= K ║
╠══════════════════════╬═══════════════════╬════════════════╬══════════════════════════════════════════╣
║ LC 1499              ║ indices of pts[]  ║ DECREASING     ║ store yi-xi, pop front when xj-xi > k     ║
║ Max Value Equation   ║ (transformed val) ║ (max yi-xi)    ║ answer candidate = yj+xj + (yi-xi front)  ║
╚══════════════════════╩═══════════════════╩════════════════╩══════════════════════════════════════════╝
```

---

## Watch Me Apply The Template To Each Problem

### LC 239: Sliding Window Maximum (The Foundation)
```
nums = [1,3,-1,-3,5,3,6,7],  k=3

DEQUE: stores indices, front = index of current MAX (decreasing order)
WINDOW: fixed size k=3, evict when index < r-k+1

r=0: push 0               deque=[0]        window=[1]       ans: -
r=1: pop 0 (1<3), push 1  deque=[1]        window=[1,3]     ans: -
r=2: push 2               deque=[1,2]      window=[1,3,-1]  ans: nums[1]=3
r=3: push 3               deque=[1,2,3]    window=[3,-1,-3] ans: nums[1]=3
r=4: front=1 stale (1<2), pop. pop 2,3 (-1<5,-3<5), push 4
                          deque=[4]        window=[-1,-3,5] ans: nums[4]=5
r=5: push 5               deque=[4,5]      window=[-3,5,3]  ans: nums[4]=5
r=6: pop 5 (3<6),pop 4(5<6),push 6 deque=[6]  window=[5,3,6]  ans: nums[6]=6
r=7: pop 6 (6<7), push 7  deque=[7]        window=[3,6,7]   ans: nums[7]=7

Output: [3,3,5,5,6,7]  ✅

PUSH INVARIANT: "a smaller element that arrived earlier than me
will NEVER be the max while I am still in the window."
Pop it — it is permanently useless.
```

### LC 1438: Longest Subarray Abs Diff <= Limit
```
nums = [8,2,4,7],  limit=4

TWO DEQUES: maxD (decreasing, front=max), minD (increasing, front=min)
VARIABLE WINDOW: shrink l while maxD.front - minD.front > limit

r=0: push 0 to both. l=0. max=8,min=8. diff=0 ≤ 4. len=1
r=1: push 1. maxD=[0,1]→pop 1? 2<8, keep. maxD=[0],minD=[1]. max=8,min=2. diff=6>4
     shrink: l=1. pop stale front of maxD(idx0<1). maxD=[1],minD=[1]. diff=0≤4. len=1
r=2: push 2. maxD: 4<8? no, keep. maxD=[1,2]. minD: 4>2? pop 2? no. minD=[1,2].
     max=8? wait maxD front=1→nums[1]=2. min=minD front=1→nums[1]=2. diff=0. len=2
r=3: push 3. maxD: pop back while nums[back]<=7: pop 2(4≤7),pop 1(2≤7). maxD=[3].
     minD: pop back while nums[back]>=7: pop 2(4<7 no). minD=[1,2,3].
     max=7,min=2. diff=5>4. shrink: l=2. minD front=1<2 pop. minD=[2,3]. min=4.
     diff=7-4=3≤4. len=2.

Output: 2  ✅

KEY: two deques working simultaneously. The window shrinks from the LEFT
whenever EITHER deque's spread exceeds the limit.
```

### LC 1696: Jump Game VI (DP + Deque Crossover)
```
nums = [1,-1,-2,4,-7,3],  k=2

dp[i] = max score to reach index i
dp[i] = nums[i] + max(dp[i-k] ... dp[i-1])
DEQUE: stores indices of dp[], front = index of max dp (decreasing)

dp[0]=1. push 0. deque=[0]

i=1: evict front if < i-k = -1 → none stale.
     dp[1] = nums[1] + dp[deque.front()] = -1 + dp[0] = -1 + 1 = 0
     maintain: pop back while dp[back]<=dp[1]=0: pop 0(dp[0]=1>0 no). push 1.
     deque=[0,1]

i=2: evict front if < 0 → none. dp[2] = -2 + dp[0] = -1.
     maintain: pop while dp[back]<=-1: pop 1(dp[1]=0>-1 no). push 2. deque=[0,1,2]

i=3: evict front if < 1 → pop 0. dp[3] = 4 + dp[1] = 4+0 = 4.
     maintain: pop while dp[back]<=4: pop 2(-1≤4),pop 1(0≤4). push 3. deque=[3]

... continue ...

Output: 3  ✅

THE INSIGHT: the deque stores dp indices, not nums indices.
The window is [i-k .. i-1] (the reachable previous positions).
Evict when front < i-k (too far back to jump from).
```

### LC 862: Shortest Subarray With Sum >= K (The Prefix Sum Trap)
```
nums = [2,-1,2],  k=3

WHY NORMAL SLIDING WINDOW FAILS:
    negative numbers mean "expanding right" can DECREASE the sum.
    The window is no longer monotone → cannot just move l rightward.

THE GOD MOVE: compute prefix sums, then deque on prefix array.
pre = [0, 2, 1, 3]  (pre[r] - pre[l] = sum of nums[l..r-1])

DEQUE: stores indices of pre[], increasing order (front = smallest prefix)
Goal: find shortest r-l where pre[r] - pre[l] >= k

l=0: push 0.                 deque=[0]
r=1: pre[1]=2. 2-pre[0]=2<3. push 1 (2>0, keep). deque=[0,1]
r=2: pre[2]=1. 1-pre[0]=1<3. maintain: pop back while pre[back]>=1: pop 1(2>=1). push 2.
     deque=[0,2]
r=3: pre[3]=3. 3-pre[0]=3>=3 → len=3-0=3, best=3. pop front. 3-pre[2]=3-1=2<3. push 3.
     deque=[2,3]

Output: 3  ✅

TWO SEPARATE ROLES of the deque here:
    FRONT: greedily find the farthest-left l where pre[r]-pre[l]>=k
           (pop front while the condition holds — shorter subarray is better)
    BACK:  maintain increasing order so future r values can still use this l
           (pop back when a larger prefix won't help future queries)
```

### LC 1499: Max Value of Equation (Math Disguise)
```
points = [[1,3],[2,5],[3,10],[4,3]], k=1

ORIGINAL: maximize yi + yj + |xi - xj| for xi < xj, xj-xi <= k
REWRITE:  since xi < xj: |xi-xj| = xj-xi
          yi + yj + xj - xi  =  (yj + xj) + (yi - xi)

INSIGHT: as we iterate j (right point), we need max(yi-xi) over
         recent i where xj-xi <= k.  That's a sliding window max!

DEQUE: stores indices of points[], decreasing by yi-xi (front = best yi-xi)
EVICT: when xj - x[front] > k  (too far away)

j=0: push 0. deque=[0]
j=1: xj=2. evict front? 2-1=1<=1 ok. candidate=(5+2)+(3-1)=9. best=9.
     maintain: pop back while (y-x)[back] <= (y-x)[1]=5-2=3: pop 0(3-1=2<=3). push 1. deque=[1]
j=2: xj=3. evict? 3-2=1<=1 ok. candidate=(10+3)+(5-2)=16. best=16.
     maintain: pop back while (y-x)[back]<=(y-x)[2]=10-3=7: pop 1(3<=7). push 2. deque=[2]
j=3: xj=4. evict? 4-3=1<=1 ok. candidate=(3+4)+(10-3)=14. best=16.
     maintain: pop back while (y-x)[back]<=(y-x)[3]=3-4=-1: none. push 3. deque=[2,3]

Output: 16  ✅

THE DISGUISE PATTERN: whenever you see a "maximize f(i)+g(j)" problem
with a distance constraint, rewrite it as "sliding window max of f(i)
as j advances." The deque handles the window, algebra handles the split.
```

---

## The Decision Tree

```
After identifying a "best value in a range" problem:

Q1: What array does the deque index into?
    
    nums[] directly         → standard sliding window max/min
    dp[] array              → DP optimization (Jump Game VI style)
    prefix sum array        → negative-number sum problems (LC 862)
    transformed value f(i)  → math rewrite first, then deque


Q2: Do I need MAX, MIN, or both?
    
    MAX only  → one DECREASING deque
    MIN only  → one INCREASING deque
    BOTH      → two deques simultaneously (LC 1438)


Q3: Is the window fixed-size or variable?
    
    FIXED size k        → evict front when index < r - k + 1
    VARIABLE (grow/shrink) → evict front while constraint violated
                             advance l to restore invariant


Q4: Is the current index i included in the answer for THIS step?
    
    YES (i is part of the window for its own query):
        → read front BEFORE pushing i
        → e.g. LC 1696: dp[i] uses deque THEN pushes i
    
    NO (i contributes only to FUTURE steps):
        → push i BEFORE reading (or push AFTER if i opens new window)
        → e.g. LC 239: push i, then read for window ending at i


Q5: Is there a math disguise?
    
    "maximize f(i) + g(j) subject to |xi-xj| <= k"
        → rewrite as g(j) + max(f(i)) over window → deque on f(i)
    
    "minimize length of subarray with sum >= k, with negatives"
        → prefix sums + monotone deque on prefix array
    
    Signal: problem looks like geometry or algebra, not arrays
        → try algebraic rewrite to expose "max over recent window"
```

---

## The Muscle Memory Drill

Every time you see "best value in a sliding or variable window," **immediately write these on paper:**

```
deque stores  = indices of ???  (nums? dp? prefix? transformed val?)

direction     = DECREASING (need MAX) or INCREASING (need MIN)

evict front when = ???  (index < r-k+1? constraint violated?)

maintain back when = ???  (pop while arr[back] <= arr[i] for MAX deque)

read answer at = ???  (before or after pushing i?)

math rewrite? = ???  (is there a f(i)+g(j) split hiding here?)
```

Do this for 10 problems and it becomes a reflex.
The moment you see "maximum/minimum in a sliding range" your hand
automatically draws the deque box, labels FRONT=BEST and BACK=NEWEST,
and asks "what array am I indexing into?"

---

## The Trigger Signals Cheatsheet

```
SIGNAL IN PROBLEM                               → PATTERN
"maximum element in every window of size k"     → single decreasing deque
"longest subarray where max - min <= limit"     → two deques simultaneously
"reach end with max score, jump at most k"      → DP + deque optimization
"shortest subarray with sum >= k"               → prefix sums + deque
  (array has negative numbers)
"maximize yi+yj+|xi-xj| with distance limit"   → algebraic rewrite + deque
"O(N·K) DP is too slow"                        → deque reduces inner max to O(1)
"find max/min in last k elements, repeatedly"   → deque over TreeMap/PQ
"constraint on window size AND window content"  → deque + two-pointer shrink
```

---

## The Core Code Templates (Java)

### Template 1 — Sliding Window Maximum (Fixed Window)
```java
// Time: O(n)  Space: O(k)
public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] ans = new int[n - k + 1];
    Deque<Integer> dq = new ArrayDeque<>();  // stores indices, decreasing values

    for (int r = 0; r < n; r++) {
        // 1. Evict stale front (outside window)
        while (!dq.isEmpty() && dq.peekFirst() < r - k + 1)
            dq.pollFirst();

        // 2. Maintain decreasing order (pop worse candidates from back)
        while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[r])
            dq.pollLast();

        // 3. Push current index
        dq.addLast(r);

        // 4. Record answer once window is full
        if (r >= k - 1)
            ans[r - k + 1] = nums[dq.peekFirst()];
    }
    return ans;
}
```

### Template 2 — Two Deques (Variable Window, Max-Min Constraint)
```java
// Time: O(n)  Space: O(n)
public int longestSubarray(int[] nums, int limit) {
    Deque<Integer> maxD = new ArrayDeque<>();  // decreasing → front = max
    Deque<Integer> minD = new ArrayDeque<>();  // increasing → front = min
    int l = 0, ans = 0;

    for (int r = 0; r < nums.length; r++) {
        // Maintain maxD
        while (!maxD.isEmpty() && nums[maxD.peekLast()] <= nums[r]) maxD.pollLast();
        maxD.addLast(r);

        // Maintain minD
        while (!minD.isEmpty() && nums[minD.peekLast()] >= nums[r]) minD.pollLast();
        minD.addLast(r);

        // Shrink window from left while constraint violated
        while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > limit) {
            l++;
            if (maxD.peekFirst() < l) maxD.pollFirst();
            if (minD.peekFirst() < l) minD.pollFirst();
        }

        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

### Template 3 — DP + Deque Optimization
```java
// Time: O(n)  Space: O(n)
public int maxResult(int[] nums, int k) {
    int n = nums.length;
    int[] dp = new int[n];
    dp[0] = nums[0];
    Deque<Integer> dq = new ArrayDeque<>();  // stores dp indices, decreasing dp values
    dq.addLast(0);

    for (int i = 1; i < n; i++) {
        // Evict front outside jump range
        while (dq.peekFirst() < i - k) dq.pollFirst();

        // Current best DP value
        dp[i] = nums[i] + dp[dq.peekFirst()];

        // Maintain decreasing deque on dp[]
        while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]) dq.pollLast();
        dq.addLast(i);
    }
    return dp[n - 1];
}
```

### Template 4 — Prefix Sum + Deque (Negative Numbers)
```java
// Time: O(n)  Space: O(n)
public int shortestSubarray(int[] nums, int k) {
    int n = nums.length;
    long[] pre = new long[n + 1];
    for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];

    Deque<Integer> dq = new ArrayDeque<>();  // stores indices of pre[], INCREASING
    int ans = n + 1;

    for (int r = 0; r <= n; r++) {
        // Greedily find shortest valid subarray ending before r
        while (!dq.isEmpty() && pre[r] - pre[dq.peekFirst()] >= k) {
            ans = Math.min(ans, r - dq.pollFirst());
        }
        // Maintain increasing prefix sums (pop larger prefixes — they're worse starts)
        while (!dq.isEmpty() && pre[dq.peekLast()] >= pre[r]) dq.pollLast();
        dq.addLast(r);
    }
    return ans == n + 1 ? -1 : ans;
}
```

### Template 5 — Math Rewrite + Deque (Equation Problems)
```java
// Time: O(n)  Space: O(n)
public long findMaxValueOfEquation(int[][] points, int k) {
    Deque<Integer> dq = new ArrayDeque<>();  // stores indices, decreasing yi-xi
    long ans = Long.MIN_VALUE;

    for (int j = 0; j < points.length; j++) {
        long xj = points[j][0], yj = points[j][1];

        // Evict front outside distance window
        while (!dq.isEmpty() && xj - points[dq.peekFirst()][0] > k) dq.pollFirst();

        // Read best (yi-xi) from front, compute candidate answer
        if (!dq.isEmpty()) {
            int i = dq.peekFirst();
            ans = Math.max(ans, yj + xj + points[i][1] - points[i][0]);
        }

        // Maintain decreasing deque on (yi-xi)
        while (!dq.isEmpty() &&
               points[dq.peekLast()][1] - points[dq.peekLast()][0] <= yj - xj)
            dq.pollLast();
        dq.addLast(j);
    }
    return ans;
}
```

---

## Why The Deque Beats TreeMap and Priority Queue

```
╔══════════════════════╦════════════════╦════════════════════════════════════════╗
║ DATA STRUCTURE       ║ COMPLEXITY     ║ WHY IT LOSES TO DEQUE                  ║
╠══════════════════════╬════════════════╬════════════════════════════════════════╣
║ Brute force (re-scan)║ O(N·K)         ║ recomputes max from scratch each window ║
║ Priority Queue (heap)║ O(N log N)     ║ can't evict stale entries in O(1)       ║
║ TreeMap              ║ O(N log N)     ║ O(log N) per insert/delete              ║
║ Monotonic Deque      ║ O(N) total     ║ each index pushed AND popped at most 1x ║
╚══════════════════════╩════════════════╩════════════════════════════════════════╝

THE AMORTIZED ARGUMENT:
Every index is pushed into the deque exactly ONCE and
popped from the deque at most ONCE (from either end).
Total push + pop operations across the entire algorithm = 2N.
Therefore: O(N) total, regardless of window size k.

This is why the deque beats the heap even though both handle
"max in a dynamic set." The deque exploits the sequential
access pattern that heaps cannot use.
```

---

## Common Mistakes & How To Avoid Them

```
MISTAKE 1: Storing VALUES instead of INDICES in the deque
    → you lose the ability to check staleness (is this index still in window?)
    → ALWAYS store indices; look up values via arr[dq.peekFirst()]

MISTAKE 2: Wrong order — reading answer AFTER pushing i (or vice versa)
    → for DP problems: read dp[front] FIRST, compute dp[i], THEN push i
      (i should not be a candidate for its own dp[i] computation)
    → for window problems: push i, then read (i IS part of the current window)
    → always ask: "is the current element part of the query for this step?"

MISTAKE 3: Forgetting to evict the front BEFORE reading the answer
    → stale front gives wrong max/min from outside the valid window
    → front eviction must come BEFORE the answer read in every iteration

MISTAKE 4: Using the wrong comparison in the back-maintenance step
    → for MAX deque: pop back while arr[back] <= arr[i]  (strict ≤, not <)
      (keeping equal values wastes space and can cause wrong answers
       when the correct max appears multiple times)
    → for MIN deque: pop back while arr[back] >= arr[i]

MISTAKE 5: Applying standard sliding window to arrays with negatives
    → standard two-pointer window assumes "expanding right always increases sum"
    → one negative element breaks this monotonicity
    → SIGNAL: if array has negatives AND sum/length constraint → prefix + deque (LC 862)

MISTAKE 6: Not recognizing the math disguise
    → "maximize f(i) + g(j)" with a distance constraint is ALWAYS a deque problem
    → the algebra split separates the "i part" (goes into deque) from the
      "j part" (read at each step)
    → if the problem has two indices and a |xi-xj| constraint → try algebraic rewrite first

MISTAKE 7: Using ArrayDeque but calling the wrong end methods
    → addLast / pollLast  →  BACK  (new arrivals, maintenance)
    → peekFirst / pollFirst →  FRONT (answers, eviction)
    → mixing these up silently reverses your monotone ordering
```

---

## Sister Problems To Solidify This Pattern

| Problem | Deque indexes | Direction | Key Insight |
|---------|--------------|-----------|-------------|
| **LC 239 Sliding Window Max** | nums[] | DECREASING | foundation; fixed window k |
| **LC 1438 Abs Diff <= Limit** | nums[] | TWO deques | max-min spread → shrink left |
| **LC 1696 Jump Game VI** | dp[] | DECREASING | dp optimization; window=[i-k..i-1] |
| **LC 862 Shortest Sum >= K** | prefix[] | INCREASING | negatives break normal window |
| **LC 1499 Max Equation** | points[] (yi-xi) | DECREASING | algebraic split exposes window max |
| LC 918 Max Circular Subarray | prefix[] | DECREASING | circular handled by prefix trick |
| LC 2762 Continuous Subarrays | nums[] | TWO deques | count subarrays, not just length |
| LC 1425 Constrained Subsequence | dp[] | DECREASING | exact LC 1696 template |

Master the first five and you **own** the Monotonic Deque pattern forever. 🔱