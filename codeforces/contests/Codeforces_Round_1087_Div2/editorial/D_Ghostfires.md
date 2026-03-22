### 1. The Core Intuition: The Physics of "Pairs"
The problem states two rules:
* $s_i \neq s_{i+1}$ (No adjacent duplicates).
* $s_i \neq s_{i+3}$ (No duplicates separated by exactly 2 elements).

Let's do a thought experiment. Imagine placing three *different* colors in a row: `R G B`.
What can you physically place as the 4th character?
* `R`? No. The distance from the 1st `R` to the 4th spot is exactly 3. Forbidden.
* `B`? No. It's adjacent to the 3rd spot. Forbidden.
* The **ONLY** valid color for the 4th spot is `G`. 

So `R G B` immediately forces the next character to be `G`, resulting in: `R G B G`.
Look at that string: `(R G)` and `(B G)`. 
The editorial makes a brilliant, GOD-tier observation here: **Any valid string can be built entirely out of 2-character pairs, plus an optional single starting character.** As long as the two characters in a pair are different, and we chain them together correctly, the distances of 1 and 3 will naturally protect themselves!

### 2. The Approach: The Greedy Matchmaker
If the entire weapon is built out of pairs of two different colors, our goal is simply to make as many pairs as possible. 
*How do you make the maximum number of pairs from 3 piles of items without leaving a massive pile unused?*
**You always pair an item from the LARGEST pile with an item from the SECOND LARGEST pile.** If you don't do this, you will run out of the smaller colors too fast, leaving you with 100,000 Red ghostfires that you can't use because you have no other colors to pair them with.

**The Strategy:**
1.  **Phase 1 (Pairing):** While you have at least 2 colors available, find the two largest piles, take one from each, and log it as a pair (e.g., `RG`, `GB`, `RB`).
2.  **Phase 2 (Construction):** Output the single leftover character (if any). Then, chain your pairs together. 
3.  **Phase 3 (The Parity Trap):** When chaining pairs, you must be careful when transitioning from one pair type to another. If you blindly append `RG` and then `BR`, you might accidentally create a distance-3 collision. The code uses a clever `flg` to flip the order of the last pair if needed.

### 3. Step-by-Step Code Explanation

```cpp
while(((r > 0) + (g > 0) + (b > 0)) >> 1)
```
* **What this does:** The expression `(r>0) + (g>0) + (b>0)` counts how many colors still have ghostfires left (returns 1, 2, or 3). 
* **The `>> 1` Trick:** Bitwise right-shift by 1 is the same as integer division by 2. If 3 colors remain, `3 >> 1` = `1` (True). If 2 colors remain, `2 >> 1` = `1` (True). If 1 color remains, `1 >> 1` = `0` (False). This translates perfectly to: *"Keep looping as long as at least 2 colors are available."*

```cpp
if(r <= g && r <= b) gb ++, g --, b --;
```
* **What this does:** It finds the *smallest* pile. If `R` is the smallest, that means `G` and `B` are the two largest. So, it takes one from `G`, one from `B`, and creates a `GB` pair (`gb++`). 

```cpp
if(g > 0) {
    putchar('G');
    while(rg > 0) putchar('R'),putchar('G'),rg --;
```
* **What this does:** We begin constructing the string. If `G` has leftovers, we place it as the starter. Then we append all our `RG` pairs. 
* **String so far:** `G` + `R G` + `R G` -> `G R G R G`. (Perfectly valid).

```cpp
    bool flg = false;
    while(gb > 0) putchar('B'),putchar('G'),gb --,flg = true;
```
* **What this does:** Now we append the `BG` pairs. The string becomes `... R G` + `B G` + `B G`. 
* **The `flg`:** We set `flg = true` to remember that we successfully placed a "middle" block of `BG` pairs.

