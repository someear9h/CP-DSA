## A - DBMB and the Array

Here is the logic, solution, and explanation for the problem **"DBMB and the Array"**.

### **Logic & Intuition**

The problem asks if we can reach a target sum  by adding the value  to elements of the array any number of times.

1. **Calculate the Initial Sum:** First, sum up all the elements in the given array. Let's call this `currentSum`.
2. **The Operation:** Every time you perform an operation, you add  to a number. This means the total sum of the array increases by exactly .
* If you perform the operation  times, the new sum will be:




3. **The Goal:** We want the `New Sum` to be exactly equal to .




### **The Two Conditions for "YES"**

From the equation above, two things must be true for a solution to exist:

1. **Non-Negative Difference:** You can only *add* to the sum, never subtract. Therefore, the target sum  must be greater than or equal to the current sum.
* **Condition 1:** `currentSum <= s`


2. **Divisibility:** The difference between the target sum and the current sum must be perfectly divisible by . If the gap is 5 but  is 2, you can add 2+2=4 or 2+2+2=6, but you can never hit exactly 5.
* **Condition 2:** `(s - currentSum) % x == 0`



If **both** are true, output `YES`. Otherwise, output `NO`.

---

### **Java Solution**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read the number of test cases
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt(); // size of array
            int s = sc.nextInt(); // target sum
            int x = sc.nextInt(); // value to add
            
            int currentSum = 0;
            
            // Read array elements and calculate sum
            for (int i = 0; i < n; i++) {
                currentSum += sc.nextInt();
            }
            
            // Check the two conditions
            // 1. We cannot reduce the sum, so currentSum must be <= target s
            // 2. The difference must be achievable by adding 'x' some number of times
            if (currentSum <= s && (s - currentSum) % x == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        sc.close();
    }
}

```

---

### **Dry Run with Examples**

**Example 1 (From screenshot):**

* **Input:** `3 8 2` (n=3, s=8, x=2), Array: `[1, 2, 3]`
* **Logic:**
1. `currentSum` = .
2. Check Condition 1: Is ? **Yes**.
3. Check Condition 2: Is ? -> . **Yes**.


* **Output:** `YES`

**Example 2 (From screenshot):**

* **Input:** `4 7 2` (n=4, s=7, x=2), Array: `[1, 1, 1, 1]`
* **Logic:**
1. `currentSum` = .
2. Check Condition 1: Is ? **Yes**.
3. Check Condition 2: Is ? -> . **No**.


* **Output:** `NO`

**Example 3 (Edge Case):**

* **Input:** `3 15 1` (n=3, s=15, x=1), Array: `[2, 4, 10]`
* **Logic:**
1. `currentSum` = .
2. Check Condition 1: Is ? **No**. (We can't decrease the sum).


* **Output:** `NO`


## B - Reverse a Permutation

Here is the explanation, dry run, and the complete Java solution.

### **The Problem Explained**

You are given a permutation (an array with numbers  to ). You can choose **one** segment (from index  to ) and **reverse** it. Your goal is to make the resulting array as "large" as possible (lexicographically maximum).

**What does "Lexicographically Maximum" mean?**
Imagine the array is a long number. You want that number to be as big as possible.

* `[4, 1, 2, 3]` is greater than `[3, 2, 1, 4]` because the first digit  is greater than .
* We prioritize the **leftmost** digits. We want the largest possible number at index 0. If that's already maxed out, we want the largest possible number at index 1, and so on.

---

### **Addressing Your Confusion (Example 1)**

**Input:** `3 2 1 4`
**Your Question:** Why is the answer `4 1 2 3` and not `4 3 2 1`?

To get `4 3 2 1`, you would need to perfectly sort the array in descending order. However, you are restricted to **exactly one reversal**.

Let's look at our options to bring the largest number (`4`) to the front:
The number `4` is currently at the last position (index 3). To move it to the first position (index 0), we **must** reverse the entire segment from index 0 to index 3.

* Original: `[3, 2, 1, 4]`
* Reverse index 0 to 3: `[4, 1, 2, 3]`
* The `4` moves to the start.
* The `1` (originally at index 2) moves to index 1.
* The `2` (originally at index 1) moves to index 2.
* The `3` moves to the end.



There is no single reversal operation that can magically shuffle `3, 2, 1` into `3, 2, 1` while simultaneously moving `4` to the front. The reversal flips the **entire** block.

---

### **The Solution Approach (Greedy Algorithm)**

Since we want the largest values at the start, we use a greedy strategy:

1. **Find the Best Start:** We look at the current array from left to right. We check: *"Is the largest possible remaining number sitting here?"*
* We start wanting the number `N` at index `0`.
* If `N` is already at index `0`, great! We leave it alone. Now we want `N-1` at index `1`.
* If `N-1` is at index `1`, great! Now we want `N-2` at index `2`.


2. **The First Mismatch:** The moment we find a position `L` where the number **should** be `X`, but it isn't, we stop.
3. **The Fix:** We find where `X` is currently hiding (let's say at index `R`). We perform our **one** reversal on the segment `[L, R]`. This guarantees `X` moves to position `L`, making the array lexicographically largest at the earliest possible spot.

**Dry Run on `3 1 2` (N=3)**

1. **Target:** We want `3` at index 0.
2. **Check:** Is `3` at index 0? Yes. (Array is `3 1 2`).
3. **Move on:** Now we want `2` at index 1.
4. **Check:** Is `2` at index 1? No, `1` is there.
5. **Locate:** Where is `2`? It is at index 2.
6. **Action:** Reverse segment from current index (1) to target index (2).
* Reverse `[1, 2]` inside `[3, 1, 2]`.
* Sub-segment `1, 2` becomes `2, 1`.
* Result: `3 2 1`.



---

### **Java Solution**

```java
import java.io.*;
import java.util.*;

