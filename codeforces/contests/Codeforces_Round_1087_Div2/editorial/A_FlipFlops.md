### 1. Constraint Analysis: The Ultimate Cheat Code
Before we read the story, we look at the constraints:
* $t \le 500$ (Test cases)
* $n \le 100$ (Monsters per test case)
* $c, k, a_i \le 10^9$ (Values can be massive)

What does this tell us?
1. **$n$ is tiny:** Since $N=100$, an $O(N^2)$ or $O(N \log N)$ approach per testcase is perfectly fine. The total operations will be less than $5 \times 10^4$, which will execute in `< 0.001` seconds.
2. **Values are huge:** Because $c, k,$ and $a_i$ can reach $10^9$, `C` will grow very large. If $n=100$ and each monster gives $10^9$, $C$ can reach $10^{11}$. **We MUST use `long long` for $C$.** (Notice the provided code does exactly this).

### 2. The Intuition (Deconstructing the Rules)
You have combat power $C$. You have $K$ flip flops.
If $a_i \le C$, you can kill monster $i$. Your new power becomes $C_{new} = C + a_i$. 
If you throw a flip flop at monster $i$, its power $a_i$ increases by 1. 

**Observation 1: The Snowball Effect**
To maximize your final $C$, you want to kill as many monsters as physically possible. 
How do you guarantee you kill the most monsters? **Always fight the weakest monster first.** Why? Because killing the weakest monster gives you free stats, which makes you stronger, making it easier to kill the next slightly stronger monster. This is the definition of sorting.

**Observation 2: The Greed of the Flip Flops**
Every flip flop you throw at a monster increases its $a_i$ by 1. When you kill that monster, that $+1$ is permanently added to your $C$.
Essentially, throwing a flip flop at a monster you are going to kill is a **1-to-1 conversion** of a flip flop into permanent Combat Power. 

*But wait!* There is a limit. You can only kill a monster if $a_i \le C$. 
If a monster has $a_i = 5$, and you have $C = 10$, you can throw at most $5$ flip flops at it before its power reaches $10$. If you throw a 6th flip flop, its power becomes $11$, and suddenly you are too weak to kill it! You just lost that monster and its stats forever.

**The Golden Rule:** For any monster you are about to kill, throw as many flip flops at it as possible, **without letting its power exceed your current $C$.**

### 3. The Approach: Step-by-Step
1. **Sort the Monsters:** Put the monsters in an array and sort them in ascending order of power ($a_i$).
2. **Iterate:** Face the monsters one by one, starting from the weakest.
3. **The Wall Check:** Before fighting, check if $a_i > C$. If the weakest remaining monster is stronger than you, you are completely stuck. Since the array is sorted, every monster behind it is also stronger than you. You must stop the battle (`break`).
4. **The Flip Flop Dump:** If $a_i \le C$, you can kill it. But first, milk it for stats. 
   How many flip flops can you safely throw? 
   The safe limit is $C - a_i$.
   But you only have $K$ flip flops in your inventory. 
   So the actual number you throw is: `throw_amount = min(k, C - a_i)`.
5. **The Execution:** * Deduct the flip flops used: `k -= throw_amount`.
   * Increase your power: `C += a_i + throw_amount`.
6. **Repeat** until you run out of killable monsters or reach the end of the array.

### 4. Flawless Dry Run (Test Case 6)
Let's trace exactly how the engine handles the provided Note example.
* $n = 5$ (monsters), $C = 18$ (initial power), $k = 30$ (flip flops)
* Array $A = [1, 2, 93, 84, 2]$

**Step 1: Sort the Array**
$A = [1, 2, 2, 84, 93]$

**Step 2: Battle Phase**
* **Monster 1 ($a_i = 1$):**
  * Check: $1 \le 18$. We can kill it!
  * Safe Limit: $18 - 1 = 17$. 
  * We have 30 flip flops. We throw `min(30, 17) = 17`.
  * *Wait, the example says OtterZ threw 10 flip flops at monster 1 later. Does order matter?* Let's follow our optimal greedy algorithm to see if we get the same final answer.
  * We throw 17 flip flops. 
  * `k` becomes $30 - 17 = 13$.
  * `C` becomes $18 + (1 + 17) = 36$.
* **Monster 2 ($a_i = 2$):**
  * Check: $2 \le 36$. We can kill it.
  * Safe Limit: $36 - 2 = 34$.
  * We have 13 flip flops left. We throw `min(13, 34) = 13`.
  * `k` becomes $13 - 13 = 0$. (We are out of flip flops!)
  * `C` becomes $36 + (2 + 13) = 51$.
* **Monster 3 ($a_i = 2$):**
  * Check: $2 \le 51$. We can kill it.
  * Safe Limit: $51 - 2 = 49$.
  * We have 0 flip flops left. We throw `min(0, 49) = 0`.
  * `C` becomes $51 + (2 + 0) = 53$.
* **Monster 4 ($a_i = 84$):**
  * Check: $84 > 53$. The Wall. 
  * `break` the loop.

**Final Result:** $C = 53$.
(Notice that even though our greedy algorithm threw the flip flops in a different order than the story in the "Note", the final maximum power is exactly the same: 53. Math never lies.)

### 5. Complexity Analysis
* **Time Complexity (TC):** $O(N \log N)$ per test case due to the `std::sort()`. The `for` loop is $O(N)$. Overall TC is $O(T \times N \log N)$. For $T=500, N=100$, this takes a fraction of a millisecond.
* **Space Complexity (SC):** $O(N)$ to store the array `A`. Highly efficient.