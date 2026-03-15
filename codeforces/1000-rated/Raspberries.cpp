#include <bits/stdc++.h>
using namespace std;

using ll = long long;
#define all(x) (x).begin(), (x).end()
#define pb push_back

void solve() {
    int n, k;
    cin >> n >> k;
    int evens = 0, minOps = INT_MAX;
    for(int i = 0; i < n; i++) {
        int x;
        cin >> x;

        if((x&1) == 0) evens++;

        int rem = x % k;
        int ops = rem == 0 ? 0 : (k-rem);
        minOps = min(minOps, ops);
    }

    if(k == 4) {
        int ops = INT_MAX;
        if(evens == 0) {
            ops = 2;
        } else if(evens == 1) {
            ops = 1;
        } else if(evens >= 2) {
            ops = 0;
        }

        minOps = min(minOps, ops);
    }

    cout << minOps << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(NULL);
    
    int t;
    cin >> t;

    while(t-->0) solve();
    return 0;
}