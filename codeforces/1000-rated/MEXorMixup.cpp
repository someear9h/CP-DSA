/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

ll xorTillN(ll n) {
    ll rem = n % 4;
    if(rem == 0) {
        return n;
    } else if(rem == 1) {
        return 1;
    } else if(rem == 2) {
        return n+1;
    } else {
        return 0;
    }
}

void solve() {
    ll a, b;
    cin >> a>> b;

    ll baseXOR = xorTillN(a-1);
    ll y = b ^ baseXOR; // xor which is missing and we need to add elements to get this xor

    if(baseXOR == b) {
        cout << a << "\n";
    } else if(y != a) {
        // since a is not y then appening y to the array wont change the mex and make array xor = b
        // we add +1 for the y appendend
        cout << a+1 << "\n";
    } else if(y==a) {
        // we cannot use a for the array as it will break the mex rule so we get more 2 numbers 
        // which have xor = y
        cout << a+2 << "\n";
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