Step back into the dojo. 

You have officially entered **Phase 3: The GCD/LCM Trap**. 

This is the exact type of problem that makes beginners completely lose their minds. They try to write `for` loops, they write custom `gcd()` functions, they try to simulate $LCM$ for every pair of numbers between $l$ and $r$, and they get annihilated by a Time Limit Exceeded (TLE) error because $r$ can be up to $10^9$.

You don't need a single loop to solve this. You just need to look at the pure mathematical definition of a multiple. Let's break down the matrix.

### 1. The Core Intuition (The Matrix of Multiples)

**Observation 1: The Ultimate LCM Shortcut**
What is the $LCM$ (Least Common Multiple) of two numbers if one number perfectly divides the other? 
If you have $3$ and $6$, the $LCM(3, 6)$ is just $6$. 
If you have $x$ and $y$, and $y$ is a direct multiple of $x$, then mathematically, **$LCM(x, y)$ is always exactly $y$.**

**Observation 2: The Greedy Choice**
The problem wants us to pick $x$ and $y$ such that their $LCM$ is $\le r$. 
Since we know that picking a direct multiple makes the $LCM$ equal to $y$, we just need to make sure $y \le r$.
How do we make $y$ as small as possible to guarantee it stays under $r$?
We take our starting number $x$, and we multiply it by the absolute smallest integer possible (other than 1). 
* **The Rule:** The absolute smallest multiple of $x$ is **$2 \times x$**.

**Observation 3: The Floor of the Range**
We know we want to test $x$ and $2x$. 
To give $2x$ the highest possible chance of staying safely underneath the ceiling ($r$), we must make $x$ as small as mathematically possible. 
What is the absolute smallest number available to us in the entire range? It is the floor of the range: $l$.
* **The Golden Pair:** The absolute safest, most optimal pair of numbers to pick in any range is **$l$** and **$2l$**.

**Observation 4: The Impossibility Wall**
If we pick the absolute best-case scenario ($l$ and $2l$), and $2l$ is *still* greater than $r$... then it is mathematically impossible for any other pair to work. If the smallest number in the range multiplied by 2 breaks the ceiling, then any larger number multiplied by 2 will break the ceiling even harder. You output `-1 -1`.

### 2. Dry Run (Visualizing the Engine)

**Test Case 2: $l = 13, r = 69$**
* Our absolute best-case $x$ is $13$.
* The smallest multiple of $x$ is $y = 2 \times 13 = 26$.
* Is $26 \le 69$? **YES.**
* Output: `13 26`. (The $LCM$ of 13 and 26 is 26, which is inside the range).

**Test Case 4: $l = 88, r = 89$**
* Our absolute best-case $x$ is $88$.
* The smallest multiple of $x$ is $y = 2 \times 88 = 176$.
* Is $176 \le 89$? **NO.**
* If 88 fails, will 89 work? $89 \times 2 = 178$. Worse. 
* Output: `-1 -1`. It is physically impossible.

### 3. Complexity Analysis
* **Time Complexity (TC):** $O(1)$ per test case. You do a single multiplication and an `if` statement. For $10^4$ test cases, this runs in exactly 0.000001 seconds. 
* **Space Complexity (SC):** $O(1)$. No arrays, no vectors, just a couple of variables.

### 4. The PRO Guide: The GCD/LCM Archetype
This problem perfectly matches the **Greedy Number Theory** pattern. 
Whenever a Codeforces problem asks you to find an $LCM$ or $GCD$ inside a massive range ($10^9$ or higher), it is a psychological trap. **Never simulate it.**
1. Look for the "Multiple Identity": $LCM(x, c \cdot x) = c \cdot x$.
2. Look for the "Divisor Identity": $GCD(x, c \cdot x) = x$.
3. Force the smallest possible multiplier ($c = 2$) and check if it fits the boundaries.