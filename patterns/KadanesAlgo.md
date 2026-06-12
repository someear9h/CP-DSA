## The Local-Global Accumulator (Kadane's Pattern)

Every problem in this pattern relies on a fundamental concept in dynamic programming: **Local Optimum vs. Global Optimum**. You only ever care about the best subarray that *ends exactly at your current step*.

Found on: LC 53 Maximum Subarray, LC 152 Maximum Product Subarray, LC 918 Maximum Circular Subarray

```text
STEP 1: Define the "Running Balance" (Local Optimum).
        You need a variable to track the accumulation of elements 
        as you walk left-to-right.
        
        Ask: "What is the absolute best contiguous sequence I can 
              make that ends EXACTLY on the number I am standing on?"

STEP 2: Define the "Baggage Drop" (The Reset Condition).
        Identify the exact mathematical moment your running balance 
        becomes a "Toxic Asset". 
        
        Sum problems: sum < 0  → Reset to 0 (or drop the old sum).
        Product problems: element is 0 → Reset to 1.
        
        Ask: "At what point does keeping my history mathematically 
              ruin my future?"

STEP 3: Define the "All-Time High" (Global Optimum).
        Because you are constantly dropping baggage and resetting, 
        your running balance fluctuates wildly. You need a separate 
        variable to act as the "High Score" tracker.
        
        Ask: "Did the step I just took beat the historical record?"

STEP 4: Address the "All-Negative" Edge Case.
        If an array is entirely negative numbers (e.g., [-5, -2, -9]), 
        a standard running sum that resets to 0 will output 0. But you 
        MUST pick at least one element. 
        
        Ask: "If I drop baggage BEFORE adding the current number, will 
              my High Score tracker successfully catch the 'least bad' 
              negative number?" (The answer is Yes).

STEP 5: Evolve to Multi-State (For Products/Modifiers).
        If the problem involves multiplication, a massive negative 
        number isn't always toxic—it might be a sleeping giant. A 
        second negative number will flip it to a massive positive!
        
        Ask: "Do I need to track BOTH the Local Maximum AND the 
              Local Minimum simultaneously?"

```

**That's it. Every single contiguous accumulation problem. No exceptions.**

---

## The Transition Table: Your Algorithm On One Page

```text
╔══════════════════╦═══════════════╦═════════════════════════════════════════╗
║ PROBLEM          ║ STATE VARIABLES║ TRANSITION RULES                        ║
╠══════════════════╬═══════════════╬═════════════════════════════════════════╣
║ Max Subarray Sum ║ total         ║ 1. if (total < 0) total = 0             ║
║                  ║ res           ║ 2. total += num                         ║
║                  ║               ║ 3. res = max(res, total)                ║
╠══════════════════╬═══════════════╬═════════════════════════════════════════╣
║ Min Subarray Sum ║ total         ║ 1. if (total > 0) total = 0             ║
║                  ║ res           ║ 2. total += num                         ║
║                  ║               ║ 3. res = min(res, total)                ║
╠══════════════════╬═══════════════╬═════════════════════════════════════════╣
║ Max Product      ║ currMax       ║ 1. if (num == 0) reset max=1, min=1     ║
║ Subarray         ║ currMin       ║ 2. temp = currMax * num                 ║
║                  ║ res           ║ 3. currMax = max(num, temp, currMin*num)║
║                  ║               ║ 4. currMin = min(num, temp, currMin*num)║
║                  ║               ║ 5. res = max(res, currMax)              ║
╚══════════════════╩═══════════════╩═════════════════════════════════════════╝

```

---

## Watch Me Apply This To The Core Variations

### LC 53: Maximum Subarray

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

STEP 1: Local = `total`
STEP 2: Baggage Drop = `if (total < 0) total = 0`
STEP 3: Global = `res = max(res, total)`

Walkthrough:
[-2] → total drops to 0, adds -2. res = -2. 
[ 1] → total drops to 0, adds  1. res =  1. (Dropped the toxic -2)
[-3] → total is 1, adds -3 = -2.  res =  1.
[ 4] → total drops to 0, adds  4. res =  4. (Dropped the toxic -2)
[-1] → total is 4, adds -1 =  3.  res =  4.
[ 2] → total is 3, adds  2 =  5.  res =  5.
[ 1] → total is 5, adds  1 =  6.  res =  6. (All-Time High!)
[-5] → total is 6, adds -5 =  1.  res =  6.
[ 4] → total is 1, adds  4 =  5.  res =  6.

