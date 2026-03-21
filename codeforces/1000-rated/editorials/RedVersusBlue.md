### 1. The Core Intuition (Walls and Buckets)

Think of the blue wins (`B`) as physical brick walls. 
If you have `1` wall, it splits a room into `2` spaces (or buckets). 
If you have `3` walls, they split the room into `4` buckets.
* **The Rule:** `b` walls always create exactly `b + 1` buckets.

Now, you have an unlimited pile of `r` red blocks. Your goal is to distribute these `r` blocks into the `b + 1` buckets so that no bucket gets too full. To minimize the maximum bucket, you must distribute them **as evenly as mathematically possible.**

### 2. The Math (Division and Remainder)

If you have `r` red blocks and `b + 1` buckets, how many does each bucket get?
You use elementary school division:
1. **The Base:** Every single bucket is guaranteed to get `r / (b + 1)` red blocks.
2. **The Leftovers:** Sometimes it doesn't divide perfectly. The remainder is `r % (b + 1)`. These are the leftover red blocks.
3. **The Distribution:** You just hand out exactly `1` leftover block to the first few buckets until you run out.

### 3. Dry Run (Visualizing the Engine)

Let's trace **Test Case 2:** `n = 6`, `r = 5`, `b = 1`

* **Walls (`B`):** 1.
* **Buckets:** `b + 1` = 2.
* **Base `R` per bucket:** `5 / 2` = **2**.
* **Leftover `R`s:** `5 % 2` = **1**.

**Building the string:**
* **Bucket 1:** Gets the Base (2) + 1 Leftover = `3` 'R's. $\rightarrow$ `RRR`
* *Place a Wall:* $\rightarrow$ `B`
* **Bucket 2:** Gets the Base (2) + 0 Leftovers = `2` 'R's. $\rightarrow$ `RR`
* **Result:** `RRRBRR`. (Flawless optimal distribution).

Let's trace **Test Case 3:** `19 13 6`
* **Buckets:** `6 + 1` = 7.
* **Base `R`:** `13 / 7` = **1**.
* **Leftovers:** `13 % 7` = **6**.
* The first 6 buckets get `1 + 1 = 2` 'R's. The last bucket gets `1` 'R'.
* **Result:** `RR B RR B RR B RR B RR B RR B R`


### Complexity Analysis
* **Time Complexity (TC):** $O(N)$. We print exactly $N$ characters in total. We don't use any nested simulation loops.
* **Space Complexity (SC):** $O(1)$. We output the characters directly to the console stream, meaning we don't even need to use memory to store a string!

You recognized the trap of brute-forcing, which is a massive leap forward. You just needed the math blueprint to build the answer directly. 

Are we ready to finally tackle a **Sliding Window** array problem, or do you want to explore another Constructive Array/String trap?