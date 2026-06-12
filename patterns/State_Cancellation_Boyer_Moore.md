## The K-Way Cancellation State Machine (Boyer-Moore)

Every problem in this pattern follows this exact flow:

found it on: LC 169 Majority Element, LC 229 Majority Element II

```text
STEP 1: Identify the "K" factor.
        The problem will ask for elements appearing strictly
        more than ⌊ n/k ⌋ times.
        
        > n/2  →  k = 2  →  Maximum winners allowed: 1
        > n/3  →  k = 3  →  Maximum winners allowed: 2
        > n/4  →  k = 4  →  Maximum winners allowed: 3
        
        Ask: "How many distinct podiums do I need to build?"
        Answer: Always K - 1.

STEP 2: Define your STATE MACHINE.
        Every podium requires exactly two variables:
        1. candidate (Who is holding the podium?)
        2. count (What is their health/reinforcement level?)
        
        e.g. For > n/3: (cand1, count1), (cand2, count2)
        
        Ask: "Do I have enough variables to track the maximum
              possible number of mathematical winners?"

STEP 3: Write the COMBAT TRANSITION RULES.
        For every element in the array, it must evaluate against
        the podiums in a STRICT, MUTUALLY EXCLUSIVE order:
            → Rule 1: Am I already on a podium? (Reinforce)
            → Rule 2: Is there an empty podium? (Claim)
            → Rule 3: I match no one, and no podiums are empty. (The K-Way Knockout)
        
        Ask: "If I am an outsider, am I dealing 1 damage to 
              EVERY single occupied podium simultaneously?"

STEP 4: Respect the "Double-Count" Trap.
        Because array elements can be 0, and our default
        candidate variables start at 0, we must use strictly
        chained `else if` statements.
        
        Ask: "Can a single number accidentally trigger two
              different `if` blocks? If yes, change it to `else if`."

STEP 5: The Mandatory VERIFICATION (Phase 2).
        Phase 1 only gives you SURVIVORS, not WINNERS.
        A survivor might just be the last number in the array.
        You must recount the survivors from scratch.
        
        Ask: "Did the problem guarantee a winner exists?"
        If NO → Phase 2 is strictly mandatory.

```

**That's it. Every frequency/majority problem demanding O(1) space. No exceptions.**

---

## The Transition Table: Your Algorithm On One Page

```text
╔══════════════════╦═══════════════╦════════════════════════════════════════════╗
║ PROBLEM          ║ PODIUMS       ║ TRANSITION RULES (Strict Order)            ║
╠══════════════════╬═══════════════╬════════════════════════════════════════════╣
║ Majority Ele I   ║ 1 Podium:     ║ 1. nums[i] == cand1 → count1++             ║
║ ( > n/2 )        ║ (cand1, cnt1) ║ 2. cnt1 == 0        → cand1=nums[i], cnt1=1║
║                  ║               ║ 3. ELSE             → count1--             ║
╠══════════════════╬═══════════════╬════════════════════════════════════════════╣
║ Majority Ele II  ║ 2 Podiums:    ║ 1. nums[i] == cand1 → count1++             ║
║ ( > n/3 )        ║ (cand1, cnt1) ║ 2. nums[i] == cand2 → count2++             ║
║                  ║ (cand2, cnt2) ║ 3. cnt1 == 0        → cand1=nums[i], cnt1=1║
║                  ║               ║ 4. cnt2 == 0        → cand2=nums[i], cnt2=1║
║                  ║               ║ 5. ELSE (Knockout)  → count1--, count2--   ║
╚══════════════════╩═══════════════╩════════════════════════════════════════════╝

```

---

## Watch Me Apply This To Both Problems

### LC 169: Majority Element (> n/2)

```text
nums = [2, 2, 1, 1, 1, 2, 2]
(Problem guarantees a majority element exists, so Phase 2 is skipped)

STEP 1: K=2 → 1 Podium needed.
STEP 2: State = (cand1=0, cnt1=0)
STEP 3: Transitions:
        [2] → cnt1=0 → cand1=2, cnt1=1
        [2] → matches cand1 → cnt1=2
        [1] → outsider! → cnt1=1
        [1] → outsider! → cnt1=0 (Podium is dead)
        [1] → cnt1=0 → cand1=1, cnt1=1
        [2] → outsider! → cnt1=0 (Podium is dead)
        [2] → cnt1=0 → cand1=2, cnt1=1

Output: 2  ✅

WHY IT WORKS: To win > n/2, a gang must have more members than 
ALL OTHER GANGS COMBINED. Even if every single other number 
kamikazes into the winning gang, the winning gang will still 
have at least 1 guy left standing.

```

### LC 229: Majority Element II (> n/3)

```text
nums = [1, 2, 3, 1, 4]

STEP 1: K=3 → 2 Podiums needed.
STEP 2: State = (cand1=0, cnt1=0), (cand2=0, cnt2=0)
STEP 3: Transitions (Phase 1):
        [1] → takes Podium 1  (cand1=1, cnt1=1)
        [2] → takes Podium 2  (cand2=2, cnt2=1)
        [3] → outsider! TRIPLE KNOCKOUT!  (cnt1=0, cnt2=0)
        [1] → takes Podium 1  (cand1=1, cnt1=1)
        [4] → takes Podium 2  (cand2=4, cnt2=1)

Phase 1 Survivors: cand1=1, cand2=4
STEP 5: Verification (Phase 2):
        Count array for 1: appears 2 times. (2 > 5/3) → WINNER!
        Count array for 4: appears 1 time.  (1 is NOT > 5/3) → LOSER!

Output: [1]  ✅

```

