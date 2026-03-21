/**
* someear1h 
*/

/**
 * PATTERN: Bit Manipulation / Constructive Array
 * * INTUITION:
 * - To minimize the max adjacent XOR, we must minimize how often the Most Significant Bit (MSB) flips.
 * - Let 'k' be the largest power of 2 strictly less than 'n'.
 * - The array is split into two classes: numbers >= k (MSB is 1) and numbers < k (MSB is 0).
 * - We MUST place at least one number from the >= k group next to the < k group. 
 * - The minimum possible XOR for this unavoidable collision is k ^ 0 = k.
 * * APPROACH:
 * - Find 'k' using a bit-shift loop (1 << i).
 * - Print descending from n-1 down to k. (This keeps the >= k group together).
 * - Print 0. (This creates the optimal k ^ 0 collision).
 * - Print ascending from 1 to k-1. (This keeps the < k group together).
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    // find the largest power of 2 -> 'k'
    int k =1;
    while((k << 1) < n) {
        k = k << 1;
    }

    // print numbers greater than k
    for(int i = n-1; i>=k; i--) {
        cout << i << " ";
    }

    // print 0 for xor with k, eg k = 4  and 4 ^ 0 = 4, minimum max cost is here
    cout << 0 << " ";

    // print rest of the numbers
    for(int i = 1; i < k; i++) {
        cout << i <<" ";
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