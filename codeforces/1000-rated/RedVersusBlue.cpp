/**
* someear1h 
*/

/**
 * PATTERN: Math / Constructive Algorithms (Pigeonhole Principle)
 * * INTUITION:
 * - We want to minimize the maximum consecutive 'R's.
 * - The 'B's act as walls. 'b' walls create exactly 'b + 1' buckets.
 * - To minimize the max 'R' in any bucket, we must distribute the 'r' items evenly across the 'b + 1' buckets.
 * * APPROACH:
 * - Calculate the base number of 'R's every bucket gets: base_count = r / (b + 1).
 * - Calculate the remainder of 'R's that didn't fit evenly: leftover = r % (b + 1).
 * - Iterate exactly 'b + 1' times (once for each bucket).
 * - For each bucket, print 'base_count' 'R's. If we still have 'leftover' > 0, print one extra 'R' and decrement 'leftover'.
 * - After filling a bucket, if it is NOT the last bucket, print a 'B' to separate it from the next one.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n, r, b;
    cin >> n >> r >> b;

    int buckets = b+1;
    int base = r/buckets;
    int leftOver= r % buckets;

    for(int i = 0; i < buckets; i++) {
        int currR = base + (leftOver > 0 ? 1 : 0);
        leftOver--;

        for(int j = 0; j < currR; j++) {
            cout << 'R';
        }

        if(i < buckets-1) {
            cout << 'B';
        }
    }

    cout << "\n";
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