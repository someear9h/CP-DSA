// https://codeforces.com/contest/2117/problem/A

#include <iostream>
#include <vector>
using namespace std;

void solve() {
    int n, x;
    cin >> n >> x;
    int l = 1e5, r = -1;
    for(int i = 0; i < n; i++) {
        int door;
        cin >> door;

        if(door == 1) {
            l = min(l, i);
            r = max(r, i);
        }
    }
    // window: r - l + 1
    cout << (x >= r - l + 1 ? "YES" : "NO" )<< endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int t;
    cin >> t;
    while(t--) {
        solve();
    }
    return 0;
}