/*
* someear1h
*/

/*
PATTERN: Greedy + Two Pointers on sorted array

KEY INSIGHT: Compute diff = y - x per person. Problem becomes:
"maximum pairs with sum >= 0". Sort ascending, pair smallest
with largest. If they can form valid group → count it, move both
pointers. If not → largest can't save smallest, discard smallest.

TRIGGER: "maximum number of groups/pairs where sum satisfies condition"
→ sort + two pointers greedily pairing extremes
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    vector<ll> x(n);
    for(int i = 0; i < n; i++) cin >> x[i];
    
    for(int i = 0; i < n; i++) {
        ll y;
        cin >> y;
        
        x[i] = y - x[i]; 
    }

    sort(x.begin(), x.end());
    
    int l = 0, r = n-1;
    ll groups = 0;

    while(l < r) {
        if(x[l] + x[r] >= 0) {
            groups++;
            l++;
            r--;
        } else {
            l++;
        }
    }

    cout << groups << "\n";

}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    cin >> t;
    while(t-->0) {
        solve();
    }
    
    return 0;
}