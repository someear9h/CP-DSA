/**
* someear1h
*/

/**
 * PATTERN: Bitwise Suffix Isolation via Modulo
 * 1. CLASS: Number Theory / Bit Manipulation.
 * 2. INSIGHT: Taking X modulo 2^m extracts exactly the last 'm' bits of X. Since all numbers are distinct, revealing one bit at a time from right-to-left guarantees they will eventually split into exactly two distinct groups (based on the newly revealed 0 or 1 bit).
 * 3. TRIGGER: "Modulo", "Exactly 2 distinct values", massive constraints (10^18) that forbid sequential search.
 * 4. APPROACH: Iterate k through powers of 2 (2^1 to 2^60). For each k, collect (a[i] % k) into a Set. If the Set size is exactly 2, print k and break.
 * 5. TRAP: Trying to find the exact k given in the example output. The problem asks for ANY valid k, so relying on the universal power-of-2 behavior is safer than trying to reverse-engineer k=7 or k=30.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define ep emplace_back

void solve() {
    int n;
    cin >> n;

    vector<ll> a(n);
    for(int i = 0; i <n; i++) {
        cin >> a[i];
    }

    ll ans = 0;
    
    // iterate powers till 2 ^ 60
    for(int i = 1; i <= 60; i++) { // TC (O) 60 which is (O) 1
        ll k = (1LL << i);
        unordered_set<ll> seto;
        
        for(int j = 0; j < n; j++) {
            seto.insert(a[j] % k);
        }

        if(seto.size() == 2) {
            ans = k;
            cout << ans <<"\n";
            return;
        }
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1;
    cin >> t;
    while(t-->0) solve();

    return 0;
}