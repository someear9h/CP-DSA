Step back into the dojo.

This is a beautiful problem that separates the coders who just type from the engineers who can actually *see* the math. When you read a problem involving absolute differences of adjacent array elements, you must immediately stop looking at them as isolated numbers and start visualizing them as a **mountain range**.

Let's break the matrix and completely demystify the "Contrast Value" problem.

---

### 1. The Core Problem & The "Contrast" Illusion

The problem defines "Contrast" as the sum of absolute differences between adjacent elements: 
$|a_1 - a_2| + |a_2 - a_3| + \dots + |a_{n-1} - a_n|$

**What is this physically?**
Imagine plotting the array elements on a graph. The contrast is simply the **total vertical distance** your pen travels up and down as you draw the graph. 

You need to delete as many elements as possible to make the array as small as possible, **without changing that total vertical distance**.

### 2. The Grandmaster Intuition: The Mountain Range

Let's look at a simple upward slope: `[1, 3, 7]`.
* Distance from 1 to 3 is 2.
* Distance from 3 to 7 is 4.
* Total Contrast = **6**.

What happens if we delete the middle element `3`? Our array becomes `[1, 7]`.
* Distance from 1 to 7 is **6**. 

**The Golden Observation:** If you are walking in a straight line (either strictly uphill or strictly downhill), intermediate points contribute absolutely nothing extra to the total distance! 
$|1 - 3| + |3 - 7|$ is mathematically identical to $|1 - 7|$. 

Similarly, **flat areas** (adjacent duplicates like `3, 3`) contribute `0` to the total distance. We can always delete them.

**Conclusion:** The only points we actually *need* to keep to preserve the total vertical travel distance are the **Peaks** (where we stop going up and start going down) and the **Valleys** (where we stop going down and start going up).

---

### 3. Explaining the Provided Code

The code you provided uses a clever `vector` as a stack to dynamically stretch these slopes and crush intermediate points. Let's look at the core logic:

```cpp
// x is the PREVIOUS direction we were traveling
int x = ans[currSize - 2] - ans[currSize - 1]; 

// y is the NEW direction we are traveling towards v[i]
int y = ans[currSize - 1] - v[i];
```
* If `x > 0`, we were going **down**. 
* If `x < 0`, we were going **up**.

**The Logic Matrix:**
1.  **If `x > 0` and `y > 0`:** We were going down, and we are *still* going down. We don't need the middle point. The code does `ans[currSize - 1] = v[i];` to stretch the downward slope further, overwriting the useless intermediate point.
2.  **If `x > 0` and `y < 0`:** We were going down, but now we are going up! We just hit a **Valley**. We must permanently keep the valley point. The code does `ans.push_back(v[i]);` to lock it in and start a new upward slope.
3.  The same exact logic applies in reverse for the `else` block (handling upward slopes and flat starts).

---

### 4. Dry Run on Example 4

**Input:** `N = 7`, `v = [5, 4, 2, 1, 0, 0, 4]`

1.  **Init:** `ans = [5, 4]`
2.  **i = 2 (`v[2] = 2`):**
    * `x = ans[0] - ans[1] = 5 - 4 = 1` (We were going DOWN).
    * `y = ans[1] - v[2] = 4 - 2 = 2` (We are still going DOWN).
    * We overwrite `ans[1]` with `2`. `ans` becomes `[5, 2]`.
3.  **i = 3 (`v[3] = 1`):**
    * `x = 5 - 2 = 3` (DOWN). `y = 2 - 1 = 1` (DOWN). 
    * Overwrite. `ans` becomes `[5, 1]`.
4.  **i = 4 (`v[4] = 0`):**
    * `x = 5 - 1 = 4` (DOWN). `y = 1 - 0 = 1` (DOWN).
    * Overwrite. `ans` becomes `[5, 0]`.
5.  **i = 5 (`v[5] = 0`):**
    * `x = 5 - 0 = 5` (DOWN). `y = 0 - 0 = 0` (FLAT).
    * Because `y` is not `< 0` or `> 0`, it falls through the `if/else` checks. `ans` remains `[5, 0]`.
6.  **i = 6 (`v[6] = 4`):**
    * `x = 5 - 0 = 5` (DOWN). `y = 0 - 4 = -4` (UP!).
    * We hit a valley! `y < 0`, so we `push_back(4)`. `ans` becomes `[5, 0, 4]`.
