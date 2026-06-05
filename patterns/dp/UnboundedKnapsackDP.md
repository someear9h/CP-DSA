## Pattern: Unbounded Knapsack DP (Infinite Supply)

Every problem in this pattern follows this exact flow. If you master this state machine, you will never struggle with an "infinite supply" DP problem again.

Found it on: LC 322 Coin Change, LC 518 Coin Change II, LC 279 Perfect Squares, LC 983 Minimum Cost For Tickets, GeeksForGeeks Rod Cutting.

```text
STEP 1: Identify the "Infinite Supply to hit a Target" smell.
        
        You are given an array of items (coins, rods, items, weights).
        You need to reach a specific TARGET (amount, budget, weight).
        Crucial word: "Unlimited copies", "Any number of times", or "Reuse".
        
        Ask: "Am I trying to maximize/minimize a value to hit a target, 
              and can I pick the exact same item multiple times?"

STEP 2: Define the State Variables.
        
        You only ever need TWO variables:
        1. index 'i' (Which item am I currently evaluating?)
        2. 'target' (How much capacity/budget do I have left?)
        
        Ask: "If I freeze time, what two pieces of information completely 
              describe my current situation?"

STEP 3: The Base Cases (Success vs. Failure).
        
        Target limits dictate the base cases:
        - Exact Target (e.g., Coin Change): if(target == 0) return 0;
        - At Most Target (e.g., Items Budget): if(i == N) return 0;
        
        Failure cases must return a "Safe Infinity":
        - MINIMIZATION: return (int) 1e9;
        - MAXIMIZATION: return (int) -1e9;
        
        Ask: "What does it mathematically mean if I hit exactly 0? 
              What if I run out of items but still have target left?"

STEP 4: The "Skip" vs "Pick" Transitions.
        
        SKIP: Move to the next item, target stays the same.
              skip = f(i + 1, target)
              
        PICK: Take the item, reduce the target, BUT STAY ON THE SAME ITEM.
              pick = value + f(i, target - weight[i])
              
        *THE GRANDMASTER SECRET:* Notice it is f(i, ...), NOT f(i+1, ...).
        Staying on 'i' is the literal definition of "unlimited supply." 
        It gives you the option to pick it again on the very next recursive call!

STEP 5: Transition Pruning (Look before you leap).
        
        Never let 'target' become negative. Wrap the PICK transition:
        if (target >= weight[i]) {
            pick = value + f(i, target - weight[i]);
        }

```

**That's it. Every Unbounded Knapsack problem. No exceptions.**

---

## The Master Matrix: Spotting the Disguises

```text
╔══════════════════════╦═══════════════╦═════════════════╦══════════════════════════════════════════╗
║ PROBLEM              ║ ITEMS (W, V)  ║ TARGET          ║ THE UNBOUNDED TRANSITION (PICK)          ║
╠══════════════════════╬═══════════════╬═════════════════╬══════════════════════════════════════════╣
║ LC 322 Coin Change   ║ W: coin value ║ exact amount    ║ min: 1 + dp(i, amount - coin[i])         ║
║ (Min Coins)          ║ V: 1 coin     ║                 ║ (stay on i to use same coin again)       ║
╠══════════════════════╬═══════════════╬═════════════════╬══════════════════════════════════════════╣
║ LC 518 Coin Change II║ W: coin value ║ exact amount    ║ sum: dp(i, amount - coin[i]) + skip      ║
║ (Count Ways)         ║ V: combinations                 ║ (count ways, so we ADD pick and skip)    ║
╠══════════════════════╬═══════════════╬═════════════════╬══════════════════════════════════════════╣
║ Rod Cutting (GFG)    ║ W: length     ║ exact length N  ║ max: price[i] + dp(i, N - length[i])     ║
║ (Max Profit)         ║ V: price      ║                 ║ (can cut multiple pieces of same size)   ║
╠══════════════════════╬═══════════════╬═════════════════╬══════════════════════════════════════════╣
║ LC 279 Perfect Sq.   ║ W: square num ║ exact number N  ║ min: 1 + dp(i, N - (i*i))                ║
║ (Min Squares)        ║ V: 1 square   ║                 ║ (items are 1, 4, 9... can reuse them)    ║
╚══════════════════════╩═══════════════╩═════════════════╩══════════════════════════════════════════╝

```

---

## Watch Me Apply The Template To Real Problems

### Problem 1: LC 322 Coin Change (Minimization / Exact Target)

```text
Goal: MINIMIZE coins to hit EXACT target.

STATE: i (current coin), amt (remaining target)
BASE 1: amt == 0 → return 0 (Success! 0 extra coins needed)
BASE 2: i == n → return 1e9 (Failure! Ran out of coins, amt > 0)

TRANSITION:
pick = 1e9
if (amt >= coins[i]): 
    pick = 1 + f(i, amt - coins[i])  <-- Stay on 'i' to reuse!
skip = f(i + 1, amt)

RESULT = min(pick, skip)

```

### Problem 2: Maximum Sale Items (Maximization / At Most Target)

