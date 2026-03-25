/**
* someear1h 
*/

/**
 * PATTERN: Bitwise Reachability / Invariant Isolation
 * * 1. CLASS: Math / Number Theory / Greedy.
 * 2. INSIGHT: Multiplying/dividing by powers of 2 never changes the other prime factors (the "odd core").
 * 3. TRIGGER: Constraints up to 10^18 with operations limited to specific multipliers (like 2, 4, 8) scream O(log N) factorization logic.
 * 4. APPROACH: Strip both numbers of their 2-factors to find the odd core. If cores match, count the difference in their 2-factors and greedily divide by the largest allowed step (3, for 2^3).
 * 5. TRAP: Avoid log2() and ceil() on massive numbers; use pure integer division and modulo to prevent floating-point precision loss.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    ll a, b;
    cin >> a >> b;

    if(a == b) {
        cout << "0\n";
        return;
    }   

    // To make the logic universal, we always want 'a' to be the smaller number.
    // Multiplying a up to b is mathematically identical to dividing a down to b.
    if(a > b) swap(a, b);
    
    // If the larger number is not perfectly divisible by the smaller number,
    // they cannot possibly share the same odd core.
    if(b % a != 0) {
        cout <<"-1\n";
        return;
    }
    
    // Isolate the difference (which must be a perfect power of 2)
    ll diff = b/a;
    ll ops = 0;

    // Greedily eat away at the difference using the largest bites possible (8, then 4, then 2)
    while(diff > 1) { 
        // start from 8 becasuse problem says we can move atmost 3 bits -> 2^8
        if(diff %8 == 0) {
            diff/=8;
        } else if(diff % 4 == 0) {
            diff /=4;
        } else if(diff % 2 == 0) {
            diff /= 2;
        } else {
            // The difference had a prime factor that wasn't 2 (e.g., diff = 3 or 5).
            // This means the original numbers did NOT share an odd core.
            cout << "-1\n";
            return;
        }

        ops++;
    }

    cout << ops << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}