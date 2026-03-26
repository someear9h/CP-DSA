Step back into the dojo.

You have brought me a beautiful math problem from Codeforces. It looks like an array permutation problem, but it is entirely a puzzle about divisibility and set theory.

### Part 2: The Grandmaster Intuition

**The Objective:** Maximize the score.
Score = (Sum of elements at indices divisible by $x$) - (Sum of elements at indices divisible by $y$).

To make the Score as massive as possible, we need to be extremely greedy:
1. We must put the **largest possible numbers** at the $x$ indices.
2. We must put the **smallest possible numbers** at the $y$ indices.

But wait! What about the indices that are divisible by **both** $x$ and $y$ (like index 12 if $x=4$ and $y=6$)?
If an index is divisible by both, its value gets added to the $x$-sum, and then immediately subtracted by the $y$-sum.
$Value - Value = 0$.
These collision indices contribute absolutely nothing to the final score. Therefore, we don't care what numbers we put there. We should save our best (largest) numbers for the pure $x$ indices, and our worst (smallest) numbers for the pure $y$ indices.

**The Blueprint:**
1.  **Count the $x$ indices:** How many indices are divisible by $x$? -> $\lfloor N / x \rfloor$
2.  **Count the $y$ indices:** How many indices are divisible by $y$? -> $\lfloor N / y \rfloor$
3.  **Count the Collisions:** How many indices are divisible by *both*? -> $\lfloor N / \text{LCM}(x, y) \rfloor$
4.  **Count the "Pure" Indices:**
    * Pure $x$ count = (Total $x$) - (Collisions)
    * Pure $y$ count = (Total $y$) - (Collisions)
5.  **Greedy Assignment (The Math):**
    * Give the `Pure x count` largest numbers to the $x$ positions. This is the sum of integers from $(N - \text{Pure x count} + 1)$ to $N$.
    * Give the `Pure y count` smallest numbers to the $y$ positions. This is the sum of integers from $1$ to `Pure y count`.
    * Use the Arithmetic Progression sum formula: $\text{Sum}(L, R) = \frac{(L + R) \times (R - L + 1)}{2}$.

### Part 3: The Dry Run

**Input:** `N = 7, x = 2, y = 3`
1. **Total $x$ indices:** $7 / 2 = 3$ (Indices 2, 4, 6)
2. **Total $y$ indices:** $7 / 3 = 2$ (Indices 3, 6)
3. **Collisions ($\text{LCM}(2,3) = 6$):** $7 / 6 = 1$ (Index 6)
4. **Pure Counts:**
   * Pure $x$ = $3 - 1 = 2$.
   * Pure $y$ = $2 - 1 = 1$.
5. **Greedy Assignment:**
   * We need the 2 largest numbers from $1$ to $7$. That is $6$ and $7$. Sum = $13$.
   * We need the 1 smallest number from $1$ to $7$. That is $1$. Sum = $1$.
6. **Result:** $13 - 1 = 12$. Flawless.

---

### Part 4: Complexity Analysis
* **Time Complexity:** $\mathcal{O}(\log(\min(x, y)))$ per test case. Finding the GCD to calculate the LCM takes logarithmic time. The rest of the operations are $\mathcal{O}(1)$ math formulas. For $10^4$ test cases, this runs in roughly 1 millisecond.
* **Space Complexity:** $\mathcal{O}(1)$. Only a few variables are stored.