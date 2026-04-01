Step back into the dojo.

This is a phenomenal problem. It looks like a massive simulation, but beneath the surface, it is a masterclass in **Dimension Reduction** and **Mathematical Contribution**. 

Let's break the matrix and upgrade your pattern recognition.

### 1. The Matrix Vision (Dimension Independence)
The problem asks for the sum of $|a_1 - b_1| + |a_2 - b_2| + \dots + |a_m - b_m|$ across all pairs of players. 

**The Grandmaster Insight:** Notice that column 1 ($a_1, b_1$) has absolutely nothing to do with column 2 ($a_2, b_2$). The math for the first number on the cards never mixes with the second number. 
Therefore, we completely stop looking at the cards as 2D objects. We just split the deck into $m$ independent 1D arrays (columns). If we can find the sum of absolute differences for *one* column, we just loop that logic $m$ times and add it all together!

### 2. The $\mathcal{O}(N^2)$ to $\mathcal{O}(N)$ Math Hack
Now we only have a 1D array. Let's say column 1 is `[1, 7, 3]`. We need the sum of differences between all pairs. 
If we use a double `for` loop, it takes $\mathcal{O}(N^2)$ time. For $N=300,000$, $\mathcal{O}(N^2)$ is $9 \times 10^{10}$ operations. Your code will instantly **Time Limit Exceeded (TLE)**.

**The "Contribution" Trick:**
Instead of calculating pairs ($7-3$, $7-1$, $3-1$), let's ask: *How many times does a specific number get added, and how many times does it get subtracted?*

To do this, we **Sort the array**: `[1, 3, 7]`.
* Look at **7** (Index 2). It is the largest. When paired with the 2 numbers to its left (1 and 3), it will always be the positive number. It is added 2 times. It has 0 numbers to its right, so it is subtracted 0 times.
* Look at **3** (Index 1). It has 1 number to its left, so it is added 1 time. It has 1 number to its right (the 7), so it is subtracted 1 time.
* Look at **1** (Index 0). It has 0 numbers to its left (added 0 times). It has 2 numbers to its right (subtracted 2 times).

**The Formula:** In a zero-indexed sorted array of size $N$, an element at index $j$ has exactly $j$ elements smaller than it, and exactly $(N - 1 - j)$ elements larger than it.
Its contribution to the final sum is simply:
`+ (Element * j) - (Element * (N - 1 - j))`

### 3. Constraint & Complexity Analysis
* **Time Complexity (TC):** We rotate the matrix in $\mathcal{O}(N \cdot M)$. We sort $M$ arrays of size $N$, which takes $\mathcal{O}(M \cdot N \log N)$. Finally, we run our formula loop in $\mathcal{O}(N \cdot M)$. The dominant factor is the sorting: **$\mathcal{O}(M \cdot N \log N)$**. Given $N \cdot M \le 3 \cdot 10^5$, this safely runs in about $0.05$ seconds (well under the 1-second limit).
* **Space Complexity (SC):** We store the transposed matrix in a 2D vector of size $M \times N$. **$\mathcal{O}(N \cdot M)$**.

### 4. Code Dry Run (Example 1)
$N = 3$ cards, $M = 5$ numbers per card.
Card 1: `1 4 2 8 5`
Card 2: `7 9 2 1 4`
Card 3: `3 8 5 3 1`

**Step 1: The code transposes the matrix into $M$ rows (columns become rows).**
Row 1 (from Col 1): `[1, 7, 3]`
Row 2 (from Col 2): `[4, 9, 8]`
*(Skipping the rest to keep it concise)*

**Step 2 & 3: Sort and apply the math trick on Row 1.**
Sort Row 1: `[1, 3, 7]`
* `j = 0` (Value 1): `ans -= 1 * (3 - 0 - 1) -> ans -= 2`. `ans += 1 * 0`. (Current: -2)
* `j = 1` (Value 3): `ans -= 3 * (3 - 1 - 1) -> ans -= 3`. `ans += 3 * 1 -> ans += 3`. (Current: -2)
* `j = 2` (Value 7): `ans -= 7 * (3 - 2 - 1) -> ans -= 0`. `ans += 7 * 2 -> ans += 14`. (Current: 12)
The sum for column 1 is $12$. (If you do the manual pairs $|1-7| + |1-3| + |7-3| = 6 + 2 + 4 = 12$). Flawless.

---

### 5. The PRO Guide: Evolving to a CP GOD
Right now, you are building a rock-solid foundation. As you push past the 1000-rating bracket to crack those 2000-level problems for tests like HackWithInfy, the secret isn't jumping straight into Segment Trees or Fenwick Trees. It is mastering *these* exact mathematical observations. 

Recognizing this "Sorting + Contribution" trick is just like recognizing your Peak-Valley patterns. 
1. **Never simulate pairs:** Whenever a problem asks for "sum of pairs" or "all subarrays", you NEVER write an $\mathcal{O}(N^2)$ loop. You immediately ask: *"How much does `arr[i]` contribute to the final answer?"*
2. **Break Dimensions:** If a problem features a 2D grid but the rules apply to rows and columns independently, split the problem. Solve 1D, then multiply or add. 
3. **Sort to unlock Math:** Absolute values $|a - b|$ are annoying because they require `if` statements. Sorting completely destroys the absolute value because you mathematically guarantee that everything on the right is larger than everything on the left.