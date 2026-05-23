# The Guide to the Sieve of Eratosthenes

When a problem requires you to query multiple prime numbers, checking them one by one is a rookie mistake. To reach the top tiers of competitive programming, you must use the **Sieve of Eratosthenes**—an ancient algorithm that generates prime numbers in a given range with blazing speed.

Here is the complete breakdown, from understanding the core mechanics to writing the highly optimized code shown in your notes.

---

## Part 1: Where and Why We Use It

**Where to use it:**
You use the Sieve whenever a problem asks you to find *all* prime numbers up to a large limit `N` (typically up to N = 10^7), or when you need to answer multiple queries about whether various numbers in a range are prime.

**Why we use it (The Brute Force vs. The Sieve):**

* **Brute Force:** If you loop from 1 to N and run an `O(sqrt(N))` `isPrime()` function on every single number, the total Time Complexity is `O(N * sqrt(N))`. If N is 1,000,000, operations easily exceed 10^9, causing a Time Limit Exceeded (TLE) error.
* **The Sieve:** Instead of asking "Is this number prime?", the Sieve asks "What are the multiples of this prime?" By crossing out composite numbers, it reduces the Time Complexity to `O(N log(log N))`. This is practically linear, taking only milliseconds for N = 10^6.

---

## Part 2: How the Algorithm Works (The Intuition)

Imagine writing down all numbers from 2 to N.

1. You assume every number is prime initially.
2. You look at the first unmarked number (which is 2). It is a prime.
3. You go through the list and "cross out" all multiples of 2 (4, 6, 8, 10...). They are composite.
4. You move to the next unmarked number (3). It is prime. You cross out all multiples of 3.
5. You move to 4. It is already crossed out! So you skip it.
6. You repeat this process. Whatever is left uncrossed at the end are your prime numbers.

---

## Part 3: The Grandmaster Optimizations

Screenshot 2026-05-14 at 6.14.16 PM.png contains two massive optimizations that separate good code from great code.

### Optimization 1: The Outer Loop stops at `sqrt(N)`

Instead of checking every number up to `N`, the outer loop condition is `for (i = 2; i x i <= N; i++)`.

* **Why?** If a number `K` is composite, it must have at least one prime factor less than or equal to `sqrt(K)`. Therefore, all composite numbers up to `N` will have already been crossed out by the time we finish processing multiples of primes up to `sqrt(N)`.

### Optimization 2: The Inner Loop starts at `i * i`

When crossing out multiples of `i`, the inner loop starts at `j = i x i`.

* **Why?** Let's say `i = 7`. We need to cross out multiples of 7. The multiples are `7x2, 7x3, 7x4, 7x5, 7x6, 7x7`...
* Notice that `7x2` was already crossed out when we processed the prime `2`.
* `7x3` was crossed out when we processed `3`.
* `7x5` was crossed out when we processed `5`.
* The first uncrossed multiple of `7` is exactly `7x7`. Starting the loop at `i * i` saves redundant operations.

---

## Part 4: The Java Implementation

Here is the exact code derived from Screenshot 2026-05-14 at 6.14.16 PM.png, translated into clean Java.
*(Note: While the screenshot uses `prime[i] = 1` and `0`, standard Java practice uses a `boolean[]` array because it uses significantly less memory than an `int[]` array).*

```java
import java.util.Arrays;

public class Solution {
    
    public static void sieveOfEratosthenes(int N) {
        // 1. Initialize the prime array of size N + 1
        boolean[] isPrime = new boolean[N + 1];
        
        // 2. Assume all numbers from 2 to N are prime initially (O(N) operation)
        Arrays.fill(isPrime, true);
        isPrime[0] = false; 
        isPrime[1] = false;

        // 3. The Outer Loop: Iterate up to the square root of N (i * i <= N)
        // for(int i = 2; i * i < n; i++) if asked for prime numbers till N (N excluded)
        for (int i = 2; i * i <= N; i++) {
            
            // If i is still marked as prime...
            if (isPrime[i] == true) {
                
                // 4. The Inner Loop: Cross out multiples starting strictly from i * i
                for (int j = i * i; j <= N; j += i) {
                    // for(int j = i * i; j < n; j+=i) -> if asked till N (N excluded)
                    isPrime[j] = false; // Mark as composite (0)
                }
            }
        }

        // 5. Print or collect the results
        System.out.println("Primes up to " + N + ":");

        // for(int i = 2; i < n; i++) -> for till N (N excluded)
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        int N = 30; // Using the N=30 logic referenced in the screenshot's '49 <= 30' note
        sieveOfEratosthenes(N);
    }
}

```

---

## Part 5: Complexity Analysis

* **Time Complexity (TC):** `O(N log(log N))`.
* The outer loop does `N` work to set up the array.
* The inner crossing-out process runs `N/2 + N/3 + N/5 + N/7...` times. This sum is a prime harmonic series. Mathematically, the sum of the reciprocals of primes converges to `log(log N)`. Therefore, the total time is `O(N log(log N))`.


* **Space Complexity (SC):** `O(N)`. We must maintain an array of size `N + 1` to track the state of every number.

---

## Final Review Note

When you review this in a few weeks, just remember:
**"Assume all are prime. Loop up to `sqrt(N)`. If you see a prime, jump to its square `i*i`, and cross out every `i`-th number after that."**
It transforms a heavy mathematical test into a fast, memory-based crossing-out game.