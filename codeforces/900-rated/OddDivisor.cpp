/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    ll n;
    cin >> n;

    // only numbers which dont have odd divisor > 1 are the numbers
    // whose prime factors are exclusively 2 (not 3, 5, 7 nothing)
    // so only powers of 2 dont have an odd divisor > 1
    // check if number is power of 2
    if((n & (n-1)) == 0) {
        cout << "NO\n";
    } else {
        cout << "YES\n";
    }
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