# 🧠 Finding Prime Divisors/Factors of a Number

## From Brute Force → Grandmaster Optimization

---

# 🎯 Problem

Given a number **N**, find **all its prime divisors** (unique prime factors).

Example:

```
N = 780
Prime Divisors → [2, 3, 5, 13]
```

---

# ⚔️ Approach 1 — Brute Force Method

---

## 💡 Idea

1. Find **all divisors** of `N`.
2. Check which divisors are **prime**.

---

## Key Observation

Divisors always appear in **pairs**.

Example:

```
N = 36

(1,36)
(2,18)
(3,12)
(4,9)
(6,6)
```

If `d` divides `n`, then `n/d` also divides `n`.

👉 Therefore we only check up to:

```
√N
```

---

## ✅ Java Implementation (Brute Force)

```java
// author: someear1h

import java.util.*;

public class Solution {
    static boolean isPrime(int n) {
        if(n <= 1) return false;
        if(n <= 3) return true;

        if(n % 2 == 0 || n % 3 == 0) return false;

        // remembering technique -> 5 and 7 are the prime numbers after 3
        // so check for i = 5 and i+2 = 7 and then keep adding 6 to i (i+=6) to jump

        for(int i = 5; i * i <= n; i+=6) {
            if(n % i == 0 || n % (i+2) == 0) return false;
        }

        return true;
    }

    static List<Integer> findPrimeFactors(int n) {
        List<Integer> primeFactors = new ArrayList<>();

        for(int i = 1; i * i <= n; i++) {
            if(n % i == 0) {
                if(isPrime(i)) primeFactors.add(i);

                if(n/i != i && isPrime(n/i)) {
                    primeFactors.add(n/i);
                }
            }
        }

        return primeFactors;
    }

    public static void main(String[] args) {
        int n1 = 36;
        int n2 = 780;

        System.out.println(findPrimeFactors(n1));
        System.out.println(findPrimeFactors(n2));
    }
}
```

---

## ⏱ Time Complexity

Loop to √N + prime check inside:

```
O(√N × √N) ≈ O(N)
```

---

## 📦 Space Complexity

```
O(D)
```

D = number of prime divisors.

---

## ❌ Problem with Brute Force

We are:

✔ Finding divisors
✔ Then checking primality again

👉 **Too many unnecessary checks.**

We can do MUCH better.

---

# ⭐ Approach 2 — Prime Factorization Insight

---

## ⚠️ Naive Thought

```
for(i = 2 → N)
    if(N % i == 0)
```

Worst case:

```
N = 37 (prime)
→ Loop runs completely
→ O(N)
```

Very slow.

---

# 🔥 Grandmaster Observations

---

## ✅ Observation 1 — Factors Reduce N

Example:

```
N = 780

780 = 2 × 2 × 3 × 5 × 13
```

Instead of checking every number:

👉 Divide immediately.

```
780 / 2 = 390
390 / 2 = 195
195 / 3 = 65
65 / 5 = 13
13 / 13 = 1
```

### Magic Insight

```
N keeps shrinking!
```

Less work every step.

---

## ✅ Observation 2 — Only Check Until √N

Mathematical Truth:

If a factor is larger than √N,
its pair must be smaller than √N.

So we only check:

```
i = 2 → √N
```

---

# 🚀 Final Optimized Algorithm

---

### Steps

1. Start from `i = 2`
2. While `i * i <= n`
3. If `i` divides `n`

   * add `i`
   * divide repeatedly
4. After loop:

   * if `n != 1`
   * remaining number is prime

---

## 🧩 Pseudocode

```
for i = 2 → √N
    if N % i == 0
        add i

        while N % i == 0
            N = N / i

if N != 1
    add N
```

---

# ☕ Grandmaster Java Implementation (Optimal)

```java
// author: someear1h

import java.util.*;

public class Solution {
    static List<Integer> findPrimeFactors(int n) {
        List<Integer> sama = new ArrayList<>();

        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                sama.add(i);

                while(n % i == 0) {
                    n = n / i;
                }
            }
        }

        // leftover prime
        if(n != 1) {
            sama.add(n);
        }

        return sama;
    }

    public static void main(String[] args) {
        int n1 = 36;
        int n2 = 780;

        System.out.println(findPrimeFactors(n1));
        System.out.println(findPrimeFactors(n2));
    }
}
```

---

# ✅ Example Run

```
Input:
780

Output:
[2, 3, 5, 13]
```

---

# ⏱ Time Complexity Analysis

---

### Outer Loop

```
Runs till √N
→ O(√N)
```

---

### Inner While Loop

Each division reduces N.

Total divisions ≈

```
log N
```

---

### ✅ Final Time Complexity

```
O(√N × log N)
```

Much faster than:

| Method                | Complexity      |
| --------------------- | --------------- |
| Naive                 | O(N)            |
| Brute divisor check   | O(N)            |
| ⭐ Prime Factorization | **O(√N log N)** |

---

# 📦 Space Complexity

```
O(k)
```

k = number of distinct prime factors.

---

# 🧠 Interview Mental Model

Think like a Grandmaster:

```
✔ Don't search everything
✔ Reduce problem size continuously
✔ Stop at √N
✔ Remaining number must be prime
```

---

# 🏆 Evolution Summary

```
Brute Force
   ↓
Use divisor pairs
   ↓
Avoid prime re-checking
   ↓
Reduce N while factoring
   ↓
Stop at √N
   ↓
Optimal Prime Factorization
```