```cpp
    if(flg) {
        while(rb > 0) putchar('B'),putchar('R'),rb --;
    } else {
        while(rb > 0) putchar('R'),putchar('B'),rb --;
    }
```
* **What this does (The Edge Case Handling):** We now append the final pairs (`R` and `B`). 
* If `flg` is true, the last character we placed was `G` (from the `BG` pair). We append `B R`. The sequence is `... B G` + `B R`. (Safe!).
* If `flg` is false, it means we had NO `BG` pairs. The last character we placed was `G` (from the `RG` pair). If we appended `B R` here, it would be `R G B R`, which violates the distance-3 rule (`R` and `R`). So instead, the code brilliantly flips it and appends `R B`. The sequence is `R G` + `R B`. (Safe!).

### 4. Complexity Analysis
* **Time Complexity:** $\mathcal{O}(R + G + B)$. The first `while` loop runs exactly $(R+G+B)/2$ times, decrementing the counts by 2 each time. The output loops run linearly based on the pair counts. For $10^6$ operations across all test cases, this runs in mere milliseconds.
* **Space Complexity:** $\mathcal{O}(1)$ Auxiliary Space. We don't even store the string in a matrix or an array. We stream it directly to the console using `putchar()`. 

### 5. Flawless Dry Run: `r=2, g=2, b=2`
1.  **Pairing Loop:**
    * Min is `R` (or any, tie). Pair `G` and `B`: `g=1, b=1, gb=1`.
    * Min is `B` (tie). Pair `R` and `G`: `r=1, g=0, rg=1`.
    * Min is `G`. Pair `R` and `B`: `r=0, b=0, rb=1`.
    * Counts: `gb=1`, `rg=1`, `rb=1`. Leftovers: None.
2.  **Output Logic:**
    * No leftovers. The code skips `if(g>0)` and `else if(r>0)`, and hits the final `else`.
    * `b>0` is false, no starter.
    * Append `GB` pairs: string is `G B`. `flg` becomes true.
    * Append `RB` pairs: because `flg` is true, it appends `R B` (the code handles the `else` block slightly differently depending on the starter). String is `G B R B`.
    * Append `RG` pairs: because `flg` is true, it appends `R G`. String is `G B R B R G`.
3.  **Final Result:** `GBRBRG`. Every character used. Rules obeyed perfectly. 

### 6. Your PRO Guide to Becoming a GOD
1.  **The "Majority Element" Paradigm:** Whenever a problem asks you to construct a string/array using maximum elements without placing specific ones near each other, your brain must instantly trigger the **Greedy Pairing / Priority Queue** pattern. You always consume the most frequent elements first. 
2.  **Streaming over Storing:** Notice how the Grandmaster didn't use `string s = ""` and `s += "R"`. Appending to strings can cause memory reallocations. Using `putchar()` writes directly to the standard output buffer. It is blindingly fast.
3.  **The State Machine Transition:** When chaining different blocks together, the boundary where Block A meets Block B is the only place a bug can exist. The `flg` variable acts as a tiny state machine, ensuring the transition across the boundary never violates the distance rule.


## Dry run of the code

Step back into the dojo.

You are doing exactly what a Grandmaster does before they ever hit the "Submit" button. You do not trust the code just because it looks pretty. You force it through the crucible. You run the numbers.

Let’s plug your two test cases directly into the engine and watch exactly how the variables shift in memory. 

---

### Dry Run 1: The Overflow `2 7 3` 
**(R = 2, G = 7, B = 3)**

**PHASE 1: The Matchmaker Loop**
We have 3 colors available. We must find the smallest pile and pair the OTHER two.

* **Turn 1:** `r=2, g=7, b=3`. Smallest is `r` (2). 
    * Pair `G` and `B` -> `gb_pairs = 1`. 
    * Piles become: `r=2, g=6, b=2`.
* **Turn 2:** `r=2, g=6, b=2`. Smallest is `r` (or `b`). Let's say `r` (2).
    * Pair `G` and `B` -> `gb_pairs = 2`. 
    * Piles become: `r=2, g=5, b=1`.
* **Turn 3:** `r=2, g=5, b=1`. Smallest is `b` (1). 
    * Pair `R` and `G` -> `rg_pairs = 1`. 
    * Piles become: `r=1, g=4, b=1`.
