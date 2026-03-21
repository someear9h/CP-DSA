### 🧠 The Intuition: The "Zero Virus"

In bitwise AND (`&`), a `0` bit is like a highly contagious virus.
If you look at the truth table for AND:

* `1 & 1 = 1`
* `1 & 0 = 0`
* `0 & 0 = 0`

The moment a `0` is introduced to a bit position, it completely destroys the `1`. It can never be turned back into a `1`.

**The Grandmaster Realization:**
The problem allows you to pick *any* interval `[l, r]` and swap-AND the elements. Because you can perform this operation an **infinite number of times**, and you can overlap your intervals however you want, you can essentially move any element to interact with any other element.

This means if *even a single number* in the entire array has a `0` at the 3rd bit position, you can spread that `0` to the 3rd bit position of *every other number* in the array.

Since your goal is to minimize the maximum value, you want to spread as many `0` bits as mathematically possible to every single element.

Therefore, the absolute lowest value you can force every element to become is simply the bitwise AND of **all the elements in the array**.

---

### ⚙️ The Approach: The Accumulator

You don't need to simulate the intervals. You don't need to track which elements are swapping. You just need to aggregate the "Zero Virus" across the whole array.

1. **Initialize:** Take the first element of the array and store it in a variable (e.g., `totAND = a[0]`). This is your baseline.
2. **Iterate:** Loop through the rest of the array from index 1 to $n-1$.
3. **Accumulate:** For every element, update your variable: `totAND &= a[i]`. This continuously strips away any `1` bits that aren't shared by every single number.
4. **Return:** Once the loop finishes, `totAND` holds the minimum possible maximum value. Print it.

---

### 💻 The GOD Code (Recap)

```java
import java.io.*;
import java.util.*;

public class MochaandMath {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while (t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                var st = new StringTokenizer(br.readLine());
                
                // Read the first element to initialize the AND accumulator
                long totAND = Long.parseLong(st.nextToken());
                
                // Loop through the rest of the elements
                for (int i = 1; i < n; i++) {
                    long currentElement = Long.parseLong(st.nextToken());
                    totAND &= currentElement; // Spread the zero bits!
                }
                
                out.println(totAND);
            }
        }
    }
}

```

*(Notice how I slightly optimized the editorial code here? You don't even need to create an array `long[] a = new long[n];`. You can just keep a running total as you read the inputs!)*

---

### ⏱️ Complexity Analysis

* **Time Complexity:** **O(N)** per test case. We iterate through the sequence of $N$ numbers exactly one time. The bitwise AND operation takes **O(1)** time. Overall time complexity for all test cases is **O(Sum of N)**, which easily passes the 1-second time limit.
* **Space Complexity:** **O(1)**. By calculating the running AND total on the fly as we read the `StringTokenizer`, we don't even need to store the numbers in an array. We just use a single `totAND` variable.

---

### 👑 The PRO Guide Summary

Whenever you see a problem with these three traits:

1. **Bitwise AND (`&`) or OR (`|`)**
2. **"Choose any subsegment" / "Choose any pair"**
3. **"You can perform this operation ANY number of times"**

...immediately stop trying to simulate the operations. The answer is almost always going to be the global AND (or global OR) of the entire array. The "infinite operations" rule is the problem setter's secret way of telling you that *order and position no longer matter*.

Save this to your notes. You are building a lethal arsenal of patterns today.

Are we calling it a victory for this session so your brain can process this, or do you want to look at one more? You are the driver here.