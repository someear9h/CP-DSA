# Modulo 10^9+7 and the Division Problem

In competitive programming, answers can become astronomically huge. C(100, 50) is a number with 29 digits. A standard C++ `int` or `long long` will explode (overflow) trying to hold it.

To prevent computers from exploding, problems ask you to return the answer **modulo 10^9 + 7**. This is like clock math. If it goes past 10^9 + 7, it wraps back around to zero.

## The Fatal Flaw of Modulo Math

In normal math, how do we calculate C(N, K)? We use factorials and division.

```
C(5, 2) = (5 x 4) / (2 x 1) = 20 / 2 = 10
```

But in clock math (modulo), **division is illegal**. If you try to divide a modulo'd number by another modulo'd number, the math completely breaks and gives you garbage.

## The Workaround: The Modular Inverse

If you want to divide 20 by 2, what is another way to do it using multiplication?

You multiply 20 by 0.5. (20 x 0.5 = 10). Here, 0.5 is the "Multiplicative Inverse" of 2.

So, in modulo math, if we want to divide the numerator by the denominator, we must find the **modular inverse** of the denominator, and multiply them together.

## Fermat's Little Theorem

How do we find the modular inverse of a number B under a modulo M?

A mathematician named Fermat proved a magical cheat code: as long as M is a prime number (and 10^9 + 7 is deliberately a prime number), the inverse of B is simply B raised to the power of M - 2.

**The ultimate translation:**

Instead of doing: `Numerator / Denominator (mod M)`

We do: `(Numerator x Denominator^(M-2)) (mod M)`

That is exactly what this line in the code is doing:

```cpp
return (num * modInverse(den)) % MOD;
```

It is calculating the numerator, figuring out the "0.5 equivalent" for the denominator, and multiplying them together so we never actually use the `/` operator.

---

# Binary Exponentiation and nCr

---

## Part 1: Binary Exponentiation (`binpow`)

**The Goal:** Calculate a^b (mod m).

If we want to calculate 3^13, a beginner writes a `for` loop that multiplies 3 by itself 13 times. But what if b is 10^9? A loop takes 1 billion steps. `binpow` does it in **30 steps**.

### The Math

Every number can be written in binary. The exponent 13 in binary is `1101` (which is 8 + 4 + 0 + 1).

Therefore: `3^13 = 3^8 x 3^4 x 3^1`

Instead of multiplying by 3 thirteen times, we just keep squaring the base (3 -> 9 -> 81 -> 6561 ...) and only multiply it into our final answer when the binary bit of the exponent is `1`.

### Line-by-Line Breakdown

```cpp
long long binpow(long long a, long long b, long long m) {
    a %= m;             // 1. If our base 'a' is already larger than 'm', shrink it immediately.
    long long res = 1;  // 2. The result starts at 1 (the identity for multiplication).

    while (b > 0) {     // 3. We will loop until the exponent 'b' is completely destroyed (reaches 0).

        // 4. (b & 1) is bitwise AND. It looks at the right-most binary bit of 'b'.
        // If it is 1, the number is odd. We multiply our current 'a' into our result.
        if (b & 1) res = res * a % m;

        // 5. Square the base for the next loop. (e.g., 3 becomes 9, 9 becomes 81).
        a = a * a % m;

        // 6. (b >>= 1) is bitwise right-shift. It deletes the right-most bit of 'b'.
        // Mathematically, this is exactly the same as doing: b = b / 2.
        b >>= 1;
    }
    return res;
}
```

### Dry Run: 3^13 (mod 100)

**Initialization:** `a = 3`, `b = 13` (binary `1101`), `res = 1`, `m = 100`

**Loop 1** (`b = 13` | `1101`):
- `13 & 1` is True (it ends in 1). `res = (1 * 3) % 100 = 3`
- Square the base: `a = (3 * 3) % 100 = 9`
- Shift b: 13 / 2 = 6 (binary `110`)

