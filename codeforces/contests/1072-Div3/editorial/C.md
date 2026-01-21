* **Root:** 
* **Left Child:** 
* **Right Child:** 

However, there is a **catch**: You cannot simply "build" the tree using recursion or a `TreeNode` class.
Why? Because if , the tree will have millions of nodes. Building it would give you **Memory Limit Exceeded (MLE)** or **Time Limit Exceeded (TLE)**.

### The Optimization: "The Range Trick"

The C++ code you provided uses a clever mathematical property to avoid building the whole tree.

Instead of tracking every single node at a certain depth, we only track the **Minimum (`l`)** and **Maximum (`r`)** values possible at that depth.

1. At depth 0, you only have . So range is .
2. At depth 1, the smallest value you can get is dividing the previous smallest by 2: .
3. At depth 1, the largest value you can get is dividing the previous largest by 2 (ceiling): .
4. **Crucial Logic:** If your target  lies inside this range , then it is **guaranteed** that  exists at this depth.

This works because the values at any depth in this specific tree are always "contiguous" (or close enough that no gaps large enough to miss an integer appear).

---

### Java Solution (Equivalent to your C++ code)

Here is the efficient Java implementation. I used `(r + 1) / 2` to calculate ceiling division, which is the same as `r / 2 + r % 2`.

```java
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        // Fast I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(br.readLine());
        
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            out.println(solve(n, k));
        }
        out.flush();
    }

    private static int solve(int n, int k) {
        // 'l' is the smallest value at current depth
        // 'r' is the largest value at current depth
        int l = n;
        int r = n;
        int depth = 0;

        // We continue as long as the largest possible value 'r' is >= k.
        // If 'r' becomes smaller than k, we have gone too deep and missed it.
        // Also stop if r == 0 to prevent infinite loops (though k >= 1 handles this).
        while (r >= k) {
            // Check if k is within the current range [l, r]
            if (l <= k && k <= r) {
                return depth;
            }

            // Move to next depth:
            // Smallest becomes floor(l / 2)
            l = l / 2;
            
            // Largest becomes ceil(r / 2). 
            // In integer math, ceil(a/b) is (a + b - 1) / b. Here b=2.
            r = (r + 1) / 2;
            
            depth++;
        }

        return -1; // If loop finishes, we never found k
    }
}

```

---

### Dry Run / Visualizing the Code

Let's trace **Example 1:** `n = 10`, `k = 3`.

**Initialization:**

* `l = 10`, `r = 10`
* `cnt (depth) = 0`

**Iteration 1:**

* Is `3` inside `[10, 10]`? **No.**
* Update `l`: .
* Update `r`: .
* `cnt` becomes **1**.
* *Interpretation:* At depth 1 (after 1 minute), all piles have size 5.

**Iteration 2:**

* Is `3` inside `[5, 5]`? **No.**
* Update `l`: .
* Update `r`: .
* `cnt` becomes **2**.
* *Interpretation:* At depth 2, the possible pile sizes are between 2 and 3.

**Iteration 3:**

* Is `3` inside `[2, 3]`? **YES!** ().
* **Return `cnt` (2).**

---

Let's trace **Example 3:** `n = 21`, `k = 4` (This returns -1).

**Initialization:** `l = 21`, `r = 21`.

**Iteration 1:**

* Range `[21, 21]`.  not in range.
* New `l = 10`, `r = 11`. `cnt = 1`.

**Iteration 2:**

* Range `[10, 11]`.  not in range.
* New `l = 5`, `r = 6`. `cnt = 2`.

**Iteration 3:**

* Range `[5, 6]`.  not in range.
* New `l = 2`, `r = 3`. `cnt = 3`.

**Iteration 4 (Crucial Step):**

* Range `[2, 3]`.  not in range.
* Wait! Our range is now `[2, 3]`. The maximum possible value is `3`.
* Our target is `4`. Since `r < k` (), we know we have passed the target. It is impossible to find `4` deeper in the tree because values only get smaller.
* **Loop ends.** Return **-1**.

### Explanation of the Logic

The code works because of the property mentioned in the problem description:

> "If a layer had no more than 2 distinct values, then the next layer will also have no more than 2."

At any given minute (depth), the pile sizes are extremely concentrated. You will never have a situation where the piles are sizes `{10, 12, 15}`. They will always be `{10, 11}` or `{10}`. This allows us to just check the boundaries `l` and `r` instead of checking every node.