Output: 6 ✅

```

### LC 152: Maximum Product Subarray

```text
nums = [2, 3, -2, 4]

STEP 1: Product rules change the game. A negative is only toxic if it doesn't find a pair.
STEP 2: We must track both `currMax` and `currMin` because `currMin` (a big negative) multiplied by a negative `num` instantly becomes the new `currMax`.

Walkthrough:
[ 2] → max = 2, min = 2. res = 2
[ 3] → max = 6, min = 3. res = 6
[-2] → The Flipper! max becomes -6, min becomes -12. res = 6
[ 4] → max = -24, min = -48. res = 6

Output: 6 ✅

```

---

## The Decision Tree

```text
After identifying "Contiguous Subarray" and "Maximize/Minimize":

Q1: Is the operation Addition (+) or Multiplication (*)?
    Addition (+) → Standard Kadane's. 1 Local Variable.
    Multiplication (*) → Multi-State Kadane's. 2 Local Variables (Max and Min).

Q2: Can the array wrap around to the beginning? (Circular Array)
    NO  → Standard loop.
    YES → Calculate `Total Sum`. 
          Calculate `Max Kadane`. 
          Calculate `Min Kadane`. 
          Answer is `Max(Max Kadane, Total Sum - Min Kadane)`. 
          (If you subtract the absolute worst subarray, you are left with the best circular one!)

Q3: Is the array entirely negative?
    If you use the `sum = max(num, sum + num)` template, or put the baggage drop BEFORE the addition, it automatically handles all-negative arrays by picking the single largest negative number.

```

---

## The Core Code Templates (Java)

### Template 1 — Standard Kadane (Max Sum)

```java
// Time: O(n)  Space: O(1)
public int maxSubArray(int[] nums) {
    int total = 0;
    int res = Integer.MIN_VALUE;

    for (int num : nums) {
        if (total < 0) total = 0; // Baggage drop
        total += num;             // Accumulate
        res = Math.max(res, total); // Update High Score
    }

    return res;
}

```

### Template 2 — Multi-State Kadane (Max Product)

```java
// Time: O(n)  Space: O(1)
public int maxProduct(int[] nums) {
    // Edge case: empty array
    if (nums.length == 0) return 0;
    
    int currMax = nums[0];
    int currMin = nums[0];
    int res = nums[0];
    
    for (int i = 1; i < nums.length; i++) {
        int num = nums[i];
        // Store max temporarily because we need the old max to calculate the new min
        int tempMax = currMax;
        
        // At any point, the new max could be:
        // 1. Just the number itself (baggage drop)
        // 2. The positive streak continuing (tempMax * num)
        // 3. A massive negative streak flipping positive! (currMin * num)
        currMax = Math.max(num, Math.max(tempMax * num, currMin * num));
        currMin = Math.min(num, Math.min(tempMax * num, currMin * num));
        
        res = Math.max(res, currMax);
    }
    
    return res;
}

```

---

## Common Mistakes & How To Avoid Them

```text
MISTAKE 1: Initializing `res = 0` instead of `Integer.MIN_VALUE`.
    → Silent wrong answer on arrays like [-3, -5]. If `res` is 0, the code 
      thinks 0 is the highest score, even though you never saw a 0!
    → ALWAYS initialize your High Score tracker to MIN_VALUE (or nums[0]).

MISTAKE 2: Dropping baggage AFTER accumulation instead of BEFORE.
    → If you do `total += num; if (total < 0) total = 0; res = max(...)`, 
      all-negative arrays will always output 0. 
    → The drop must happen before the current element, so the current element 
      is forced to register its negative value in the High Score tracker.

MISTAKE 3: Trying to use Sliding Window (`left` and `right` pointers) for negative numbers.
    → Sliding window ONLY works when all elements are positive (because you know 
      expanding the window strictly increases the sum). 
    → If an array has negative numbers, expanding the window might decrease the sum!
      Never use Sliding Window here; stick strictly to Kadane's single loop.

MISTAKE 4: Forgetting the edge case in Circular Arrays.
    → If the entire array is negative, `Total Sum == Min Kadane`. 
      So `Total Sum - Min Kadane` equals 0. 
    → You must add an `if (maxKadane < 0) return maxKadane;` check to prevent 
      returning an empty 0 subarray.

```