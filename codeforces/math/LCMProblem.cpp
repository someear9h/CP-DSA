/**
* someear1h 
*/

/**
 * PATTERN: Greedy Number Theory (LCM/GCD)
 * * INTUITION:
 * - If y is a multiple of x, then LCM(x, y) is simply y. 
 * - To keep the LCM within the upper bound 'r', we must make 'y' as small as possible.
 * - The smallest multiple of a number 'x' is '2 * x'.
 * - To give '2 * x' the highest chance of being <= r, 'x' must be the smallest possible number in the range, which is 'l'.
 * * APPROACH:
 * - Always test the pair (l, 2*l).
 * - If 2*l > r, then it is mathematically impossible for any other valid pair to exist, print -1 -1.
 * - Otherwise, the answer is l and 2*l.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    ll l, r;
    cin >> l >> r;

    if(2*l > r) {
        cout << "-1 -1\n";
    } else {
        cout << l << " " << 2*l << "\n";
    }
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