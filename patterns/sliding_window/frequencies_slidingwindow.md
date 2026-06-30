## The Universal Variable Sliding Window Template

Every problem in this pattern follows this exact flow:

```text
STEP 1: Expand the window (The Right Pointer)
        Walk through the array with a 'right' pointer. Add the 
        current element to your running state (e.g., sum, hashmap of 
        character frequencies, count of zeros).

STEP 2: Shrink the window if invalid (The Left Pointer)
        Ask: "Did adding that last element break the rules?"
        (e.g., sum > target, too many distinct characters, too many zeros).
        While the window is invalid, remove the element at the 'left' pointer 
        from your state, and increment 'left'.

STEP 3: Process the valid state
        Now that the window is guaranteed to be valid, update your answer.
        Ask: "Am I looking for the longest window or the shortest?"
        maxLength = max(maxLength, right - left + 1)
        OR
        minLength = min(minLength, right - left + 1)

STEP 4: Prove the Time Complexity limits
        Ask: "If I did this with nested loops checking every subarray, would it TLE?"
        Nested loops take O(N^2) operations.
        In a sliding window, the 'right' pointer moves right N times. 
        The 'left' pointer moves right AT MOST N times. 
        Total operations: N + N = 2N. The time complexity is strictly O(N). ✅


```

**That's it. Every problem. No exceptions.**

---

## Watch Me Apply This To "Minimum Size Subarray Sum"

```text
Target sum is 7. Array is [2, 3, 1, 2, 4, 3].

STEP 1: Expand right. 
        currentSum += nums[right]
        Let's say we expanded until right is at index 3: [2, 3, 1, 2]. currentSum = 8.

STEP 2: Shrink while invalid.
        Is currentSum >= target (7)? Yes. 8 >= 7.
        We have a valid window, but we want the MINIMUM length. We must shrink 
        to see if we can do better.
        Remove nums[left] (2) -> currentSum = 6. left moves up.
        Is 6 >= 7? No. Stop shrinking.

STEP 3: Process the state.
        Before we shrank and broke it, the length was 4. minLength = 4.
        (We do this check INSIDE the while loop for "shortest" problems).

STEP 4: Complexity check.
        O(N^2) would check every combination. We just slithered the window 
        across the array in O(N). Optimal and safe. ✅


```

---

## The Decision Tree

```text
After identifying a contiguous subarray/substring problem:

Q1: What am I optimizing?
    
    Longest/Maximum → Update answer AFTER the `while` loop (when window is valid).
    Shortest/Minimum → Update answer INSIDE the `while` loop (right before you shrink).
    Exact Count → Advanced math (often requires exact(K) = atMost(K) - atMost(K-1)).


Q2: What is the "State"?
    
    Sums/Totals → An integer `currentSum`.
    Character Counts → A `HashMap<Character, Integer>` or `int[128]` frequency array.
    Flipping 0s to 1s → An integer `zeroCount`.


Q3: Is the problem Monotonic? (CRITICAL)
    
    YES (All positive numbers) → Sum only goes up when expanding, only goes down 
                                 when shrinking. Sliding window works perfectly.
    NO (Contains negative numbers) → Expanding might make the sum go down! 
                                     Sliding window breaks. You MUST use Prefix Sum + HashMap.


```

---

## The Classic Problems Mapped To This Template

```text
LC 209  Min Size Subarray Sum      expand sum → shrink while sum >= target, update min
LC 3    Longest Substring w/o Rep. expand chars → shrink while char count > 1, update max
LC 1004 Max Consecutive Ones III   expand 0s → shrink while zeroCount > k, update max
LC 424  Longest Repeating Char Rep expand chars → shrink while (len - maxFreq) > k, update max
LC 904  Fruit Into Baskets         expand types → shrink while distinctTypes > 2, update max


```

---

## Watch Me Apply To 3 Leetcode Problems

### LC 3: Longest Substring Without Repeating Characters

```text
Find the longest substring with no duplicate characters.

STEP 1: Add s.charAt(right) to a HashSet or increment frequency array.
STEP 2: Shrink while invalid.
        While the character frequency > 1, remove s.charAt(left) from the 
        set/array and increment left.
STEP 3: Process the state. Update maxLen = max(maxLen, right - left + 1).
STEP 4: O(N) time instead of checking every substring O(N^3). ✅


```

### LC 1004: Max Consecutive Ones III

```text
Given a binary array, you can flip at most K zeros to ones. Find max consecutive ones.

STEP 1: If nums[right] == 0, zeroCount++.
STEP 2: Shrink while invalid.
        While zeroCount > K, if nums[left] == 0, zeroCount--. Increment left.
STEP 3: Process the state. Update maxLen = max(maxLen, right - left + 1).
STEP 4: O(N) sweep completely bypasses O(N^2) brute force. ✅


```

### LC 424: Longest Repeating Character Replacement

```text
Replace at most K chars to make a string of identical chars. Find max length.

STEP 1: Add s.charAt(right) to frequency array. Track the `maxFreq` of a single char in window.
STEP 2: Shrink while invalid.
        Current window length is `right - left + 1`. The number of chars we NEED to flip is 
        (length - maxFreq). 
        While (length - maxFreq) > K: remove s.charAt(left) from frequencies, increment left.
STEP 3: Process the state. Update maxLen = max(maxLen, right - left + 1).
STEP 4: O(N) time complexity. Optimal. ✅


```

---

## The Muscle Memory Drill

Every time you see a problem asking for a **contiguous subarray** or **substring**, immediately write these on paper:

```text
state_tracker = ???     (how do I store the elements in my current window?)

invalid_condition = ??? (what exact math breaks the rules of the problem?)

shrink_logic = ???      (how do I undo the state at the 'left' pointer?)

answer_update = ???     (am I updating max outside the loop, or min inside the loop?)


```

---

## The Trigger Signals Cheatsheet

```text
SIGNAL IN PROBLEM                                     → PATTERN
"longest/shortest contiguous subarray/substring"      → Sliding Window
"maximum consecutive elements with K flips allowed"   → Sliding Window
"substring containing exactly/at most K distinct"     → Sliding Window
"minimum window containing all target characters"     → Sliding Window
"array contains NEGATIVE numbers"                     → ABORT: Use Prefix Sum + Hash Map


```