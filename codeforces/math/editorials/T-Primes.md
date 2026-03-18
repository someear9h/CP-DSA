Welcome back to the dojo. 

You brought a working solution to the table, and by doing so, you have essentially answered the two intuition questions I left you with. This is the exact moment where your brain stops simulating loops and starts seeing the pure mathematical matrix.

Let's decode this problem, understand exactly what this code is doing, and then I am going to show you a Grandmaster tweak to make this code even faster.

### 1. The Core Intuition (The Mathematical Matrix)

**Observation 1: The Odd Divisor Anomaly**
As we discussed, divisors come in pairs (the "See-Saw" effect). $12$ has $(1, 12), (2, 6), (3, 4)$. Because they are paired, almost all numbers have an **even** number of divisors. 
The *only* way a number can have an **odd** number of divisors is if the see-saw balances perfectly in the middle. Meaning $a \times a = X$. 
* **Rule 1:** Only **Perfect Squares** have an odd number of divisors.

**Observation 2: The "Exactly Three" Rule**
If $X$ is a perfect square, let's call its square root $P$. ($X = P^2$). 
We instantly know three divisors: $1$, $P$, and $P^2$. 
But if $P$ is a composite number (like $4$), then $X = 16$. The divisors of $16$ are $1, 2, 4, 8, 16$ (Five divisors). That breaks the rule!
To guarantee $X$ has *exactly* three divisors, $P$ itself must be completely unbreakable. 
* **Rule 2:** $P$ must be a **Prime Number**.

**The Ultimate Conclusion:** A "T-Prime" is mathematically defined as **strictly the square of a prime number**.

### 2. The Approach (The Sieve of Eratosthenes)
Now that we know we just need to find squares of prime numbers, how do we do it without triggering a Time Limit Exceeded (TLE)?

Since $X \le 10^{12}$, its square root $P$ will never exceed $10^6$. 
The code provided uses the **Sieve of Eratosthenes**, an ancient algorithm to find all prime numbers up to $10^6$ in milliseconds.

1. **The Boolean Array:** Create a massive array `arr` of size $1,000,001$ where `false` means "is prime" and `true` means "is not prime".
2. **The Cross-Off:** Start at $2$. It is prime. Now, run a fast inner loop to cross off every single multiple of $2$ ($4, 6, 8, 10\dots$). Move to $3$. It is prime. Cross off all multiples of $3$ ($9, 15, 21\dots$). 
3. **The Collection:** After the loop, whatever numbers are not crossed off are guaranteed primes! Square them and push them into a `std::set`.
4. **The $O(\log K)$ Query:** For every number $X$ the server gives us, we just check if it exists in our `std::set`.

### 3. Dry Run (Visualizing the Engine)
Let's trace the example: $n = 3$, array = `[4, 5, 6]`.

* **Precomputation:** The Sieve runs up to $10^6$. It identifies $2, 3, 5, 7\dots$ as primes. It inserts their squares into the `set`: `{4, 9, 25, 49...}`.
* **Query 1 ($X=4$):** Does $4$ exist in the set? Yes ($2^2$). Output `YES`.
* **Query 2 ($X=5$):** Does $5$ exist in the set? No. Output `NO`.
* **Query 3 ($X=6$):** Does $6$ exist in the set? No. Output `NO`.

### 4. Complexity Analysis of the Provided Code
* **Time Complexity (TC):**
  * **Sieve:** $O(M \log \log M)$ where $M = 10^6$. This is practically $O(M)$, brutally fast.
  * **Set Insertion:** There are roughly $78,498$ primes up to $10^6$. Inserting into a `std::set` (which is a Red-Black Tree) takes $O(P \log P)$.
  * **Queries:** For $N$ queries, searching the set takes $O(N \log P)$. 
  * **Total TC:** $O(M + P \log P + N \log P)$. It comfortably passes the 2-second limit.
* **Space Complexity (SC):** $O(M)$ for the boolean array + $O(P)$ for the `std::set`. Total $\approx$ a few Megabytes. Well within the 256MB limit.