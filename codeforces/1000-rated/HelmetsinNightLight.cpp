/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define pb push_back

void solve() {
    int n, p;
    cin >> n >> p;

    vector<ll> a(n), b(n);
    for(int i = 0; i < n; i++) {
        cin >> a[i];
    }

    for(int i = 0; i < n; i++) {
        cin >> b[i];
    }

    vector<pair<ll, ll>> pairs(n);
    for(int i = 0; i < n; i++) {
        pairs[i].first = b[i];
        pairs[i].second = a[i];
    }

    sort(all(pairs));

    ll shared = 1, minCost = p;

    for(auto& it : pairs) {
        ll canShare = it.second;
        ll cost = it.first;

        if(cost >= p) break;

        if(canShare + shared > n) {
            minCost += (n-shared) * cost;
            shared=n;
            break;
        } else {
            minCost += canShare * cost;
            shared += canShare;
        }
    }

    minCost += (n-shared) * p;
    cout << minCost << "\n";
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