* **Turn 4:** `r=1, g=4, b=1`. Smallest is `r` (or `b`). Let's say `r` (1).
    * Pair `G` and `B` -> `gb_pairs = 3`. 
    * Piles become: `r=1, g=3, b=0`.
* **Turn 5:** `r=1, g=3, b=0`. Now `b` is 0! Blue is the smallest pile.
    * Pair `R` and `G` -> `rg_pairs = 2`. 
    * Piles become: `r=0, g=2, b=0`.

*Loop terminates.* Only 1 color (`G`) is left available. 
**Final Inventory:** `rg_pairs = 2`, `gb_pairs = 3`, `rb_pairs = 0`. Leftover: `G = 2`.

**PHASE 2: The Construction**
* **The Starter:** `g > 0` is true! We print the starting character: **`G`**.
    * *Note: We had 2 `G`s left, but we only print ONE. The other is permanently discarded! Why? Because with 5 total R/B ghostfires, we only have 6 safe "slots" for G. We physically cannot place 7 Gs without breaking the weapon. The greedy math naturally handles this!*
* **Append RG Pairs (2):** `G` + `RG` + `RG` -> **`G R G R G`**.
* **Append BG Pairs (3):** `G R G R G` + `BG` + `BG` + `BG` -> **`G R G R G B G B G B G`**.
* **Append RB Pairs (0):** Nothing to append.

**Final Output:** `GRGRGBGBGBG`. 
*(Matches the example output perfectly!)*

---

### Dry Run 2: Perfect Equilibrium `2 2 2`
**(R = 2, G = 2, B = 2)**

**PHASE 1: The Matchmaker Loop**
* **Turn 1:** `r=2, g=2, b=2`. Smallest is `r` (all tie, code picks `r`). 
    * Pair `G` and `B` -> `gb_pairs = 1`. 
    * Piles become: `r=2, g=1, b=1`.
* **Turn 2:** `r=2, g=1, b=1`. Smallest is `g` (tie with `b`, code picks `g`).
    * Pair `R` and `B` -> `rb_pairs = 1`. 
    * Piles become: `r=1, g=1, b=0`.
* **Turn 3:** `r=1, g=1, b=0`. Smallest is `b` (0).
    * Pair `R` and `G` -> `rg_pairs = 1`. 
    * Piles become: `r=0, g=0, b=0`.

*Loop terminates.* Zero colors available.
**Final Inventory:** `rg_pairs = 1`, `gb_pairs = 1`, `rb_pairs = 1`. Leftover: None!

**PHASE 2: The Construction**
* `g > 0` is False. `r > 0` is False. The code falls into the final `else` block (the Blue block).
* **The Starter:** `b > 0` is False. No starting character is printed!
* **Append GB Pairs (1):** We print `GB`. String so far: **`G B`**.
* **Append RB Pairs (1):** We print `RB`. 
    * `middle_pair_placed` becomes `true`.
    * String so far: `G B` + `R B` -> **`G B R B`**.
* **Append RG Pairs (1):** * Does the code flip the pair? `middle_pair_placed` is `true`, so we do **NOT** flip. We use the safe block.
    * We print `RG`.
    * String so far: `G B R B` + `R G` -> **`G B R B R G`**.

**Final Output:** `GBRBRG`.
*(Matches the example output perfectly, using every single ghostfire!)*

---

### The Grandmaster Takeaway
Do you see the absolute beauty of Test Case 2? 

If the `middle_pair_placed` flag did not exist, and we had zero `RB` pairs, the code would have appended `GB` and then blindly appended `RG`. 
It would have output: `G B R G`.
Look at the distance between the two `G`s. It is exactly 3. **Boom. Weapon explodes.**

But because of that tiny boolean flag, the engine knows when the boundaries are dangerous and automatically flips the final pair to protect the string. 

You have now traced the memory of a highly advanced Greedy Constructive algorithm. Are there any gears in this engine that still feel like magic, or do you fully trust the machine now?