Welcome back to the Dojo.

You should be extremely proud of yourself. You successfully transitioned away from the $\mathcal{O}(N^2)$ loops and the HashMap trap. You correctly identified that the entire problem revolves strictly around counting the `1`s and `0`s. You built the exact foundation a Grandmaster builds.

You only stumbled at the very last step: **The Combinatorics Math.**

You wrote this: `cnt += 1 + c0;`
This tells the computer: *"For every `1` I throw away, I can either throw away zero `0`s, or exactly ONE `0`."*

But what if the array has five `0`s? You could throw away the `1` alongside two `0`s, or three `0`s, or all five `0`s! Because removing *any* number of `0`s still leaves the sum perfectly untouched.

Let's plug into the matrix and unlock the final mathematical formula.

---

### 🧠 The Intuition: The Power of Binary Choice

To reduce the total sum $S$ to $S - 1$, our "throw away" pile must equal exactly $1$.

1. **Choosing the `1`:**
We must throw away exactly one `1`. If we have $c_1$ ones in the array, we have exactly **$c_1$** ways to pick which one to throw away.
2. **Choosing the `0`s:**
This is where the magic happens. Once we have our `1` in the "throw away" pile, we look at all the `0`s in the array.
For *every single `0*`, we have exactly **two choices**:
* Choice A: Keep it in the array.
* Choice B: Throw it away with the `1`.


If we have $c_0$ zeros, and each one gives us 2 independent choices, the total number of combinations of `0`s we can make is **$2^{c_0}$**.

**The Grandmaster Formula:**
Total Nearly Full Subsequences = (Ways to pick a `1`) $\times$ (Ways to pick a subset of `0`s)
**Result = $c_1 \times 2^{c_0}$**

---

### 💻 The GOD Code

Here is the flawless $\mathcal{O}(N)$ implementation. Notice the bitwise left-shift operator `1L << c0`. This is the Grandmaster way to calculate $2^{c_0}$ rapidly without using the slow and sometimes imprecise `Math.pow()` function.

*Note: We MUST use `1L` (a `long` integer) because $c_0$ can be up to 60. A standard 32-bit `int` overflows at $2^{31}$.*

```java
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            
            while (t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                long[] a = new long[n];
                var st = new StringTokenizer(br.readLine());
                
                for (int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                }
                
                out.println(solve(a, n));
            }
        }
    }
  
    static long solve(long[] nums, int n) {
        long c1 = 0;
        long c0 = 0;
        
        // 1. Count the actors
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) c1++;
            else if (nums[i] == 0) c0++;
        }
        
        // 2. The Formula: c1 * (2 ^ c0)
        // 1L << c0 is exactly 2^c0 using bitwise shifting
        return c1 * (1L << c0);
    }
}

```

---

### 🏃‍♂️ Dry Run the Matrix

Let's test this on Example 4 from the problem:
`n = 5`, `array = [3, 0, 2, 1, 1]`

1. **Counting Pass:**
* Is it `1`? (Indices 3 and 4). $c_1 = 2$.
* Is it `0`? (Index 1). $c_0 = 1$.


2. **The Formula:**
* $c_1 \times 2^{c_0}$
* $2 \times 2^1$
* $2 \times 2 = \mathbf{4}$


3. **Result:** `4`. (Matches the exact expected output).

**Let's visualize the 4 actual sequences we threw away:**
We have two `1`s (let's call them $1_A$ and $1_B$) and one `0`.

* Throw away $1_A$ only.
* Throw away $1_A$ AND the `0`.
* Throw away $1_B$ only.
* Throw away $1_B$ AND the `0`.
Exactly 4 ways!

---

### ⏱️ Complexity Analysis

* **Time Complexity:** $\mathcal{O}(N)$. We loop through the array exactly once to count the `1`s and `0`s. The bitwise shift `1L << c0` takes $\mathcal{O}(1)$ time.
* **Space Complexity:** $\mathcal{O}(1)$ inside the `solve` function. We only use two variables (`c1` and `c0`) regardless of the array's size. (The `main` function uses $\mathcal{O}(N)$ to store the array, but the actual algorithmic logic requires no extra space).

---

### 👑 The PRO Guide: The "Subset/Combinatorics" Pattern

You just leveled up your mathematical intuition. When you see problems involving **subsequences** (not subarrays) and **sums**, follow this hierarchy of thought:

1. **Can I find the delta?** (What do I need to remove/add to reach the target?)
2. **Does the order matter?** Subsequences preserve relative order, but if you only care about the *sum*, the order doesn't matter at all! You can just count frequencies.
3. **Are there neutral elements?** `0` is the ultimate neutral element in addition problems. Any time you see a problem asking for combinations of sums, immediately ask yourself: *"How do `0`s affect my answer?"* The answer is almost always a $2^{\text{count of zeros}}$ multiplier.

You have successfully survived the Math and Combinatorics section of the Dojo today. Would you like to save this pattern mentally, or are you ready for another challenge right now?