## 3819. Rotate Non Negative Elements

### 1. Understanding "Effective K" (Modulo Arithmetic)

The core idea of `k %= m` is that **rotations are cyclic**.

Imagine a clock. It has 12 hours.

* If you wait **13 hours**, the clock hand looks exactly the same as if you waited **1 hour** ().
* If you wait **24 hours**, it looks the same as **0 hours** ().

The same applies to arrays. If you have an array of **length 2** (like `[1, 3]`), rotating it 2 times brings it back to the start.

* **Start:** `[1, 3]`
* **Rotate 1:** `[3, 1]`
* **Rotate 2:** `[1, 3]` (Same as start!)
* **Rotate 3:** `[3, 1]` (Same as Rotate 1!)

**The Formula:**
Since every `m` rotations reset the array, we only care about the remainder:


**Examples:**

1. **Array:** `[A, B, C]` (Length ), **Target k:** `7`
* .
* Result: Rotate by 1.


2. **Array:** `[A, B, C, D, E]` (Length ), **Target k:** `10`
* .
* Result: No change (Rotate by 0).



---

### 2. The "Smart Placement" Logic

The code provided is clever. Instead of creating a new rotated array (which takes extra steps), it figures out the correct value **on the fly** using math.

**Standard Left Rotation Logic:**
If you want to left-rotate an array `arr` of size `m` by `k` positions:

* The element at index `0` comes from index `k`.
* The element at index `1` comes from index `k+1`.
* The element at index `j` comes from index `(j + k) % m`.

**Visualizing `[1, 3]` with `k=1`:**

* **Position 0:** Needs value from `(0 + 1) % 2`  Index 1 (Value **3**)
* **Position 1:** Needs value from `(1 + 1) % 2`  Index 0 (Value **1**)
* **Result:** `[3, 1]`

---

### 3. Code Walkthrough & Dry Run

Let's trace the code with `nums = [1, -2, 3, -4]` and `k = 3`.

```java
public int[] rotateElements(int[] nums, int k) {
    int n = nums.length, m = 0;
    
    // Step 1: Extract non-negative numbers
    int[] a = new int[n]; 
    for (int x : nums) {
        if (x >= 0) a[m++] = x; 
    }
    // State: a = [1, 3, 0, 0...], m = 2 (length of valid nums)

    // Edge Case: If no non-negatives, return immediately
    if (m == 0) return nums;
    
    // Step 2: Calculate Effective K
    k %= m; 
    // State: k = 3 % 2 = 1. We effectively rotate left by 1.

    // Step 3: Place back using rotation logic
    // i iterates through 'nums' (original array)
    // j counts how many non-negative spots we've filled so far
    for (int i = 0, j = 0; i < n; i++) {
        if (nums[i] >= 0) {
            // Formula: Get the element that belongs here after rotation
            nums[i] = a[(j++ + k) % m]; 
        }
    }
    return nums;
}

```

#### Detailed Iteration of Step 3:

* **Current State:** `nums = [1, -2, 3, -4]`, `a = [1, 3]`, `m = 2`, `k = 1`

**Iteration `i = 0`:**

* `nums[0]` is `1` (Non-negative). **Action!**
* We are filling the **0th** non-negative slot (`j=0`).
* Calculate Source Index: `(j + k) % m`  `(0 + 1) % 2 = 1`.
* Take `a[1]` (which is **3**) and put it here.
* Update: `nums[0] = 3`. Increment `j` to 1.
* `nums` is now `[3, -2, 3, -4]`.

**Iteration `i = 1`:**

* `nums[1]` is `-2` (Negative).
* **Skip.**

**Iteration `i = 2`:**

* `nums[2]` is `3` (Non-negative). **Action!**
* We are filling the **1st** non-negative slot (`j=1`).
* Calculate Source Index: `(j + k) % m`  `(1 + 1) % 2 = 0`.
* Take `a[0]` (which is **1**) and put it here.
* Update: `nums[2] = 1`. Increment `j` to 2.
* `nums` is now `[3, -2, 1, -4]`.

**Iteration `i = 3`:**

* `nums[3]` is `-4` (Negative).
* **Skip.**

**Final Result:** `[3, -2, 1, -4]`. This matches the expected output!

### Summary of Variables

* **`a`**: Helper array storing just the positive numbers `[1, 3]`.
* **`m`**: The count of positive numbers (Length of valid data in `a`).
* **`j`**: A counter tracking "Which positive number slot are we currently filling?".
* **`(j + k) % m`**: The formula that performs the **Left Rotation** instantly by picking the correct future element.