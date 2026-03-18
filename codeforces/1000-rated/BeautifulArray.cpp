/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    ll n, k, b, s;
    cin >> n >> k >> b >> s;

    ll min_s = k * b;
    ll max_s = k * b + (k-1) * n;

    if(s < min_s || s > max_s) {
        cout << -1 << "\n";
        return;
    }

    vector<ll> ans(n, 0);
    ans[0] = min_s;
    s -= min_s;

    for(int i = 0; i < n; i++) {
        ll add = min(k-1, s);
        ans[i] += add; 
        s -= add;
    }

    for(auto& i : ans) cout << i << " ";
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