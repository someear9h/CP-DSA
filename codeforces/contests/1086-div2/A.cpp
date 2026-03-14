/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define pb push_back

void solve() {
    int n;
    cin >> n;

    vector<vector<int>> a(n, vector<int>(n));
    unordered_map<int, int> mp;
    int mxFreq = 0;
    
    // Read directly into the frequency map
    for(int i = 0; i < n * n; i++) {
        int color;
        cin >> color;
        mp[color]++;
        mxFreq = max(mxFreq, mp[color]); // Track max on the fly
    }

    if(mxFreq >= n + ((n-1) * (n-1))) {
        cout << "NO" << "\n";
    } else {
        cout << "YES" << "\n";
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