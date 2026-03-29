#include <bits/stdc++.h>
using namespace std;

using ll = long long;

class Solution {
public:
    ll mod = 1000000007;
    
    ll binPow(ll base, ll exp) {
        base %= mod;
        ll res = 1;
        while(exp > 0) {
            if(exp&1) {
                res = res * base % mod;
            }

            base = base * base % mod;
            exp >>= 1;
        }

        return res;
    }

    ll modInverse(ll n) {
        return binPow(n, mod - 2);
    }

    ll nCr(ll n, ll r) {
        if(r < 0 || r > n) return 0;
        if(r > n/2) r = n-r; // symmetry property

        ll num = 1, den = 1;
        for(int i = 0; i < r; i++) {
            num = (num * (n-i)) % mod;
            den = (den * (i+1)) % mod;
        }

        return (num * modInverse(den)) % mod;
    }
};

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    ll mod = 1000000007;
    ll base, exp;
    cin >> base >> exp;

    Solution sol;

    cout << sol.binPow(base, exp) << "\n";

    return 0;
}