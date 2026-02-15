#include <bits/stdc++.h>
using namespace std;

void solve() {
    int a, b, c;
    cin >> a >> b >> c;

    // try changing a
    int new_a = (2*b) - c;

    if(new_a >= a && new_a != 0 && new_a % a == 0) {
        cout << "YES" << endl;
        return;
    }

    // try chaning b
    int nb = (a+c) / 2;

    if(nb >= b && (a+c) % 2 == 0 && nb % b == 0) {
        cout << "YES" << endl;
        return;
    }

    // try changing c
    int nc = (2*b) -a;
    if(nc >= c && nc % c == 0 && nc != 0) {
        cout << "YES" << endl;
        return;
    }

    cout << "NO" << endl;
    return;
}

int main() {
    int t;
    cin >> t;

    while(t-->0) {
        solve();
    }

    return 0;
}
