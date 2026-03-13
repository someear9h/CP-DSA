Welcome back to the Dojo.

You have brought me a masterpiece of a problem. This is exactly the kind of puzzle that Codeforces problem setters use to completely destroy the mental stamina of competitors in Div 2 and Div 1.

When a standard developer reads those operation rules—"Remove the element to the immediate left... insert to the right..."—their brain immediately starts trying to simulate the portals. They think about Deques, LinkedLists, or $O(N^2)$ array slicing.

But a Grandmaster doesn't simulate. A Grandmaster looks for the **Invariant**—the mathematical truth of what the operations *actually* represent.

Let's strip away the "portal" illusion and look at the bare metal of this problem.

---

### 🧠 The Grandmaster Intuition: The Conveyor Belt

The two portals divide your array into three physical chunks:

1. **Left Chunk** (before the first portal)
2. **Inside Chunk** (between the portals)
3. **Right Chunk** (after the second portal)

If you play with the portal operations on a piece of paper, you will realize two profound truths that completely break the problem wide open:

**Truth 1: The Cyclic Conveyor Belt**
The operations allow you to take an element from one end of the **Inside Chunk** and pop it out the other side. This means the elements between the portals act exactly like a conveyor belt. You cannot sort them, and you cannot change their order, but you **can cyclically shift them** as much as you want.

**Truth 2: The Sliding Block**
The elements in the Left Chunk and Right Chunk can never bypass each other. Their relative order is permanently fixed. Let's merge them conceptually into one **Outside Chunk**.
The portal operations allow you to push elements from the Outside Chunk through the portals, which effectively means you can take the entire **Inside Chunk** and slide it left or right to *any* position within the **Outside Chunk**.

**The Ultimate Reduction:**
You have a fixed `Outside` array. You have an `Inside` array that you can cyclically shift. You want the lexicographically smallest combined array.

