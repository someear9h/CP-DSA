/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define pb push_back

void solve() {
    ll n, k, p, m;
    cin >> n >> k >> p >> m;

    vector<int> a(n);
    for(int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int w_idx = p-1;
    int w_cost = a[w_idx];

    ll cost1 = 0;

    if(p > k) {
        vector<int> window;
        for(int i = 0; i < w_idx; i++) {
            window.pb(a[i]);
        }

        sort(all(window));

        for(int i = 0; i < p-k; i++) {
            cost1 += window[i];
        }
    }

    ll cost2 = 0;
    if(n > k) {
        vector<int> rest;
        for(int i = 0; i < n; i++) {
            if(i == w_idx) continue;
            rest.pb(a[i]);
        }

        sort(all(rest));
        for(int i = 0; i < n-k; i++) {
            cost2 += rest[i];
        }
    }

    // math simulation
    if(m < (w_cost + cost1)) {
        cout << "0" << "\n";
    } else {
        ll plays = 1;
        m -= w_cost + cost1;
        plays += m / (cost2+w_cost);
        cout << plays << "\n";
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(NULL);
    
    int t;
    cin >> t;
    while(t-->0) solve();
    
    return 0;
}