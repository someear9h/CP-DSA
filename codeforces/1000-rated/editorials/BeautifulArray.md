### 1. The Problem Decoded
You are tasked with building an array of $n$ non-negative integers. You must satisfy two strict conditions:
1.  **The Sum Condition:** The sum of all elements in the array must exactly equal $s$.
2.  **The Beauty Condition (The Quotient Sum):** If you take every element, divide it by $k$, and round down to the nearest integer (floor division), the sum of all those quotients must exactly equal $b$. 

Mathematically: $\sum \lfloor A_i / k \rfloor = b$.

### 2. The Intuition & Observations (The Matrix)
The key to this problem is understanding how **integer division (modulo math)** hides value.

**Observation 1: The "Hidden" Remainder**
If $k = 5$, and I want the integer division to equal $2$, the minimum number I can pick is $10$ ($10 / 5 = 2$). 
But I can also pick $11, 12, 13$, or $14$. All of them divided by $5$ still equal $2$! 
* **The Rule:** Every number can hold up to $k - 1$ "hidden" value without changing its quotient. 

**Observation 2: The Absolute Minimum**
If the sum of all quotients must be $b$, the absolute cheapest way to achieve this is to just multiply $b$ by $k$. 
* **Formula:** `minimum_s = k * b`
* *Example:* If $k=5$ and $b=2$, the minimum sum is $10$. If your target sum $s$ is $8$, it is mathematically impossible. You output `-1`.

**Observation 3: The Absolute Maximum**
What is the absolute highest sum we can reach without accidentally increasing the quotient sum $b$? 
We start with the minimum ($k \cdot b$) and then we stuff every single one of the $n$ elements with the maximum "hidden" remainder possible, which is $k - 1$.
* **Formula:** `maximum_s = (k * b) + n * (k - 1)`
* *Example:* If $n=3$, $k=5$, $b=2$. The minimum is $10$. We can add up to $4$ to all three elements. $10 + (3 \times 4) = 22$. If your target sum $s$ is $25$, it is mathematically impossible. You output `-1`.

### 3. The Approach (Greedy Construction)
If the target sum $s$ falls between our minimum and maximum boundaries, a valid array is 100% guaranteed to exist. We just have to build it greedily.

1.  **Satisfy the Quotient Immediately:** Give the entire required base value ($k \cdot b$) to the very first element: `ans[0] = k * b`. Now, our quotient condition is permanently solved!
2.  **Calculate the Leftover:** Subtract that base value from our target sum to see how much "hidden remainder" we still need to distribute: `s -= minimum_s`.
3.  **Distribute Greedily:** Loop through the array. Pour as much of the leftover sum $s$ as you legally can into the current element. The legal limit is $k - 1$. 

### 4. Dry Run (Visualizing the Engine)
Let's trace a test case: `n = 3`, `k = 5`, `b = 2`, `s = 16`.

**Step 1: Check Boundaries**
* `minimum_s` = $5 \times 2 = 10$.
* `maximum_s` = $10 + 3 \times (5 - 1) = 10 + 12 = 22$.
* Is $16$ between $10$ and $22$? Yes. We proceed.

**Step 2: Initialize & Satisfy Quotient**
* `ans = [0, 0, 0]`
* `ans[0] = minimum_s` $\rightarrow$ `ans[0] = 10`. 
* Leftover `s` = $16 - 10 = 6$.

**Step 3: Greedy Distribution**
* **Loop `i = 0`:** We can add $\min(k - 1, s) \rightarrow \min(4, 6) = 4$.
    * `ans[0] = 10 + 4 = 14`.
    * Leftover `s = 6 - 4 = 2`.
* **Loop `i = 1`:** We can add $\min(4, 2) = 2$.
    * `ans[1] = 0 + 2 = 2`.
    * Leftover `s = 2 - 2 = 0`.
* **Loop `i = 2`:** Leftover `s` is $0$. Add $\min(4, 0) = 0$.
    * `ans[2] = 0`.

**Final Output:** `14 2 0`.
Let's verify the math:
* Sum: $14 + 2 + 0 = 16$. (Matches $s$).
* Quotients: $\lfloor 14/5 \rfloor + \lfloor 2/5 \rfloor + \lfloor 0/5 \rfloor \rightarrow 2 + 0 + 0 = 2$. (Matches $b$). 
* **Flawless execution.**

### 5. Complexity Analysis
* **Time Complexity (TC):** $O(N)$. We do a single pass over the array of size $N$ to distribute the remainder, and another pass to print it.
* **Space Complexity (SC):** $O(N)$. We create a vector `ans` of size $N$ to store the constructed array.

### 6. The PRO Guide: Master the "Constructive Algorithm" Pattern
This problem falls under the **Greedy Constructive Math** pattern. 

When you see a Codeforces problem asking you to "construct an array" or "find any valid sequence" that meets specific math conditions:
1.  **Never simulate or guess.**
2.  **Find the absolute boundaries:** Calculate the absolute mathematical minimum and absolute mathematical maximum that the rules allow.
3.  **Check for existence:** If the target is outside those bounds, print `-1`.
4.  **Be aggressively greedy:** Don't try to balance the array evenly. Stuff everything into the first index until it hits its legal limit, then spill over to the next index. This prevents complex edge cases.