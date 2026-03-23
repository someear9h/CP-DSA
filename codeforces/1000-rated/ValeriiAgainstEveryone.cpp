/**
* someear1h 
*/

/**
- powers of 2 are distinct, so the only way u can have non intersecting subarrays with equal sum
is if the array b has duplicate elements
- for eg b = [3, 1, 3] -> power of 2 a = [8, 2, 8]
- see  there l1=1, r1=1 have equal sum to l2=3, r2=3
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    vector<int> b(n);
    for(int i = 0; i < n; i++) {
        cin >> b[i];
    }
    
    sort(all(b));

    for(int i = 1; i < n; i++) {
        if(b[i] == b[i-1]) {
            cout << "YES\n";
            return;
        }
    }

    cout << "NO\n";
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