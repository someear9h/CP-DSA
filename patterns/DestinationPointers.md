While most people broadly call this "Two Pointers," that name is too generic. In the Grandmaster dojo, we call this specific technique the **Decoupled Read/Write Pointers** pattern (or "Destination Pointers").

Most beginners think of array iteration as a single action: you look at an index, and you do something to that index. This pattern completely shatters that limitation by separating the "eyes" (Read Pointers) from the "hands" (Write Pointers).

Here is your ultimate playbook for mastering it.

---

### 📖 The Read/Write Pointers Guide

#### 1. How to SPOT the Pattern

You will know a problem requires Decoupled Read/Write Pointers when you see these exact signals:

* **The Goal:** You are asked to "rearrange," "partition," "interleave," or "separate" an array based on a specific condition.
* **The Categories:** Elements are being sorted into distinct buckets (e.g., Positive vs. Negative, Evens vs. Odds, Zeros vs. Non-Zeros).
* **The Constraint:** You must maintain the **relative order** of the elements as they originally appeared.
* **The Complexity:** An $\mathcal{O}(N^2)$ brute force is too slow, and standard $\mathcal{O}(N \log N)$ sorting destroys the relative order. You need an $\mathcal{O}(N)$ linear sweep.

#### 2. The PHILOSOPHY (How to Approach It)

The core intuition is to treat your array like a factory sorting line.
You do not care where the elements currently are. You only care about **where they belong**.

* **The Eyes (The Loop):** You use a standard `for` loop to act as a conveyor belt, reading elements one by one.
* **The Hands (The Write Pointers):** You initialize standalone variables (`pos`, `neg`, `left`, `right`) whose *only job* is to remember the next empty slot for a specific category.
* **The Action:** When your "Eyes" see an element that belongs in Bucket A, your "Hand A" places it in its dedicated slot, and *only* Hand A moves forward.

#### 3. The EXECUTION (How to Solve It)

Every solution in this family follows a strict 3-step formula:

1. **Allocate the Destinations:** Determine if you need an auxiliary `ans` array (like in your two codes) or if you are modifying the array in-place.
2. **Plant the Write Pointers:** Initialize your write pointers at their respective starting lines.
* If interleaving (like `rearrangeArray`), plant them at `0` and `1`.
* If splitting front/back (like `pivotArray`), plant them at `0` and `N - 1`.


3. **The Blind Write:** Iterate through the array. Check the condition. Write to the destination pointer, and immediately post-increment/decrement it (e.g., `ans[pos++] = nums[i]`).

---

### ⚔️ The Proving Grounds: Your Next Challenges

To permanently lock this pattern into your muscle memory, you need to apply it to its variations. Here are four essential problems, ranked from easiest to legendary.

#### 1. Move Zeroes (LeetCode 283)

* **The Prompt:** Given an array, move all `0`s to the end while maintaining the relative order of the non-zero elements. **Must be done in-place without a copy of the array.**
* **Why it fits:** This is the purest form of the pattern. You only need one Write Pointer (`insertPos`). As you read left-to-right, if you see a non-zero, write it to `insertPos` and increment. Fill the remaining array with zeros at the end.

#### 2. Sort Array By Parity II (LeetCode 922)

* **The Prompt:** Given an array of half evens and half odds, sort the array so that whenever `nums[i]` is odd, `i` is odd, and whenever `nums[i]` is even, `i` is even.
* **Why it fits:** This is the exact twin sibling to your `rearrangeArray` code. Use `even = 0` and `odd = 1`.

#### 3. Merge Sorted Array (LeetCode 88)

* **The Prompt:** You are given two sorted integer arrays `nums1` and `nums2`. Merge `nums2` into `nums1` as one sorted array. `nums1` has enough empty space (zeros) at the very end to hold `nums2`.
* **Why it fits:** This is a brilliant twist on the pattern. Because the empty space is at the *end* of `nums1`, if you use Read/Write pointers from left-to-right, you will overwrite your own data! You must plant your Write Pointer at the absolute end of the array and read everything **backwards**.

#### 4. Sort Colors (LeetCode 75) - *The Grandmaster Test*

* **The Prompt:** Given an array with $n$ objects colored red, white, or blue (represented by `0`, `1`, and `2`), sort them **in-place** so that objects of the same color are adjacent, with the colors in the order `0`, `1`, and `2`. (Also known as the Dutch National Flag problem).
* **Why it fits:** You must partition the array into *three* sections in a single pass, in-place. You need a `left` write pointer for 0s, a `right` write pointer for 2s, and an `i` read pointer that dynamically swaps elements on the fly.
