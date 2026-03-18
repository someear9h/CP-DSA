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
    
    int minn = INT_MAX;
    vector<int> min2;

    for(int i = 0; i < n; i++) {
        int m;
        cin >> m;

        vector<int> v(m);
        for(auto& el : v) cin >> el;

        int minele = *min_element(v.begin(), v.end());
        minn = min(minn, minele);

        v.erase(find(all(v), minele));
        min2.push_back(*min_element(all(v)));
    }

    cout << minn + (ll) accumulate(all(min2), 0LL) - *min_element(all(min2)) << "\n";
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