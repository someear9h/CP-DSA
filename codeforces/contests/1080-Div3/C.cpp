/**
* someear1h
*/

#include <bits/stdc++.h>
using namespace std;
using ll = long long;

bool isBad(int x, int y) {
    if(x == y) return true;
    if(x + y == 7) return true;

    return false;
}

void solve() {
    // lets go someear1h
    int n;
    cin >> n;

    vector<int> a(n);
    for(int i = 0; i < n; i++) {
        cin >> a[i];
    }
    
    int ops = 0;
    int len = 1;

    for(int i = 1; i < n; i++) {
        if(isBad(a[i], a[i-1])) {
            len++;
        } else {
            ops += len / 2;
            len =1;
        }
    }

    ops += len / 2;

    cout << ops << endl;
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