**What if we walk backwards?**
If you iterate from right to left (from the future to the past), you *already know* where the `g` is! You just carry its location with you as you walk back. The moment you step on an `r`, you simply subtract your current position from the `g` position you are carrying.

No booleans. Just one integer.

### The Step-by-Step Matrix

1. **Double the String:** To handle the "cycle" safely, we physically append the string to itself. `s += s`. Now a length `5` string becomes length `10`.
2. **The Time Machine:** Start at the very end of the new string (`2*n - 1`) and walk backwards to `0`.
3. **The State Tracker:** Keep a single integer variable called `last_g_pos` (initialized to `-1`). 
4. **The Update:** Every time you see a `g`, update `last_g_pos = i`.
5. **The Measurement:** Every time you see your target color `c`, and you have a valid `last_g_pos`, calculate the distance: `last_g_pos - i`. Keep the maximum.

### Dry Run (Visualizing the Time Machine)
Let's use the exact example that confused you: `n = 5`, `c = 'r'`, `s = "rggry"`.
After doubling: `s = "rggryrggry"`. Length is 10. We loop `i` from 9 down to 0.

* `i = 9` ('y'): Do nothing. `last_g_pos = -1`.
* `i = 8` ('r'): Do nothing. `last_g_pos = -1`.
* `i = 7` ('g'): Update! `last_g_pos = 7`.
* `i = 6` ('g'): Update! `last_g_pos = 6`.
* `i = 5` ('r'): Target color found! Distance = `6 - 5 = 1`. `max_dist` = **1**.
* `i = 4` ('y'): Do nothing. 
* `i = 3` ('r'): Target color found! Distance = `6 - 3 = 3`. `max_dist` = max(1, 3) = **3**.
* `i = 2` ('g'): Update! `last_g_pos = 2`.
* `i = 1` ('g'): Update! `last_g_pos = 1`.
* `i = 0` ('r'): Target color found! Distance = `1 - 0 = 1`. `max_dist` = max(3, 1) = **3**.

**Final Answer: 3.**

Notice how incredibly clean that is? We didn't need to track "first r" or "found g". Because we walk backwards, the `last_g_pos` variable is *guaranteed* to be the nearest green light to the right of our current position. 

**The Grandmaster Takeaway:**
Whenever a problem asks you to find the "nearest element to the right" (like the next greater element, the next green light, the next prime number), your first instinct should ALWAYS be to **iterate the array backwards**. It eliminates almost all complex state tracking. 