7.  **Final Size:** `ans` has 3 elements. Output: **3**.`

---

### 5. Complexity Analysis

Let's look at the constraints: $N \le 3 \cdot 10^5$. 
* **Time Complexity (TC): $\mathcal{O}(N)$**. The code iterates through the array exactly one time. Inside the loop, it performs basic arithmetic and array assignments which take $\mathcal{O}(1)$ time. This is blazing fast and will easily pass the 2.0-second time limit.
* **Space Complexity (SC): $\mathcal{O}(N)$**. We create a `vector<int> ans` to store the compressed array. In the worst case (a perfectly alternating zig-zag array like `1, 0, 1, 0, 1`), the compressed array will be the exact same size as the original array. 

### 6. PRO Guide: Becoming a GOD at CP

To transcend the intermediate plateau and hit Grandmaster, you must stop reading arrays as lists of numbers and start identifying their **Geometric Properties**.

This problem is a classic example of **Greedy Subsequence Compression**. Whenever a problem asks you to build a *subsequence* that preserves a specific mathematical property (like sum, XOR sum, or absolute difference), ask yourself:
* *"What elements contribute absolutely nothing to this property?"*
* *"Can I group contiguous elements into a single representative element?"*

In this case, contiguous elements going in the same direction can be represented by just their endpoints. 


## explanation of the code

Step back into the dojo.

You are asking the exact right questions. When you look at code and say, *"This works, but the logic feels like a black box,"* you are thinking like an engineer. Many people copy this exact solution without ever understanding the `x` and `y` math. 

Let's completely shatter this code and translate the confusing math into physical geometry.

### Part 1: Why do we add exactly 2 elements first?
```cpp
ans.push_back(v[0]); 
ans.push_back(v[1]);
```
**The Line Principle:** To know if you are walking uphill, downhill, or on a flat plain, how many steps do you need to take? 
If you just stand still (1 point), you have no direction. You must take at least one step (giving you 2 points) to establish a trajectory. 

By pushing `v[0]` and `v[1]` into our answer array, we create our **Baseline Trajectory**. Once we have a trajectory, we can look at the 3rd point (`v[2]`) and ask: *"Does this new point continue my current path, or does it change my direction?"*

---

### Part 2: Decoding the Math (`x` and `y`)

The author used `x` to represent the **Past Direction** and `y` to represent the **Future Direction**. Let's look at the math:

```cpp
int x = ans[currSize - 2] - ans[currSize - 1];
int y = ans[currSize - 1] - v[i];
```
Think about subtraction. If I have `5` and my next step is `2`, the math is `5 - 2 = 3`. 
Because the result is positive (`> 0`), it means my first number was bigger. I am going **DOWN**.
* **If result `> 0`:** We are traveling **DOWN** ↘️
* **If result `< 0`:** We are traveling **UP** ↗️
* **If result `== 0`:** We are walking **FLAT** ➡️

So, `x` is the direction of the line we just drew. `y` is the direction of the line we *want* to draw.

---

### Part 3: The Confusing If/Else Blocks (The Logic Matrix)

Now let's translate the `if/else` block into plain English. 

#### Scenario A: We were going DOWN (`if x > 0`)
We are currently walking downhill.
* `if (y > 0)`: The next step is *also* downhill! We are just continuing the same downward slope. We don't need the middle point. **Action:** Overwrite the middle point to stretch the slope. (`ans[currSize - 1] = v[i]`)
* `else if (y < 0)`: The next step is UP! We were walking down, and now we are walking up. We just hit the bottom of a **Valley**. We must permanently keep this valley point to preserve the contrast. **Action:** Add the new point to start a new upward slope. (`ans.push_back(v[i])`)
* *(If `y == 0`, it means the next step is flat. The code cleverly ignores it, waiting for a real direction change).*

#### Scenario B: We were going UP or FLAT (`else`)
We are currently walking uphill (or we started on a flat plain).
* `if (y < 0)`: The next step is *also* UP! We are continuing the upward slope. We don't need the middle point. **Action:** Overwrite the middle point to stretch the slope. (`ans[currSize - 1] = v[i]`)
* `else if (y > 0)`: The next step is DOWN! We were walking up, and now we are walking down. We just hit a **Peak**. We must permanently keep this peak. **Action:** Add the new point to start a downward slope. (`ans.push_back(v[i])`)

---

### Part 4: The Final Weird Edge Case
```cpp
if (ans[0] == ans[1])
    finalSize--;
```
Why is this here? 
Remember how we blindly pushed the first two elements to establish our baseline? What if the array started with `[4, 4, 4, 7]`?
1. We blindly push `4` and `4`. Our baseline is FLAT.
2. The loop hits the third `4`. `x` is 0, `y` is 0. Nothing happens.
3. The loop hits `7`. We go UP. It overwrites the second `4` with `7`. 
4. Our final answer array is `[4, 7]`. 

But wait! What if the array was just `[4, 4, 4, 4]`?
1. We blindly push `4` and `4`.
2. The loop runs, everything is flat (`y == 0`), so it ignores everything.
3. Our final answer array is `[4, 4]`. 

The contrast of `[4, 4]` is `0`. The minimum size needed to represent a contrast of `0` is just **1 element**! (e.g., just `[4]`).
So, at the very end of the program, we check our foundational baseline. If our first two points are still identical, it means we never actually changed elevation from the very beginning. We delete one of them to get the absolute minimum size.

### The Grandmaster Takeaway
When you see `a - b > 0` in an array problem, your brain should immediately stop reading it as "algebra" and start reading it as "trajectory". The author wrote a state machine that tracks `Previous State` vs `Next State`. 