**Loop 2** (`b = 6` | `110`):
- `6 & 1` is False (it ends in 0). Skip the `res` update.
- Square the base: `a = (9 * 9) % 100 = 81`
- Shift b: 6 / 2 = 3 (binary `011`)

**Loop 3** (`b = 3` | `011`):
- `3 & 1` is True. `res = (3 * 81) % 100 = 243 % 100 = 43`
- Square the base: `a = (81 * 81) % 100 = 6561 % 100 = 61`
- Shift b: 3 / 2 = 1 (binary `001`)

**Loop 4** (`b = 1` | `001`):
- `1 & 1` is True. `res = (43 * 61) % 100 = 2623 % 100 = 23`
- Square the base: `a = (61 * 61) % 100 = 21`
- Shift b: 1 / 2 = 0

**End of Loop:** `b` is 0. Return `res = 23`.

**Proof:** 3^13 = 1,594,323. And 1,594,323 (mod 100) = **23**. Correct.

---

## Part 2: Combinatorics (`nCr`)

**The Goal:** Calculate C(n, k) (mod m). "Out of n items, choose k items."

The mathematical formula is: `(n x (n-1) x ... (k terms)) / (1 x 2 x ... x k)`

### Line-by-Line Breakdown

```cpp
long long nCr(long long n, long long k, long long m) {
    // 1. Sanity check. You cannot choose -1 items, and you cannot choose 6 items from 5 items.
    if (k < 0 || k > n) return 0;

    // 2. Symmetry optimization. Choosing 98 winners out of 100 is exactly the same as choosing 2 losers.
    // If k is huge, we flip it to the smaller number to save thousands of loop iterations.
    if (k > n / 2) k = n - k;

    long long num = 1, den = 1;

    // 3. The engine. We loop exactly 'k' times.
    for (int i = 0; i < k; i++) {
        // 4. Build the numerator. First loop multiplies by n. Second loop by (n-1). Third by (n-2)...
        num = (num * (n - i)) % m;
        // 5. Build the denominator. First loop multiplies by 1. Second loop by 2. Third by 3...
        den = (den * (i + 1)) % m;
    }

    // 6. The division trick. Instead of (num / den), we multiply by the modular inverse of den.
    // (Note: modInverse calls our binpow function to calculate den^(m-2) % m).
    return (num * modInverse(den, m)) % m;
}
```

### Dry Run: C(5, 2) (mod 7)

Note: Fermat's Little Theorem requires the modulo to be a prime number. So we use m = 7 for this test.

**Initialization:** `n = 5`, `k = 2`, `m = 7`. `num = 1`, `den = 1`.

**Symmetry check:** Is 2 > 5/2? No. Proceed.

**Loop i = 0:**
- `num = (1 * (5 - 0)) % 7 = 5`
- `den = (1 * (0 + 1)) % 7 = 1`

**Loop i = 1:**
- `num = (5 * (5 - 1)) % 7 = (5 * 4) % 7 = 20 % 7 = 6`
- `den = (1 * (1 + 1)) % 7 = (1 * 2) % 7 = 2`

**End of Loop:** `num = 6`, `den = 2`

**The Finish Line (Modular Inverse):**
- We need to return `(6 * modInverse(2, 7)) % 7`
- `modInverse(2, 7)` calculates 2^(7-2) (mod 7), which is 2^5 (mod 7)
- 2^5 = 32. And 32 (mod 7) = 4. So the modular inverse of 2 under modulo 7 is **4**
- Substituting back: `(6 * 4) % 7 = 24 % 7 = 3`

**Final Output: 3**

**Proof:** Normal math says C(5, 2) = 10. What is 10 (mod 7)? It is **3**. Correct.

---

## Grandmaster Summary

- `binpow` looks at the binary 1s and 0s of an exponent to calculate massive powers in milliseconds.
- `nCr` takes a fraction, calculates the top half (`num`) and the bottom half (`den`) iteratively, and then uses `binpow` to cleanly divide them using prime number magic.

The binary shifting (`>>= 1`) and bitwise AND (`& 1`) are simply lightning-fast ways to perform `/ 2` and `% 2` respectively.