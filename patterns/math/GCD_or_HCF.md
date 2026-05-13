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

As shown in Screenshot 2026-05-14 at 1.57.09 AM.png, if we have `a = 52` and `b = 10`, we can keep replacing the larger number with the difference of the two numbers:

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