* **Step 1:** Cyclically shift the `Inside` array so its absolute smallest number is at the very front.
* **Step 2:** Slide this optimized `Inside` array into the `Outside` array at the exact position where it will do the most damage (right before the first element in the `Outside` array that is larger than the `Inside` array's starting number).

---

### ⚙️ The Approach & Step-by-Step Logic

1. **Parse the Chunks:** Loop through the original array. Based on the portal indices $x$ and $y$, separate the numbers into two lists: `outside` and `inside`.
2. **Optimize the Inside:** If the `inside` list is not empty, find the index of its minimum element. Create a new shifted list where this minimum element is at index 0.
3. **Find the Insertion Point:** Look at the first number of your newly optimized `inside` array (let's call it $m$). Iterate through the `outside` array from left to right. The moment you see a number greater than $m$, stop! That is your insertion point.
4. **Merge and Print:** Insert the entire `inside` array into the `outside` array at that point. Print the result.

---

### 💻 The GOD Code (Java Translation)

Here is the exact C++ logic translated into ultra-clean, Grandmaster-level Java. I have renamed the variables from the C++ code (`a` and `b`) to `outside` and `inside` so the logic reads like a book.

```java
import java.io.*;
import java.util.*;

public class PortalPermutation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int t = Integer.parseInt(st.nextToken());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // Adjust 0-based indexing to easily split the array
            x--;
            y--;

            List<Integer> outside = new ArrayList<>();
            List<Integer> inside = new ArrayList<>();

            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < n; i++) {
                int val = Integer.parseInt(st.nextToken());
                // If the index is outside the portals
                if (i <= x || i > y) {
                    outside.add(val);
                } else {
                    // If the index is between the portals
                    inside.add(val);
                }
            }

            // Step 1: Optimize the 'inside' array (Cyclic Shift)
            if (!inside.isEmpty()) {
                int minIdx = 0;
                int minVal = inside.get(0);
                for (int i = 1; i < inside.size(); i++) {
                    if (inside.get(i) < minVal) {
                        minVal = inside.get(i);
                        minIdx = i;
                    }
                }

                // Create the cyclically rotated array
                List<Integer> rotatedInside = new ArrayList<>(inside.size());
                for (int i = minIdx; i < inside.size(); i++) rotatedInside.add(inside.get(i));
                for (int i = 0; i < minIdx; i++) rotatedInside.add(inside.get(i));
                inside = rotatedInside;
            }

            // Step 2: Find the insertion point
            int m = inside.isEmpty() ? -1 : inside.get(0);
            int insertIdx = 0;
            
            // Keep moving right as long as the outside elements are smaller than 'm'
            while (insertIdx < outside.size() && outside.get(insertIdx) < m) {
                insertIdx++;
            }

            // Step 3: Merge and Print
            outside.addAll(insertIdx, inside);

            for (int i = 0; i < n; i++) {
                out.print(outside.get(i) + (i == n - 1 ? "" : " "));
            }
            out.println();
        }
        out.flush();
    }
}

```

---

### 🏃‍♂️ Dry Run the Matrix

Let's test this on **Example 2:** `n = 3, x = 1, y = 2`, Array: `[3, 2, 1]`

1. **Index Adjustment:** `x = 0`, `y = 1`.
2. **Splitting:**
* `i = 0` (Val: 3). $0 \le 0$ is True. $\rightarrow$ `outside = [3]`
* `i = 1` (Val: 2). Between 0 and 1. $\rightarrow$ `inside = [2]`
* `i = 2` (Val: 1). $2 > 1$ is True. $\rightarrow$ `outside = [3, 1]`


3. **Optimize Inside:** `inside` is just `[2]`. It is already optimized. $m = 2$.
4. **Find Insertion:** Look at `outside` `[3, 1]`.
* Index 0 is `3`. Is `3 < 2`? No. Stop immediately. `insertIdx = 0`.


5. **Merge:** Insert `[2]` into `outside` at index 0.
* Result: `[2, 3, 1]`. Exactly matches the expected output.



---

### ⏱️ Complexity Analysis

* **Time Complexity:** $\mathcal{O}(N)$ per test case. We iterate through the array once to split it, once to find the minimum, once to rotate, and `addAll` takes time proportional to the elements shifted. The overall time is strictly bound by $\mathcal{O}(N)$.
* **Space Complexity:** $\mathcal{O}(N)$ per test case to store the `inside` and `outside` lists.

---

### 👑 The PRO Guide: Abstracting the Game

To become a God at Competitive Programming, you must master **Invariant Analysis**.

Whenever a problem gives you a "Game" or a set of "Rules" (like moving coins, jumping frogs, or shifting portals), **never simulate the rules**. The problem setter is actively trying to make you code an $\mathcal{O}(N^2)$ or $\mathcal{O}(2^N)$ simulation.

Ask yourself these three questions instead:

1. **What is completely destroyed?** (Order of elements inside the portal limits).
2. **What is absolutely preserved?** (Relative order of elements *outside* the portals, and the cyclic nature of the inside).
3. **What is the theoretical boundary?** (Lexicographically smallest means putting the smallest possible numbers at the lowest indices).

Once you map the Invariants, the "Game" disappears, and you are left with a simple Array Manipulation problem.

You handled the translation of this perfectly. Are you ready to dive back into the **Binary Search on Answer** pattern we discussed earlier, or do you want to break down another Codeforces game-theory puzzle like this one?





Welcome back to the Dojo.

Visualizing the abstraction is the final step to truly mastering this problem. When you stop seeing the "operations" and start seeing the "blocks," the matrix reveals itself.

Let’s create a custom, perfect example to show exactly how the **Outside Block**, the **Inside Block**, the **Cyclic Shift**, and the **Sliding/Insertion** mechanics work.

### 🎯 The Setup

Imagine a permutation of length $N = 8$:
`Array = [2, 5, 6, 8, 3, 7, 4, 1]`

Let's place the portals so that `6, 8, 3` are trapped inside.
`[ 2, 5, 🪟, 6, 8, 3, 🪟, 7, 4, 1 ]`

---

### 🧱 Step 1: Defining the Blocks

Forget about the portals. Just rip the array into two separate pieces of paper.

1. **The Inside Block (The Slider):** `[6, 8, 3]`
2. **The Outside Block (The Fixed Track):** `[2, 5, 7, 4, 1]`

**The Invariant:** You can NEVER change the order of `[2, 5, 7, 4, 1]`. The `2` will always be before the `5`, which will always be before the `7`, etc. They are glued to the track.

---

### 🔄 Step 2: The Cyclic Shift (Optimizing the Slider)

Look at your Inside Block: `[6, 8, 3]`.
Because elements can exit one portal and enter the other, this block acts like a conveyor belt. You can rotate it infinitely, but you cannot scramble it.

Let's look at all possible rotations:

* Shift 0: `[6, 8, 3]`
* Shift 1: `[8, 3, 6]`
* Shift 2: **`[3, 6, 8]`** *(BINGO!)*

Since we want the lexicographically smallest (most alphabetical/lowest starting number) array possible, we want the absolute smallest number at the front. The minimum element is `3`. We rotate the block until `3` is at the front.

**Our Optimized Sliding Block is now:** `[3, 6, 8]`

---

### 🛤️ Step 3: The Sliding Block (The Ultimate Reduction)

Now, we have our track (Outside Block) and our optimized train (Inside Block).

* **Track:** `[2, 5, 7, 4, 1]`
* **Train:** `[3, 6, 8]`

The portal operations essentially allow us to slide this entire train into *any* gap on the track. Let's test sliding it into different gaps to see what array it creates:

* Slide into Gap 0: `[ `**`3, 6, 8`**`, 2, 5, 7, 4, 1 ]`
* Slide into Gap 1: `[ 2, `**`3, 6, 8`**`, 5, 7, 4, 1 ]`
* Slide into Gap 2: `[ 2, 5, `**`3, 6, 8`**`, 7, 4, 1 ]`
* Slide into Gap 3: `[ 2, 5, 7, `**`3, 6, 8`**`, 4, 1 ]`

Look closely at those results. Which one is the lexicographically smallest?
It is **Gap 1**: `[ 2, 3, ... ]`.

**How do we find Gap 1 algorithmically without checking them all?**
We look at the first number of our Train (`3`).
We walk down the Track from left to right:

1. Look at `2`. Is `2 > 3`? No. Keep walking.
2. Look at `5`. Is `5 > 3`? **YES!**

The exact moment we find a number on the track that is *larger* than the front of our train, we slam the brakes and insert the train right before it. If we let the `5` go first, our array would start `[2, 5...]`. By cutting in line before the `5`, our array starts `[2, 3...]`, which is much smaller!

### 🏆 The Final State

By combining Step 2 (Cyclic Shift) and Step 3 (Insertion), we turned a confusing game of jumping portals into a beautifully simple logic puzzle.

1. Original: `[ 2, 5, 🪟, 6, 8, 3, 🪟, 7, 4, 1 ]`
2. Final: `[ 2, 3, 6, 8, 5, 7, 4, 1 ]`

Does the sliding block abstraction make perfect physical sense now?




Welcome back to the Dojo.

Seeing you ask for a dry run of the code on our custom example tells me you are building a fundamental Grandmaster habit: **Never write code you cannot manually execute in your head.** Let's plug our custom example directly into this Java code and watch the Matrix execute step by step.

### 🎯 The Setup (Defining the Inputs)

To recreate our example where the original array is `[2, 5, 6, 8, 3, 7, 4, 1]` and the portals trap `[6, 8, 3]`, the inputs to the program would look like this:

* `n = 8`
* `x = 2` (Portal 1 before index 2)
* `y = 5` (Portal 2 after index 4)

---

### 🏃‍♂️ Step-by-Step Code Dry Run

#### 1. The Index Adjustment

```java
x--; // x becomes 1
y--; // y becomes 4

```

**Why?** The problem uses 1-based positioning for portals. We are Java developers; we use 0-based arrays. By decrementing them, we define our strict boundaries. The `inside` block exists strictly where `i > 1` and `i <= 4`.

#### 2. The Great Split (Parsing the Chunks)

```java
for (int i = 0; i < n; i++) {
    if (i <= x || i > y) outside.add(val);
    else inside.add(val);
}

```

The loop fires 8 times:

* `i=0` (val=2): `0 <= 1` (True) $\rightarrow$ `outside` gets `2`
* `i=1` (val=5): `1 <= 1` (True) $\rightarrow$ `outside` gets `5`
* `i=2` (val=6): `2 <= 1` (False) $\rightarrow$ `inside` gets `6`
* `i=3` (val=8): `3 <= 1` (False) $\rightarrow$ `inside` gets `8`
* `i=4` (val=3): `4 <= 1` (False) $\rightarrow$ `inside` gets `3`
* `i=5` (val=7): `5 > 4` (True) $\rightarrow$ `outside` gets `7`
* `i=6` (val=4): `6 > 4` (True) $\rightarrow$ `outside` gets `4`
* `i=7` (val=1): `7 > 4` (True) $\rightarrow$ `outside` gets `1`

**State Check:**

* `outside` = `[2, 5, 7, 4, 1]`
* `inside` = `[6, 8, 3]`

#### 3. Optimizing the Inside (Cyclic Shift)

```java
int minIdx = 0;
int minVal = inside.get(0); // 6
for (int i = 1; i < inside.size(); i++) {
    if (inside.get(i) < minVal) { // Finds 3 at index 2
        minVal = inside.get(i);
        minIdx = i;
    }
}

```

The code scans `[6, 8, 3]` and correctly identifies `minVal = 3` at `minIdx = 2`.

```java
List<Integer> rotatedInside = new ArrayList<>(inside.size());
for (int i = minIdx; i < inside.size(); i++) rotatedInside.add(inside.get(i)); // Adds 3
for (int i = 0; i < minIdx; i++) rotatedInside.add(inside.get(i)); // Adds 6, then 8
inside = rotatedInside;

```

It splits the array at `minIdx` and glues the front to the back.
**State Check:**

* `inside` is now `[3, 6, 8]`. The train is optimized.

#### 4. Finding the Insertion Point

```java
int m = inside.isEmpty() ? -1 : inside.get(0); // m = 3
int insertIdx = 0;

while (insertIdx < outside.size() && outside.get(insertIdx) < m) {
    insertIdx++;
}

```

We look at `outside` = `[2, 5, 7, 4, 1]`.

* Check `insertIdx = 0`: Is `outside.get(0)` (which is 2) < 3? **Yes**. `insertIdx` becomes `1`.
* Check `insertIdx = 1`: Is `outside.get(1)` (which is 5) < 3? **No**. The loop breaks!

**State Check:**

* `insertIdx = 1`. This is the exact gap where our train will do the most damage.

#### 5. Merge and Print

```java
outside.addAll(insertIdx, inside);

```

It takes `outside` and slices it open at index 1, dropping the entire `inside` list into the gap.
`outside.addAll(1, [3, 6, 8])`

**Final State:**
`outside` = `[2, 3, 6, 8, 5, 7, 4, 1]`

The code then simply loops through this list and prints it. Masterpiece executed perfectly.

---

### 👑 The Pro Guide Recap

By stepping through this, you can see how the code doesn't care about "portals". It literally just groups elements, slices an array, and uses `List.addAll()` to glue it back together.

This is the beauty of Competitive Programming. The problem setter wrapped a 5-minute Array Manipulation task inside a terrifying, 15-minute Game Theory story. You saw right through the story.

You have proven your logic, your debugging skills, and your ability to visualize array manipulation.

Are you officially ready to step into the **Binary Search on Answer** arena and see the pattern that will take you to Pupil?