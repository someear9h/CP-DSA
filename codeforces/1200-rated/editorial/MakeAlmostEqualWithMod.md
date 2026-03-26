Step back into the dojo.

This is a beautiful problem. It looks like chaotic number theory, but underneath, it is an elegant puzzle about bits. The constraints ($a_i \le 10^{17}$) scream that we cannot just loop to find $k$; we have to understand the fundamental nature of the numbers we are dealing with.

Let's break this down to its atomic level.

---

### 1. The Core Problem
You have an array of distinct positive integers. You need to find a magical number $k$ such that when you take every element in the array modulo $k$ ($a_i \pmod k$), the resulting array contains **exactly two distinct numbers**.

### 2. Constraint Analysis (The Silent Whispers)
* **$N \le 100$:** This is tiny. We can afford an $O(N^2)$ or even $O(N^3)$ operation per test case.
* **$a_i \le 10^{17}$:** This is massive. It means $k$ can be up to $10^{18}$. An $O(k)$ loop is impossible. We must construct $k$ logically, not search for it sequentially.

### 3. The Grandmaster Intuition (The Binary Truth)
When you see modulo operations and massive numbers, always ask: **What happens if I modulo by a power of 2?**

Let's look at numbers in binary.
* $10 = 1010_2$
* $11 = 1011_2$
* $14 = 1110_2$

What does $X \pmod 2$ do? It isolates the **last bit** (the 0th bit).
* $10 \pmod 2 = 0$ (Even)
* $11 \pmod 2 = 1$ (Odd)
* $14 \pmod 2 = 0$ (Even)

What does $X \pmod 4$ do? It isolates the **last two bits**.
What does $X \pmod 8$ do? It isolates the **last three bits**.

In general, $X \pmod{2^m}$ extracts exactly the **last $m$ bits** of $X$.

**The Golden Observation:**
All numbers in the array are distinct. This means that if we look at their binary representations, they must eventually differ at *some* bit position if we read them from right to left (from the least significant bit upwards).

Let's imagine scanning the bits of all the numbers from right to left (0th bit, 1st bit, 2th bit, etc.).
1.  **Scenario A:** What if all numbers have the exact same 0th bit? (e.g., they are all even).
    Then $X \pmod 2$ will result in exactly 1 distinct value (0). This is not our answer (we need 2).
2.  **Scenario B:** What if some numbers have a '0' at the 0th bit, and some have a '1' at the 0th bit?
    Then $X \pmod 2$ will result in exactly 2 distinct values (0 and 1). **We found our $k = 2$!**

But what if they all match for the first few bits?
**Example:** `[8, 24, 40]`
* $8 = 001000_2$
* $24 = 011000_2$
* $40 = 101000_2$

* 0th bit: All `0`. ($k=2 \implies [0, 0, 0]$ -> 1 distinct).
* 1st bit: All `0`. ($k=4 \implies [0, 0, 0]$ -> 1 distinct).
* 2nd bit: All `0`. ($k=8 \implies [0, 0, 0]$ -> 1 distinct).
* 3rd bit: All `1`. ($k=16 \implies [8, 8, 8]$ -> 1 distinct).
* 4th bit: `0`, `1`, `0`. ($k=32 \implies [8, 24, 8]$ -> **2 distinct values! (8 and 24)**).

Because all numbers are distinct, we are guaranteed that if we keep looking at larger and larger suffixes (by checking $k = 2^1, 2^2, 2^3 \dots 2^{60}$), we will *eventually* find a boundary where the suffixes split into exactly two groups. 
Wait, is it always exactly two groups? Yes! Because we are revealing exactly one new bit at a time. The numbers that previously had the same suffix will now split into at most two groups: those with a '0' in the new bit position, and those with a '1'.

### 4. The Approach (The Code's Logic)
1.  We know $k$ must be a power of 2: $k = 2^1, 2^2, \dots, 2^{60}$. (Since $10^{17} < 2^{60}$).
2.  Iterate through every possible power of 2. Let $k = 2^i$.
3.  For each $k$, calculate $a_j \pmod k$ for all elements in the array.
4.  Store these remainders in a `std::set`. A set automatically removes duplicates.
5.  If the size of the set is exactly 2, we have found our $k$. We print it and break.

### 5. Dry Run
**Input:** `n = 4, arr = [8, 15, 22, 30]`

* **Try $k = 2^1 = 2$:**
    * $8 \pmod 2 = 0$
    * $15 \pmod 2 = 1$
    * $22 \pmod 2 = 0$
    * $30 \pmod 2 = 0$
    * Set: `{0, 1}`. Size is 2!
    * **Result:** Print `2`. (The example output says 7, but 2 is also valid, and the problem says "print any").

**Input:** `n = 5, arr = [60, 90, 98, 120, 308]`
Let's trace the binary ends:
* $60$ (ends in `00`)
* $90$ (ends in `10`)
* $98$ (ends in `10`)
* $120$ (ends in `000`)
* $308$ (ends in `00`)

* **Try $k = 2$:** All are even. Modulos are `[0, 0, 0, 0, 0]`. Set size = 1.
* **Try $k = 4$:**
    * $60 \pmod 4 = 0$
    * $90 \pmod 4 = 2$
    * $98 \pmod 4 = 2$
    * $120 \pmod 4 = 0$
    * $308 \pmod 4 = 0$
    * Set: `{0, 2}`. Size is 2!
    * **Result:** Print `4`. (Example output says 30, but 4 is perfectly valid).

### 6. Complexity Analysis
* **Time Complexity (TC):** $\mathcal{O}(t \cdot 60 \cdot N \log N)$.
    For each test case ($t$), we loop 60 times. Inside the loop, we iterate over the $N$ elements and insert them into a `set` (which takes $\mathcal{O}(\log N)$ per insertion). Total per test case is $\approx 60 \cdot N \log N$. Given $N \le 100$, $60 \cdot 100 \cdot \approx 7 = 42,000$ operations per test case. This easily passes within the 1-second time limit.
* **Space Complexity (SC):** $\mathcal{O}(N)$ to store the array and $\mathcal{O}(N)$ for the set. Total SC is $\mathcal{O}(N)$.

### 7. Pro Guide to Mastery
This problem tests a critical CP skill: **"Don't solve the example, solve the constraints."**
The problem setter gave example outputs like `k=7` and `k=30` to confuse you into looking for weird prime factors or common divisors. But by ignoring the specific numbers in the example and looking at the properties of modulo math and the massive constraints, the power-of-2 trick emerges as a universal, guaranteed path to a valid answer. Always look for invariants (things that don't change, like bits splitting deterministically).