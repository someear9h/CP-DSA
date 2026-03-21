# 🦗 The Odd Grasshopper: A Masterclass in Cycle Finding

## 📜 The Problem Matrix

A grasshopper starts at coordinate `x0`. Every minute `i`, it jumps a distance of exactly `i`.

* **The Rule:** If the current position is **EVEN**, it jumps **LEFT** (`-i`). If **ODD**, it jumps **RIGHT** (`+i`).
* **The Goal:** Find the final position after exactly `n` jumps.
* **The Catch:** `n` can be up to `10^14`.

## 🚨 The TLE Trap (The Mortal Approach)

A beginner sees this and immediately writes a `for` loop from `1` to `n`.
When `n = 10^14`, a loop will result in a **Time Limit Exceeded (TLE)** error. Modern judges execute roughly `10^8` operations per second. `10^14` operations would take over 11 days to run.

**Grandmaster Rule:** When constraints are `N >= 10^9`, simulation is a trap. You must find the mathematical cycle.

---

## 🧠 The Grandmaster Intuition: The 4-Jump Anomaly

If we simulate the first few jumps starting from `0` (an Even number), a perfect mathematical cancellation reveals itself:

* Jump 1 (Dist 1): `0 - 1 = -1`
* Jump 2 (Dist 2): `-1 + 2 = 1`
* Jump 3 (Dist 3): `1 + 3 = 4`
* Jump 4 (Dist 4): `4 - 4 = 0`

After exactly 4 jumps, the grasshopper's net movement is **exactly 0**.
Because `-k + (k+1) + (k+2) - (k+3) = 0` for *any* starting jump `k`, billions of jumps are completely irrelevant. We only care about the remainder: `n % 4`.

---

## ⚙️ The Approach

### Step 1: Calculate the Net Displacement Vector

We assume the grasshopper starts at an **EVEN** number (like 0) and determine its net movement based strictly on the remaining jumps (`n % 4`).

| State (`n % 4`) | Logical Meaning | Net Displacement (`finalPos`) | Example (if `n` was 1, 2, 3, or 4) |
| --- | --- | --- | --- |
| **`1`** | Cycle reset. Just jumped Left by `n`. | `-n` | `0 - 1 = -1` |
| **`2`** | Jumped Left by `k`, Right by `k+1`. | `1` | `-1 + 2 = 1` |
| **`3`** | Currently at `1` (Odd), so jump Right by `n`. | `n + 1` | `1 + 3 = 4` |
| **`0`** | Perfect 4-jump cycle completed. | `0` | `4 - 4 = 0` |

### Step 2: The Parity Mirror (The Real World Adjustment)

The table above calculates the displacement assuming an **EVEN** start.
What if the grasshopper starts on an **ODD** number?
Because an odd start forces the first jump to the Right instead of the Left, it flips the direction of the *entire* chain of jumps. It is the exact mathematical inverse.

* **If Start is EVEN:** We are in the normal universe. `Result = start + finalPos`
* **If Start is ODD:** We are in the mirror universe. `Result = start - finalPos`

---

## 💻 The GOD Code (Java)

```java
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); 
        
        while (t-- > 0) {
            long start = scanner.nextLong(); 
            long jumps = scanner.nextLong(); 
            long finalPos; 

            // STEP 1: Calculate displacement vector (assuming EVEN start)
            if (jumps % 4 == 1) {
                finalPos = -jumps; 
            } else if (jumps % 4 == 2) {
                finalPos = 1; 
            } else if (jumps % 4 == 3) {
                finalPos = jumps + 1; 
            } else {
                finalPos = 0; 
            }

            // STEP 2: The Parity Mirror (Apply to actual starting position)
            if (start % 2 == 0) {
                finalPos = start + finalPos; // Normal Universe
            } else {
                finalPos = start - finalPos; // Mirror Universe
            }

            System.out.println(finalPos); 
        }
        scanner.close();
    }
}

```

---

## 🏃‍♂️ The Dry Run

**Test Case:** `start = 177`, `jumps = 13`

1. **Modulo Check:** `13 % 4 = 1`.
2. **State Identification:** We are in State 1.
3. **Displacement Calculation:** Formula is `-jumps`. Displacement = `-13`.
4. **Parity Mirror:** * Is `start` (177) Even or Odd? It is **ODD**.
* Apply Mirror Formula: `start - finalPos`
* `177 - (-13)` = `177 + 13` = `190`.


5. **Output:** `190`. (Calculated instantly, bypassing 13 manual loops).

---

## ⏱️ Complexity Analysis

* **Time Complexity:** **O(1)** per test case. We perform basic arithmetic and modulo operations regardless of how large `n` is.
* **Space Complexity:** **O(1)**. We only allocate three `long` variables (`start`, `jumps`, `finalPos`), taking negligible space.

---

## 👑 The PRO Guide: Spotting the "Cycle" Pattern

This problem is a classic archetype. When you see similar problems in contests, run this checklist:

1. **The Constraint Trigger:** Does the problem ask you to perform sequential operations `10^9` to `10^18` times? **Stop coding.** You are looking for a math formula or a cycle.
2. **The Parity Trigger:** Does the problem have alternating rules based on Even/Odd, Positive/Negative, or Alice/Bob turns? This almost always guarantees a repeating loop.
3. **The Paper Simulation:** Do not try to guess the formula. Write out the results for `N = 1, 2, 3, 4, 5, 6, 7, 8` on a piece of paper.
4. **Find the Zero-Sum / Reset:** Look for the point where the state resets to `0` or returns to the exact starting condition. That is your cycle length `K`. Your logic now revolves entirely around `N % K`.