/**
* someear1h 
*/

/**
 * PATTERN: Sorted Frequency Grouping (Constructive Algorithms)
 * 1. CLASS: Constructive / Sorting / Greedy.
 * 2. INSIGHT: If you take pairwise minimums of a sorted array, the absolute smallest element will appear exactly (n-1) times, the second smallest (n-2) times, etc. The largest element appears 0 times.
 * 3. TRIGGER: "Array of pairwise minimums", "Reconstruct any valid original array", Size is exactly n(n-1)/2.
 * 4. APPROACH: Sort the given array 'b'. The first element is a[1]. Jump the pointer forward by (n-1) to skip its duplicates. The element you land on is a[2]. Jump by (n-2). Repeat until the end. Append 10^9 as the final element a[n].
 * 5. TRAP: Do not try to use hash maps to count frequencies; duplicate elements in the original hidden array will naturally merge their counts, breaking simple hash map logic. The sorting + leaping pointer completely bypasses this trap.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    ll n;
    cin >> n;
    ll N = (n * (n-1)) /2;
    
    vector<ll> b(N);
    for(int i = 0;i< N; i++) {
        cin >> b[i];
    }

    sort(all(b));

    // the smallest element in a would be smaller than n-1 elements
    // the second smallest would be smaller than n-2 elements
    ll x = n-1, i = 0;
    while(x> 0) {
        cout << b[i] << " ";
        i += x;
        x--;
    }

    cout << "1000000000\n";
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