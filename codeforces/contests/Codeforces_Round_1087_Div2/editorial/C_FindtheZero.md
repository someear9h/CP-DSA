### 1. The Core Intuition: The Pigeonhole Trap
You have an array of length 2n. Exactly n elements are `0`. The other n elements are unique positive numbers.
**The Golden Rule:** The *only* way two elements can be equal is if they are both `0`. 

If we pair up the entire array into n distinct pairs: `(1,2), (3,4), (5,6) ... (2n-1, 2n)`
* If we ask about a pair and get a `1`, we win. Both are zeroes.
* If we get a `0`, that pair holds *at most* one zero.

**The Trap:** We intentionally ignore the first pair `(1, 2)`. 
We query the remaining n-1 pairs: `(3,4), (5,6), etc.`
If the judge is evil and answers `0` to every single one of them, it means those n-1 pairs contain a maximum of n-1 zeroes.
But the rules dictate there are n zeroes in total! 
By the **Pigeonhole Principle**, the missing zero *must* be hiding in the pair we ignored: `(1, 2)`. 

Furthermore, to fit exactly n zeroes into n pairs without any pair having two zeroes, **every single pair must contain exactly one zero.**

### 2. The Approach: The Cross-Examination
We used n-1 queries. We have exactly 2 queries left (budget is n+1). 
We mathematically know two facts:
1.  Either index 1 or index 2 is a zero.
2.  Either index 3 or index 4 is a zero.

We just need to cross-examine index 1.
* **Query N:** Ask `? 1 3`. If it’s `1`, index 1 is a zero. We win.
* **Query N+1:** If it was `0`, ask `? 1 4`. If it’s `1`, index 1 is a zero. We win.
* **The Checkmate:** If both are `0`, index 1 matched with *neither* 3 nor 4. Since we know either 3 or 4 *is* a zero, index 1 CANNOT be a zero. Therefore, index 2 MUST be the zero. We output `! 2` and win without even asking.

### 3. The Grandmaster C++ Implementation
Here is the exact translation of the Python code into C++. In C++, using `endl` automatically flushes the output buffer, making it perfect for interactive problems.

```cpp
#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    
    int ans = -1;
    bool found = false;
    
    // Step 1: Query the pairs from (3,4) up to (2n-1, 2n)
    // We loop n-1 times.
    for (int i = 1; i < n; i++) {
        cout << "? " << 2 * i + 1 << " " << 2 * i + 2 << endl; // endl automatically flushes!
        
        int resp;
        cin >> resp;
        
        // Always check for -1 in interactive problems! It means we messed up or hit a limit.
        if (resp == -1) exit(0); 
        
        if (resp == 1) {
            ans = 2 * i + 1;
            found = true;
            break; // We found the zero, stop asking!
        }
    }
    
    // Step 2: The Endgame (If the judge said 0 to everything)
    if (!found) {
        // Cross-examine index 1 with index 3
        cout << "? 1 3" << endl;
        int resp1;
        cin >> resp1;
        if (resp1 == -1) exit(0);
        
        if (resp1 == 1) {
            ans = 1;
        } else {
            // Cross-examine index 1 with index 4
            cout << "? 1 4" << endl;
            int resp2;
            cin >> resp2;
            if (resp2 == -1) exit(0);
            
            if (resp2 == 1) {
                ans = 1;
            } else {
                // Checkmate. Index 1 failed both, so it must be index 2.
                ans = 2;
            }
        }
    }
    
    // Step 3: Report the answer
    cout << "! " << ans << endl;
}

int main() {
    // For interactive problems, DO NOT use cin.tie(0) as it can mess with flushing.
    int t;
    if (cin >> t) {
        while (t--) {
            solve();
        }
    }
    return 0;
}
```

### 4. Complexity Analysis
* **Time Complexity:** O(N) per testcase. We run a loop n-1 times. Inside the loop, we do O(1) input/output operations. Total time for all test cases easily passes the 2.0s limit.
* **Space Complexity:** O(1). We only use a few integer variables (`ans`, `resp`, `i`). We never actually build an array in memory. 

### 5. Flawless Dry Run (Adaptive Worst-Case)
Let's trace n = 3 (6 elements, 3 zeroes).
The judge decides to be evil. It will only answer `1` if forced.

1.  **You:** `? 3 4`
    **Judge:** Answering `1` lets them win. I will answer `0`. (Internal note: 3 and 4 cannot both be zeroes).
2.  **You:** `? 5 6`
    **Judge:** Answering `1` lets them win. I will answer `0`. (Internal note: 5 and 6 cannot both be zeroes).
3.  **You:** (Loop finishes). You now know `(1,2)`, `(3,4)`, and `(5,6)` each contain exactly one zero. 
4.  **You:** `? 1 3`
    **Judge:** If I answer `1`, they win. I will answer `0`. 
5.  **You:** `? 1 4`
    **Judge:** Wait. I previously declared that either 3 or 4 MUST be a zero. If I answer `0` here, it means 1 doesn't match 3, and 1 doesn't match 4. This implies 1 is not a zero. This is mathematically valid, so I will answer `0` to keep them guessing. 
6.  **You:** (Checkmate). Since 1 matched neither of the zeroes hiding in pair `(3,4)`, 1 is not a zero. The zero in the first pair MUST be 2. 
    **You:** `! 2`. 
    **Judge:** Defeated.

### 6. Your PRO Guide to Becoming a GOD
1.  **Interactive Protocol:** Never use fast I/O (`cin.tie(NULL)`) in interactive problems. It desyncs the input and output streams, causing your program to read answers out of order. Always use `endl` instead of `\n` because `endl` forces a buffer flush.
2.  **The Adaptive Mindset:** Always assume the judge is hostile. Build your logic around the *worst-case scenario* where the judge always answers "No" or "0". If your algorithm can corner the judge when it lies, it will naturally handle the easy cases where it tells the truth.
3.  **Pigeonhole Principle:** Whenever a problem gives you a strict number of items (n zeroes) and groups (n pairs), look for the Pigeonhole trap. It allows you to prove things about elements you haven't even looked at.

### 7. Your Commented Blueprint

```cpp
/**
 * PATTERN: Interactive Adaptive / Pigeonhole Principle
 * * INTUITION:
 * - We have 2n elements, exactly n are zeroes. Only zeroes can equal each other.
 * - Group into n pairs: (1,2), (3,4) ... (2n-1, 2n).
 * - If we query n-1 pairs and all return 0 (not equal), they hold at most n-1 zeroes.
 * - By Pigeonhole, the unqueried pair (1,2) MUST hold the final zero.
 * - In fact, EVERY pair holds exactly one zero in this worst-case scenario.
 * * APPROACH:
 * - Query pairs (3,4) to (2n-1, 2n). If any returns 1, we found the zeroes. Break and output.
 * - If all return 0, we cross-examine index 1 with the pair (3,4).
 * - Query (1,3). If 1, index 1 is zero.
 * - Query (1,4). If 1, index 1 is zero.
 * - If both are 0, index 1 is NOT the zero. Thus, index 2 MUST be the zero.
*/
```

You just conquered an adaptive interactive problem using pure mathematical logic. Are you comfortable with why `cin.tie(0)` is dangerous in interactive problems, or would you like to move on to another pattern?