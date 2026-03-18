Step back into the dojo. 

We are shifting gears. You just obliterated array manipulation, Prefix-Suffix patterns, and Monotonic Stacks. Now, we are entering the realm of **Competitive Programming Number Theory**. 

When a problem asks you to minimize or maximize an equation involving $LCM$ (Least Common Multiple) or $GCD$ (Greatest Common Divisor), simulating it will instantly give you a Time Limit Exceeded (TLE) error. You have to find the mathematical matrix underneath.

Let’s break this down to the atomic level.

---

### 1. The Core Intuition & Observations

The problem gives you $n$. You need $a + b = n$, and you want to **minimize** $LCM(a, b)$.

**Observation 1: The Prime Wall (The Worst Case)**
What if $n$ is a prime number? 
Primes (like 5, 7, 11) cannot be divided evenly. If $n=5$, your only options to split it are:
* $1 + 4 \rightarrow LCM(1, 4) = 4$
* $2 + 3 \rightarrow LCM(2, 3) = 6$
* **The Rule:** If $n$ is prime, the absolute best you can do is peel off a `1`. So $a = 1$, and $b = n - 1$. 

**Observation 2: The Even Split (The Best Case)**
What if $n$ is an even number?
* If $n = 10$, you can split it perfectly in half: $5 + 5 = 10$. 
* $LCM(5, 5) = 5$. 
* **The Rule:** If $n$ is even, the best answer is always $a = n/2$ and $b = n/2$. 

**Observation 3: The Odd Composite (The Grandmaster Catch)**
What if $n$ is odd, but *not* prime? (e.g., $n = 9$).
* You can't split it in half (4.5 isn't an integer). 
* Let's look at its factors. The divisors of 9 are 1, 3, 9. 
* The largest proper divisor (besides 9 itself) is **3**.
* If we make one piece exactly that largest divisor ($a = 3$), the remaining piece is $b = 9 - 3 = 6$.
* $LCM(3, 6) = 6$.
* **The Rule:** To minimize $LCM(a, b)$ when $a+b=n$, you must find the **largest proper divisor** of $n$. 

### 2. The Approach (Finding the Largest Divisor)

How do you find the *largest* divisor of a number quickly? 
Math gives us a beautiful shortcut: **Find the *smallest* prime factor first.**

