### 🧠 The Intuition: The "Wasted Space" Concept

Think of $x$ as the capacity of a shipping box. The array elements are piles of items.
The "beauty" is the total number of boxes you need to ship all the piles. Because you can't ship a fraction of a box, any leftovers require a whole new box (that's the ceiling function $\lceil \dots \rceil$).

**Scenario A: Boxing separately (No Operations)**
If $x = 3$, and you have piles of `4` and `4`.

* Pile 1 needs $\lceil 4/3 \rceil = 2$ boxes. (One box is full, the second box only has 1 item. Lots of wasted space).
* Pile 2 needs $\lceil 4/3 \rceil = 2$ boxes.
* Total boxes = **4**.

**Scenario B: Combining the piles (Using Operations)**
What if we combine the piles *before* boxing them? $4 + 4 = 8$.

* The giant pile needs $\lceil 8/3 \rceil = \mathbf{3}$ boxes.

**The Grandmaster Revelation:** By combining elements, you pool their "leftover" remainders together. If those remainders add up to $x$, they fit into a single box, saving you an entire box!
Therefore, **combining elements can only DECREASE the total beauty, or keep it the exact same.** It can *never* increase it.

### ⚙️ The Approach

Because of this absolute mathematical truth, the two answers the problem asks for are actually the two most extreme states of the array:

1. **Maximum Beauty:** Never combine anything. Keep all the "wasted space" separate. Just calculate the ceiling of every single element divided by $x$ and sum them up.
2. **Minimum Beauty:** Combine literally everything. Smash the entire array into one massive single number, and then divide it by $x$ and take the ceiling.

That's it. There is no Dynamic Programming. There is no simulation. It is pure Math and Greedy logic.

---

### 💻 The GOD Code (Java)

Here is how a Grandmaster writes this. Pay very close attention to two things:

1. I am using `long` everywhere. The sum of $10^5$ elements up to $10^9$ is $10^{14}$. If you use `int`, you will overflow and get a Wrong Answer.
2. Look at how I calculate the ceiling without using `Math.ceil()`.

```java
import java.io.*;
import java.util.*;

public class ArrayBeauty {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            
            long maxBeauty = 0;
            long totalSum = 0;
            
            for (int i = 0; i < n; i++) {
                long val = Long.parseLong(st.nextToken());
                
                // Max Beauty: Calculate ceiling individually
                // GRANDMASTER TRICK: ceil(a / b) for integers is (a + b - 1) / b
                maxBeauty += (val + x - 1) / x; 
                
                // Accumulate total sum for Min Beauty
                totalSum += val;
            }
            
            // Min Beauty: Calculate ceiling of the combined total sum
            long minBeauty = (totalSum + x - 1) / x;
            
            out.println(minBeauty + " " + maxBeauty);
        }
        out.flush();
    }
}

```

---

### 🏃‍♂️ Dry Run: Visualizing the Extremes

Let's run **Test Case 2:** `n = 3, x = 3`, Array = `[6, 4, 11]`

**Maximum Beauty (Keep them separate):**

* Element 6: $\lceil 6/3 \rceil = 2$
* Element 4: $\lceil 4/3 \rceil = 2$ (math check: `(4 + 3 - 1) / 3 = 6 / 3 = 2`)
* Element 11: $\lceil 11/3 \rceil = 4$
* **Max Beauty:** $2 + 2 + 4 = \mathbf{8}$

**Minimum Beauty (Smash them together):**

* `totalSum` = $6 + 4 + 11 = 21$
* **Min Beauty:** $\lceil 21/3 \rceil = \mathbf{7}$

Output: `7 8`. Matches perfectly.

---

### ⏱️ Complexity Analysis

* **Time Complexity:** $\mathcal{O}(N)$ per test case. We do exactly one pass over the array. Overall time is bounded by the sum of $N$, which is $10^5$. This runs in a few milliseconds.
* **Space Complexity:** $\mathcal{O}(1)$. We don't even store the array! We just read the values on the fly, update our running sums, and forget them.

---

### 👑 The PRO Guide: The "Extremes" Pattern

This problem is a quintessential Codeforces Div2 A/B problem. It tests your ability to spot the **"Global vs. Local Extremes"** pattern.

1. **The Ceiling Math Trick:** Stop using `Math.ceil((double) a / b)`. When numbers get massive (like $10^{18}$), `double` loses precision and will give you the wrong answer. Always use integer math: `(a + b - 1) / b`. This is a mandatory Grandmaster tool.
2. **Evaluating the Operation:** When a problem says "You can do this operation as many times as you want," don't try to simulate it. Ask yourself: *"If I do this operation zero times, what happens? If I do this operation infinitely until I can't anymore, what happens?"* The answers to the problem almost always lie at those two extreme boundaries.

You successfully broke down a Codeforces Math problem. Your instincts were right to test the inequality, you just tripped on the execution.

Are we cementing this concept, or do you want to test your mettle against the "Binary Search on Answer" pattern next?