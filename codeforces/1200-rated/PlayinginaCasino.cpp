/**
* someear1h 
*/

/**
 * PATTERN NOTE: Dimension Reduction + Mathematical Contribution
 * 1. What class of problem was this? 
 * Math / Sorting (Sum of Absolute Differences).
 * 2. What was the key insight that made the approach obvious? 
 * The columns were mathematically completely independent. Sorting a 1D array removes the need for absolute values, allowing us to calculate each element's exact addition/subtraction contribution based strictly on its index in O(N) time.
 * 3. What signal in the problem should trigger this pattern next time? 
 * Seeing "sum of absolute differences across all pairs" or "Manhattan distance" in large arrays. NEVER simulate pairs in O(N^2) — always sort and calculate index contributions!
*/
#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n, m;
    cin >> n >> m;
    vector<vector<ll>> a(m, vector<ll>(n, 0));
    
    for(int j = 0; j < n; j++) {
        for(int i = 0; i < m; i++) {
            cin >> a[i][j];
        }
    }
    
    for(int i = 0; i < m; i++) {
        sort(a[i].begin(), a[i].end());
    }
    
    ll sum = 0;

    for(int i = 0; i < m; i++) {
        for(int j = 0; j < n; j++) {
            // get each element's contribution to the sum 
            // in the ith row
            sum += a[i][j] * j;
            sum -= a[i][j] * (n-j-1);
        }
    }

    cout << sum << "\n";
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