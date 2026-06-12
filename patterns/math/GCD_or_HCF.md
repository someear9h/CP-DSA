# The Guide to GCD (Greatest Common Divisor)

Finding the Greatest Common Divisor (GCD) of two numbers `a` and `b` is a foundational algorithm in competitive programming. Here is the complete breakdown, from the naive approach to the highly optimized Euclidean Algorithm.

---

## Part 1: The Brute Force Approach

The simplest way to find the GCD of two numbers is to check every possible divisor starting from the smaller of the two numbers and going downwards to 1. The first number that perfectly divides both `a` and `b` is the GCD.

**The Logic:**

1. Find the minimum of `a` and `b`. Let's call it `limit`.
2. Loop a variable `i` from `limit` down to 1.
3. If `a % i == 0` AND `b % i == 0`, return `i`.

**Complexity:**

* **Time Complexity (TC):** O(min(a, b)). In the worst case (e.g., if the GCD is 1 and the numbers are prime), you will loop all the way down to 1. If your numbers are up to 10^9, this will cause a Time Limit Exceeded (TLE) error.
* **Space Complexity (SC):** O(1). We only use a few integer variables.

---

## Part 2: The Euclidean Algorithm (The Pro Way)

The ancient Greek mathematician Euclid discovered a brilliant property about divisors:
**If a number divides both `a` and `b`, it must also divide their difference `(a - b)`.**

### The Subtraction Method (Intuition)

if we have `a = 52` and `b = 10`, we can keep replacing the larger number with the difference of the two numbers:

* `gcd(52, 10)` -> replace 52 with (52 - 10)
* `gcd(42, 10)` -> replace 42 with (42 - 10)
* `gcd(32, 10)`
* `gcd(22, 10)`
* `gcd(12, 10)`
* `gcd(2, 10)` -> Now `b` (10) is larger, so we subtract `a` (2) from `b`.
* `gcd(2, 8)` ... and so on, until one number becomes `0`. The non-zero number is the GCD.

### The Modulo Optimization (The "Jump")

Repeated subtraction is still slow if one number is huge and the other is tiny (e.g., `a = 1,000,000` and `b = 2`).
But look closely at Screenshot 2026-05-14 at 1.57.09 AM.png. Subtracting `10` from `52` repeatedly until we get `2` is the exact definition of the **modulo operator (%)**.
`52 % 10 = 2`.

Instead of subtracting step-by-step, we can "jump" directly to the remainder:

* `gcd(52, 10)` -> `52 % 10 = 2` -> jump to `gcd(2, 10)`.
* `gcd(2, 10)` -> `10 % 2 = 0` -> jump to `gcd(2, 0)`.
When one number hits `0`, the other number (2) is our GCD!

---

## Part 3: The Java Implementation

Based directly on the pseudo-code provided in Screenshot 2026-05-14 at 1.59.10 AM.png, here is the clean, optimal Java code.

```java
public class Solution {
    
    // The Optimal GCD Method
    public static int getGCD(int a, int b) {
        
        // Keep looping as long as BOTH numbers are strictly greater than 0
        while (a > 0 && b > 0) {
            
            // If 'a' is the larger number, replace it with the remainder of a divided by b
            if (a > b) {
                a = a % b;
            } 
            // If 'b' is larger (or they are equal), replace it with the remainder of b divided by a
            else {
                b = b % a;
            }
        }
        
        // Once the loop breaks, one of the numbers is 0. 
        // The other number is our Greatest Common Divisor.
        if (a == 0) {
            return b;
        } else {
            return a;
        }
    }

    public static void main(String[] args) {
        int a = 52;
        int b = 10;
        System.out.println("The GCD of " + a + " and " + b + " is: " + getGCD(a, b));
        // Output: 2
    }
}

```

---

## Part 4: Why This Code is Optimal

### 1. How the while-loop works

The loop `while (a > 0 && b > 0)` guarantees that we never perform a modulo by zero (which would crash the program). Inside the loop, the `if-else` block ensures we are always taking the larger number and reducing it using the smaller number.

Because we use modulo `%` instead of subtraction `-`, numbers shrink drastically in a single step.

### 2. Time and Space Complexity

* **Time Complexity (TC):** O(log(min(a, b)))
* Why? Every time we do `a % b`, the value of `a` is reduced by at least half. Because the numbers shrink logarithmically (similar to binary search), the algorithm is blisteringly fast. Even for numbers as large as 10^18, this loop will run at most around 60 to 80 times.


* **Space Complexity (SC):** O(1)
* Why? We are only modifying the inputs `a` and `b` directly. We don't use any extra arrays, lists, or recursion stack space.



### 3. Review Notes for the Future

When you look at this code weeks from now, remember the core rule of Euclid's Modulo Algorithm:
**"The GCD of two numbers does not change if the larger number is replaced by its remainder when divided by the smaller number."**
You just keep crushing the larger number down using the `%` operator until one vanishes into `0`. The survivor is your GCD.



## Important Properties

This is the ultimate Grandmaster cheat sheet for GCD.

In competitive programming, GCD is rarely just about finding the greatest common divisor. It is used as a structural invariant, a constraint on reachability, and a tool to compress massive arrays. If you see a problem involving GCD, it is mathematically guaranteed to rely on one of the properties below.

Commit these to memory.

---

### Category 1: The Euclidean Foundations (Shifting & Scaling)