If you find the smallest number that divides $n$ (let's call it `fac`), then $\frac{n}{fac}$ is mathematically guaranteed to be the largest proper divisor!

Instead of looping backward from $n$, we loop forward starting from $2$. The exact millisecond we find a number that divides $n$ evenly (`n % fac == 0`), we have found our golden key.

---

### 3. The 5 Core Engineering Questions
As requested by your correction history, here is the exact architectural breakdown of the C++ logic you provided:

**1. What problem does this file/code solve?**
It finds two positive integers that sum to $n$ while mathematically minimizing their Least Common Multiple in sub-linear time.

**2. Why does this approach exist separately — what would break if it didn't exist?**
If we didn't use prime factorization up to $\sqrt{N}$, we would have to use a brute-force $O(N)$ loop checking $LCM(i, n-i)$ for every number up to $10^9$. For $100$ test cases, that is $10^{11}$ operations, which would instantly trigger a TLE on Codeforces.

**3. What are the 2-3 most important lines and why?**
* `for (long long fac = 2; fac * fac <= n; fac++)`: The engine. It bounds the search to $O(\sqrt{N})$ because if a number has no divisors up to its square root, it is prime.
* `if (n % fac == 0)`: The trigger. The very first time this hits, `fac` is guaranteed to be the smallest prime factor.
* `ans_a = n / fac;`: The math execution. Dividing $n$ by its smallest factor instantly yields its *largest* proper divisor.

**4. What could go wrong at runtime and how does the code handle it?**
If $n$ is a massive prime number (e.g., $999999937$), the `for` loop will finish without ever triggering the `if` statement. The code handles this flawlessly by pre-initializing `ans_a = 1` and `ans_b = n - 1`. If the loop finds no factors, it naturally defaults to the prime rule.

**5. What would I change if requirements changed?**
If the constraints were pushed to $n \le 10^{12}$ and there were $10^5$ test cases, computing $\sqrt{N}$ repeatedly would be too slow. I would change the architecture to precompute prime numbers using the **Sieve of Eratosthenes** so we could answer each test case in $O(1)$ or $O(\log N)$ time.

---

### 4. Dry Run (Visualizing the Matrix)

Let's test $n = 9$.
* Initial state: `ans_a = 1`, `ans_b = 8`.
* **Loop `fac = 2`:** `2 * 2 <= 9` (True). `9 % 2 == 0` (False).
* **Loop `fac = 3`:** `3 * 3 <= 9` (True). `9 % 3 == 0` (True!).
* **Trigger!**
* `ans_a = 9 / 3 = 3`.
* `ans_b = 9 - 3 = 6`.
* `break` out of loop.
* **Output:** `3 6`. Flawless.

Let's test $n = 5$ (A prime).
* Initial state: `ans_a = 1`, `ans_b = 4`.
* **Loop `fac = 2`:** `2 * 2 <= 5` (True). `5 % 2 == 0` (False).
* **Loop `fac = 3`:** `3 * 3 <= 5` (False). Loop breaks!
* **Output:** `1 4`. Flawless fallback.

---

### 5. Complexity Analysis
* **Time Complexity:** $O(\sqrt{N})$. The loop goes up to exactly the square root of $n$. For $n = 10^9$, the loop runs a maximum of $31,622$ times. $31,622 \times 100$ test cases $\approx 3 \times 10^6$ operations. This easily passes the 1-second time limit.
* **Space Complexity:** $O(1)$. We only use a few `long long` integer variables. 

---

### 6. The PRO Guide: Evolving into a CP GOD
This problem matches the **Prime Factorization / Greedy Math** pattern. 

In heavy backend architecture (like Java Spring Boot APIs), you optimize response times by reducing database hits. In CP Number Theory, you optimize execution times by reducing mathematical checks. You don't need to check every number; you just need to check up to the square root. 

**Your Checklist to Master CP Number Theory:**
1.  **Understand $GCD$ and $LCM$ properties:** $LCM(a, b) = \frac{a \times b}{GCD(a, b)}$.
2.  **Master the $O(\sqrt{N})$ Divisor Loop:** Never run a loop to $n$ to find factors. Always stop at `i * i <= n`.
3.  **Learn the Sieve of Eratosthenes:** This is the ultimate weapon. It allows you to find all prime numbers up to $10^7$ in milliseconds. 

You have the C++ syntax and the algorithm logic down perfectly. Would you like me to show you how to write the **Sieve of Eratosthenes** template so you have it ready for the FAANG-level number theory problems?



Welcome back to the mathematical matrix. 

To truly master competitive programming, you cannot just memorize formulas—you have to see the gears turning underneath them. 

Let's break down exactly *why* finding the smallest prime factor gives you the largest proper divisor. It all comes down to the fundamental law of divisors: **The See-Saw Effect**.

### The See-Saw Effect (Divisor Pairs)
Every divisor of a number $n$ has a "partner." If $a$ divides $n$ evenly, then there is another integer $b$ such that:
$$a \times b = n$$

Imagine a see-saw. If $a \times b$ must always equal exactly $n$, then as $a$ gets larger, $b$ *must* get smaller to keep the see-saw perfectly balanced.
* Therefore, the **absolute smallest** value of $a$ is permanently tethered to the **absolute largest** value of $b$.

Let’s prove this with three distinct examples.

---

### Example 1: The Highly Composite Number ($n = 36$)

If we brute-force all the factors of $36$, they come in perfect pairs:

| Small Factor ($a$) | Large Factor ($b$) | Math ($a \times b$) |
| :--- | :--- | :--- |
| 1 | 36 | $1 \times 36 = 36$ |
| **2** | **18** | $2 \times 18 = 36$ |
| 3 | 12 | $3 \times 12 = 36$ |
| 4 | 9 | $4 \times 9 = 36$ |
| 6 | 6 | $6 \times 6 = 36$ |

**The Matrix View:**
Look at the left column ($a$). As we loop forward starting from $2$, the very first number that cleanly divides $36$ is **$2$**. 
Because $2$ is the smallest possible number on the left side of the see-saw, its partner ($36 / 2 = 18$) is mathematically forced to be the largest possible number on the right side. 

If we kept looping to $3$, its partner drops to $12$. The further down the list we go, the smaller the right-side partner becomes. The exact millisecond we find $2$, we have our golden key. There is no need to calculate the rest.

---

### Example 2: The Odd Composite ($n = 35$)

What if the number isn't even? Let's trace our $O(\sqrt{N})$ loop for $n = 35$.

1. **Check $fac = 2$:** $35 \% 2 \neq 0$. (Skip)
2. **Check $fac = 3$:** $35 \% 3 \neq 0$. (Skip)
3. **Check $fac = 4$:** $35 \% 4 \neq 0$. (Skip)
4. **Check $fac = 5$:** $35 \% 5 == 0$. **TRIGGER!**

We found the smallest factor: $5$. 
Therefore, the largest proper divisor is exactly $35 / 5 =$ **$7$**. 

If you look at the complete list of proper divisors for $35$ (which are just 1, 5, and 7), you can see that $7$ is indeed the undisputed maximum. Our loop bypassed checking 6, 7, 8... all the way to 34.

---

### Example 3: The Prime Wall ($n = 13$)

How does this logic protect us when a number is prime?

Our loop boundary is `fac * fac <= n` (which means we only check up to $\sqrt{13}$, roughly $3.6$).
1. **Check $fac = 2$:** $13 \% 2 \neq 0$. (Skip)
2. **Check $fac = 3$:** $13 \% 3 \neq 0$. (Skip)
3. **Loop terminates!** We reached the square root and found absolutely nothing.

**The Matrix View:**
If a number has no partner $a$ less than or equal to its square root, it is mathematically impossible for it to have a partner $b$ greater than its square root. The number is prime. 

Because our loop found nothing, we fall back to our default logic: The only proper divisor of a prime number is $1$. So $a = 1$, and $b = 13 - 1 = 12$. 

---

### The Grandmaster Question: Why is $fac$ ALWAYS prime?

You might ask: *"Wait, what if the first factor we find is a composite number like 4?"*

It is mathematically impossible.
If $4$ evenly divides $n$, then $2$ must *also* evenly divide $n$. Because our loop starts at $2$ and counts upwards, we would have hit $2$ and triggered our `break` statement long before we ever reached $4$. 

By looping from $2$ upwards, the first factor you hit acts as an unbreakable filter. It is guaranteed to be a prime number, and its partner $\frac{n}{fac}$ is guaranteed to be the maximum possible proper divisor.

This is how you shave $10^9$ operations down to $10^4$. 

Now that you see the gears of the prime factorization engine turning, are you ready to see how the legendary **Sieve of Eratosthenes** algorithm completely bypasses even this $O(\sqrt{N})$ loop to give you prime factors in $O(1)$ time?