/**
* someear1h
*/

#include <bits/stdc++.h>
using namespace std;
using ll = long long;

int getOddRoot(int n) {
    while(n % 2 == 0) {
        n /= 2;
    }

    return n;
}

void solve() {
    // lets go someear1h
    int n;
    cin >> n;

    vector<int> a(n+1);
    for(int i=1; i <= n; i++) {
        cin >> a[i];
    }

    for(int i = 1; i <=n; i++) {
        if(getOddRoot(a[i]) != getOddRoot(i)) {
            cout << "NO" << endl;
            return;
        }
    }

    cout << "YES" << endl;
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