---

## The Decision Tree

```text
After identifying a frequency/counting problem:

Q1: Does the problem demand O(1) space / strictly linear time?
    YES → Boyer-Moore Cancellation Pattern.
    NO  → Just use a HashMap. (Don't overcomplicate if not asked).

Q2: What is the math threshold?
    > n/2 → 1 variable pair.
    > n/3 → 2 variable pairs.
    > n/k → (k - 1) variable pairs.

Q3: Does the problem explicitly state "You may assume a majority element always exists"?
    YES → Return the survivor of Phase 1 directly.
    NO  → You MUST run a Phase 2 `for` loop to verify the survivors.

Q4: Are array elements allowed to be 0 or negative?
    YES → Your default candidates (which initialize to 0) might accidentally match.
          You MUST use strict `else if` to prevent a number from filling an empty 
          podium when it should just be updating an existing one.

```

---

## The Muscle Memory Drill

Every time you see "O(1) space" and "elements appearing more than X times", **immediately write these on paper:**

```text
k_factor = ???       (denominator in the threshold)
podiums  = k - 1     (how many variables I need to declare)

Phase 1 Structure:
    if (match 1)       { ... }
    else if (match 2)  { ... }
    else if (empty 1)  { ... }
    else if (empty 2)  { ... }
    else               { KNOCKOUT ALL }

Phase 2 Verification:
    Always required unless the prompt explicitly gives you a guarantee.
    MUST use mutually exclusive `else if` when recounting.

```

---

## The Trigger Signals Cheatsheet

```text
SIGNAL IN PROBLEM                          → PATTERN / ACTION
"appears more than ⌊ n/2 ⌋ times"          → Standard Boyer-Moore (1 var)
"appears more than ⌊ n/3 ⌋ times"          → Extended Boyer-Moore (2 vars)
"O(1) space complexity" + counting         → State Machine Cancellation
"assume majority always exists"            → Skip Phase 2
"no guarantee majority exists"             → Phase 2 Mandatory
"array contains positive and negative"     → Guard default 0 values with else-if

```

---

## The Core Code Templates (Java)

### Template 1 — LC 169 (> n/2, Guarantee Exists)

```java
// Time: O(n)  Space: O(1)
public int majorityElement(int[] nums) {
    int count = 0;
    int candidate = 0;

    for (int num : nums) {
        if (count == 0) {
            candidate = num;
        }
        
        if (num == candidate) {
            count++;
        } else {
            count--;
        }
    }

    // No Phase 2 needed because the problem guarantees a winner
    return candidate;
}

```

### Template 2 — LC 229 (> n/3, No Guarantees)

```java
// Time: O(n)  Space: O(1)
public List<Integer> majorityElement(int[] nums) {
    int cand1 = 0, cand2 = 0, cnt1 = 0, cnt2 = 0;

    // Phase 1: The Bloodbath
    for (int num : nums) {
        if (cand1 == num) {
            cnt1++;
        } else if (cand2 == num) {
            cnt2++;
        } else if (cnt1 == 0) {
            cand1 = num;
            cnt1 = 1;
        } else if (cnt2 == 0) {
            cand2 = num;
            cnt2 = 1;
        } else {
            cnt1--;
            cnt2--;
        }
    }

    // Phase 2: The Lie Detector
    cnt1 = 0; 
    cnt2 = 0;
    for (int num : nums) {
        if (num == cand1) cnt1++;
        else if (num == cand2) cnt2++; // Mutually exclusive to prevent double-count!
    }

    List<Integer> res = new ArrayList<>();
    if (cnt1 > nums.length / 3) res.add(cand1);
    if (cnt2 > nums.length / 3) res.add(cand2);

    return res;
}

```

---

## Common Mistakes & How To Avoid Them

```text
MISTAKE 1: Reversing the Check Order in Phase 1
    → If you check `if (cnt1 == 0)` BEFORE checking `if (num == cand2)`,
      an incoming number might accidentally claim an empty podium even if 
      it already owns the other podium!
    → FIX: ALWAYS check for "Reinforcements" (matching existing candidates) 
      BEFORE checking for "Empty Podiums". 

MISTAKE 2: Skipping the `else` in Phase 2
    → If the input is [0,0,0] and cand1=0, cand2=0.
    → Two separate `if` statements will increment BOTH counters simultaneously.
    → FIX: `if (num == cand1) ... else if (num == cand2) ...`

MISTAKE 3: Returning Survivors without Counting (The Latecomer Trap)
    → If the array is [1, 2, 3, 4, 5], 4 and 5 survive simply because they
      were the last to arrive, not because they are majority elements.
    → FIX: Always recount from scratch if the problem doesn't guarantee a winner.

MISTAKE 4: Knocking out when a podium is empty
    → You should only trigger `cnt1--; cnt2--;` if BOTH podiums are actively 
      held by enemy candidates.
    → FIX: Because of the `else if` chain, the knockout `else` block only 
      triggers if `cnt1 != 0` AND `cnt2 != 0`. The structure protects itself.

```