/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

// longest common substring: dp
int lcs(string s, string t, int n, int m) {
    vector<vector<int>> dp(n+1, vector<int>(m+1));
    int ans = 0;

    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= m; j++) {
            if(s[i-1] == t[j-1]) {
                dp[i][j] = 1 + dp[i-1][j-1];
                ans = max(ans, dp[i][j]);
            } else {
                dp[i][j] = 0;
            }
        }
    }

    return ans;
}

void solve() {
    string s, t;
    cin >> s;
    cin >> t;

    int n = s.size();
    int m = t.size();

    int lonegstCommonSubstring = lcs(s, t, n, m);
    int a = n- lonegstCommonSubstring;
    int b = m - lonegstCommonSubstring;
    int add = a+b;
    
    cout << add << "\n";
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