These are the fundamental operations that let you mutate numbers without changing their GCD.

**1. The Difference Invariant**

The GCD of any two numbers can NEVER be greater than the absolute difference between them.
Mathematically: $\gcd(a, b) \le |a - b|$Why? Because if a number $G$ divides both $a$ and $b$, it must also divide their difference. Therefore, the absolute maximum excitement the fans can ever get is exactly $g = |a - b|$. You don't need a while loop to find this; it's a simple subtraction!
* **Property:** $\gcd(a, b) = \gcd(a - b, b) = \gcd(a \bmod b, b)$
* **The CP Signal:** This is what you just used in the racing problem! If a problem allows you to add or subtract the *same* value $k$ from an entire array, the GCD of the array is bounded by the differences between the elements.
* **Extension:** $\gcd(a, b, c) = \gcd(a, b - a, c - b)$. You can replace an array with its difference array to find the GCD!

**2. The Coprime Reduction**

* **Property:** If $g = \gcd(a, b)$, then $\gcd(a/g, b/g) = 1$.
* **The CP Signal:** When you need to find if two numbers share *any* other factors, or if you need to reduce a fraction to its simplest form. Dividing both numbers by their GCD leaves them perfectly coprime.

**3. The Scalar Extraction**

* **Property:** $\gcd(k \cdot a, k \cdot b) = k \cdot \gcd(a, b)$
* **The CP Signal:** If every number in your array is multiplied by $X$, you can just pull $X$ out, find the GCD of the base array, and multiply the final answer by $X$.

---

### Category 2: Array & Subarray Secrets (The Codeforces Div 2 C/D Lifesaver)

If a problem asks about "the GCD of all subarrays" or "sliding window GCD," it is testing exactly one mathematical law: **The Logarithmic Drop.**

**4. The Monotonicity of GCD**

* **Property:** $\gcd(a_1, a_2, a_3) \le \gcd(a_1, a_2)$. Adding more numbers to a GCD pool can *never* increase the GCD. It can only decrease it or leave it exactly the same.
* **The CP Signal:** Because it's monotonic, you can use **Binary Search** to find how far an array can extend before its GCD drops below a certain value.

**5. The Log-Drop Guarantee (The Holy Grail)**

* **Property:** When calculating the GCD of a running prefix of an array, the GCD will change **at most $\log_2(\max A)$ times.**
* **The CP Signal:** If the maximum number in an array is $10^9$, its GCD can only change about 30 times! Even if the array has $10^5$ elements, there are only $\approx 30$ distinct GCD values in any subarray starting at index $i$. You can store these values in a map or a stack and solve $\mathcal{O}(N^2)$ brute-force subarray problems in $\mathcal{O}(N \log(\max A))$ time.

---

### Category 3: Reachability and Combinatorics

When problems ask "Can you reach state X?" or "Find the number of ways," GCD is usually the invisible gatekeeper.

**6. Bézout’s Identity (The Grid Walker)**

* **Property:** The linear equation $ax + by = c$ has integer solutions for $x$ and $y$ **if and only if** $c$ is a multiple of $\gcd(a, b)$.
* **The CP Signal:** "You have a 3-liter jug and a 5-liter jug, can you measure exactly 4 liters?" or "You can jump forward by $A$ and backward by $B$, can you reach coordinate $C$?"
* **The Answer:** Yes, if $C \bmod \gcd(A, B) == 0$. Otherwise, print `-1`.

**7. The LCM Bridge**

* **Property:** $a \times b = \gcd(a, b) \times \operatorname{lcm}(a, b)$
* **The CP Signal:** If a problem asks for the Least Common Multiple (LCM), **never** try to compute it directly. Always compute the GCD first, then use this formula.
* **Warning:** Multiply $a \times b$ will overflow standard integers. Always write it as: $\operatorname{lcm}(a, b) = (a / \gcd(a, b)) \times b$ to prevent overflow!

---

### Category 4: The "Cheat Code" Properties

These are highly specific algebraic and sequence properties that problem setters use to build "impossible" math puzzles.

**8. The Consecutive Coprime Rule**

* **Property:** $\gcd(x, x+1) = 1$. (Any two consecutive integers are always coprime).
* **Property:** $\gcd(x, x+2)$ is $1$ if $x$ is odd, and $2$ if $x$ is even.
* **The CP Signal:** If you need to construct an array of coprime numbers, or if you need to find the maximum GCD of a range $[L, R]$, if the range has length $\ge 2$, the GCD of the whole range is almost always 1.

**9. The Exponential / String GCD**

* **Property:** $\gcd(k^a - 1, k^b - 1) = k^{\gcd(a, b)} - 1$
* **The CP Signal:** "Given two massive strings of '1's of length $A$ and $B$, find the length of their greatest common divisor string." The length of the answer is just $\gcd(A, B)$.
* **Example:** $\gcd(2^{10} - 1, 2^{15} - 1) = 2^{\gcd(10, 15)} - 1 = 2^5 - 1 = 31$.

**10. The Fibonacci GCD**

* **Property:** $\gcd(F_a, F_b) = F_{\gcd(a, b)}$ (where $F_i$ is the $i$-th Fibonacci number).
* **The CP Signal:** If a problem involves Fibonacci numbers and divisors, you don't need to compute the massive Fibonacci numbers. Just compute the GCD of their indices!