public class Main {
    // Fast I/O helper
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        // Read number of test cases
        int t = Integer.parseInt(next());
        
        while (t-- > 0) {
            solve();
        }
    }

    // Separate function to solve each test case
    static void solve() throws IOException {
        int n = Integer.parseInt(next());
        int[] p = new int[n];
        
        // This array helps us find the position of any number in O(1) time
        // pos[5] = 3 means the value 5 is located at index 3
        int[] pos = new int[n + 1];

        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(next());
            pos[p[i]] = i; // Store the index of this value
        }

        // We want the value 'n' at index 0, then 'n-1' at index 1, etc.
        int expectedIdx = 0;
        
        // Iterate downwards from the largest possible value
        for (int val = n; val >= 1; val--) {
            int currentPos = pos[val];

            // If the largest available value (val) is already at the 
            // expected position, we don't need to do anything for this position.
            if (currentPos == expectedIdx) {
                expectedIdx++;
                continue;
            }

            // If we are here, it means 'val' is NOT where it should be.
            // It is at 'currentPos', but we want it at 'expectedIdx'.
            // OPTIMAL MOVE: Reverse the segment from expectedIdx to currentPos.
            reverse(p, expectedIdx, currentPos);
            
            // We are allowed exactly one operation, so we stop immediately after doing it.
            break;
        }

        // Print the result array
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(p[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    // Helper function to reverse a segment of the array
    static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    // Helper for fast input reading
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }
}

```

### **Code Explanation**

1. **`pos` Array:** We create a map where `pos[value] = index`. This allows us to instantly find where the largest number is without scanning the array every time.
2. **The Loop:** We loop `val` from `n` down to `1`.
* `expectedIdx` tracks the leftmost position we haven't "fixed" yet (starts at 0).
* If the max value `val` is already at `expectedIdx`, we increment `expectedIdx` (move right) and check the next largest number.


3. **The Trigger:** As soon as `pos[val] != expectedIdx`, we have found our opportunity. The largest remaining number is somewhere deeper in the array. We reverse the segment from `expectedIdx` to `pos[val]` to bring that big number to the front.
4. **Break:** Since we can only perform **one** operation, we break the loop immediately after reversing.