```text
Goal: MAXIMIZE items obtained with AT MOST 'budget'.

STATE: i (current item), k (remaining budget)
BASE 1: i == n → return 0 (Success! Ran out of items, legal to have budget left)
BASE 2: N/A (Pruning prevents negative budget)

TRANSITION:
pick = -1e9
if (k >= items[i].price):
    pick = 1 + free + f(i, k - items[i].price) <-- Stay on 'i' to buy again!
skip = f(i + 1, k)

RESULT = max(pick, skip)

```

---

## The Evolution Guide: Memoization $\to$ Tabulation $\to$ Space Optimization

This is the exact mental framework to convert your recursive code into a FAANG-level 1D array.

### Stage 1: Top-Down Memoization (The Blueprint)

You already know this. It's the `f(i, target)` recursive function.

* **Time:** $O(N \cdot K)$
* **Space:** $O(N \cdot K)$ (2D Array) + $O(K)$ (Call Stack)

### Stage 2: Bottom-Up 2D Tabulation (Reversing Time)

To remove the recursive call stack, we build the table iteratively.

* **Rule 1: Dimensions.** Size the array as `dp[N+1][Target+1]` to act as a buffer for the `i+1` base case.
* **Rule 2: Base Cases.** Translate `if (amt == 0) return 0;` to `for(int i=0; i<=N; i++) dp[i][0] = 0;`.
* **Rule 3: Loop Order.** Because your recursive `skip` needs `i+1`, your `i` loop must go **BACKWARDS** from `N-1` down to `0`.

```java
int[][] dp = new int[N + 1][Target + 1];
// Init base cases...

for (int i = N - 1; i >= 0; i--) {
    for (int t = 1; t <= Target; t++) {
        
        int pick = (int) 1e9; // or -1e9 for max
        if (t >= weight[i]) {
            // Unbounded: Same row 'i', earlier target
            pick = val[i] + dp[i][t - weight[i]]; 
        }
        // Skip: Row below 'i+1', same target
        int skip = dp[i + 1][t];
        
        dp[i][t] = Math.min(pick, skip);
    }
}

```

### Stage 3: The 1D Space Optimization (The Grandmaster Move)

Look at the 2D transitions: `dp[i][t]` relies on `dp[i+1][t]` (the row directly below) and `dp[i][t - weight[i]]` (the *same* row, but to the left).
Because we only ever need the row below us and the elements to our left, we can crush this into a 1D array of size `Target + 1`.

* **The Unbounded Secret:** Loop the target from **LEFT TO RIGHT** (`w = weight[i]` up to `Target`).
* Why? When calculating `dp[w]`, the `pick` transition needs `dp[w - weight[i]]`. Because we are moving left to right, `dp[w - weight[i]]` has *already been updated* during the current item's loop. This perfectly simulates picking the exact same item multiple times!

*(Note: In 0/1 Knapsack, you loop Right-to-Left to prevent reusing the item. In Unbounded, you loop Left-to-Right to FORCE the ability to reuse!)*

```java
// Time: O(N * Target) | Space: O(Target)
public int unboundedKnapsack(int[] weight, int[] val, int target) {
    int[] dp = new int[target + 1];
    
    // Fill with safe infinity depending on Min/Max
    Arrays.fill(dp, (int) -1e9); 
    dp[0] = 0; // Success base case
    
    for (int i = 0; i < weight.length; i++) {
        // UNBOUNDED KNAPSACK: Left to Right sweep!
        for (int t = weight[i]; t <= target; t++) {
            
            // dp[t] acts as 'skip' (it holds the value from the previous item)
            // dp[t - weight[i]] acts as 'pick' (it holds the updated value for the current item)
            dp[t] = Math.max(dp[t], val[i] + dp[t - weight[i]]);
        }
    }
    return dp[target];
}

```

---

## 10 Sister Problems To Solidify Unbounded Knapsack

Master these, and you will recognize the "Infinite Supply" pattern instantly.

| Problem | Goal | Variation & Disguise |
| --- | --- | --- |
| **LC 322 Coin Change** | Min Coins | Exact target, minimization. The pure baseline. |
| **LC 518 Coin Change II** | Count Combinations | Exact target, but you ADD pick + skip instead of min/max. |
| **GFG Rod Cutting** | Max Profit | Cut a rod of length N. Items are lengths 1 to N. |
| **LC 279 Perfect Squares** | Min Elements | Target is N. Items are all squares ($1^2, 2^2 \dots \le N$). |
| **LC 983 Minimum Cost For Tickets** | Min Cost | Days array dictates targets. Items are the 1, 7, 30-day passes. |
| **LC 1449 Form Largest Integer** | Maximize String | Unbounded knapsack, but state stores Strings (or digits) to maximize numeric value. |
| **LC 377 Combination Sum IV** | Count Permutations | *Trap!* Looks like Unbounded, but order matters. Requires swapping the `i` and `target` loop order in Tabulation! |
| **GFG Maximum Ribbon Cut** | Max Pieces | Identical to Rod Cutting, but usually constrained to 3 specific cut lengths (a, b, c). |
| **LC 343 Integer Break** | Max Product | Break integer N into $k$ parts. Items are numbers 1 to N-1. |
| **LC 1049 Last Stone Weight II** | Min Difference | *Counter-example:* This is 0/1 Knapsack! Stones CANNOT be reused. Good practice to compare the two templates. |