/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

/**
 * PATTERN: Bitwise Properties / Math Mirage
 * * INTUITION:
 * - The bitwise AND operation (&) only ever decreases or maintains a value. It never increases it (1 & 0 = 0).
 * - The problem allows infinite operations of replacing elements with the AND of themselves and mirrored elements across ANY interval.
 * - Because we have infinite mobility across the array, we can theoretically AND any element with any other element.
 * - To minimize the maximum value, we want to destroy as many '1' bits as possible. 
 * - A bit can only be destroyed if at least ONE number in the entire array has a '0' in that position.
 * * APPROACH:
 * - We simply compute the bitwise AND of all elements in the array. 
 * - This represents the absolute minimum state where every possible '0' has been propagated to every element.
*/

void solve() {
    int n;
    cin >> n;
    
    int ans;
    cin >> ans; // Read the first element to initialize the AND accumulator
    
    // Iterate through the rest of the elements
    for (int i = 1; i < n; i++) {
        int x;
        cin >> x;
        ans &= x; // Accumulate the bitwise AND
    }
    
    cout << ans << "\n";
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