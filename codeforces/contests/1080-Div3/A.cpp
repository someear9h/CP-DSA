/**
* someear1h
*/

#include <bits/stdc++.h>
using namespace std;
using ll = long long;

void solve() {
    int n;
    cin >> n;
    bool f = false;
    for(int i = 0; i < n; i++) {
        int x;
        cin >> x;

        if(x == 67) f = true;
    }

    if(f) {
        cout << "YES" << endl;
    } else {
        cout << "NO" << endl;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while(t-- > 0) {
        solve();
    }

    return 0;
}