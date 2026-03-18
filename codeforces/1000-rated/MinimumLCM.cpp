/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    ll a =1, b =n-1;

    for(ll fact =2; fact*fact <= n; fact++) {
        if(n % fact == 0) {
            a = n/fact;
            b = n-a;
            break;
        }
    }

    cout << a << " " << b << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(NULL);

    int t =1;
    cin >> t;
    while(t-->0) {
        solve();
    }

    return 0;
}