/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    ll a, b;
    cin >> a >> b;

    ll minOps = INT_MAX;

    for(int i = 0; i <= 30; i++) {  
        ll currB = b + i;
        
        if(currB == 1) continue; // we dont want to divide by 1, it will be infinite loop

        ll currA = a;
        ll currOps = i;

        while(currA >0) {
            currA /= currB;
            currOps++;
        }

        if(currOps < minOps) {
            minOps = currOps;
        }
    }

    cout << minOps << "\n";
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