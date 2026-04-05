/*
* someear1h
*/

/**
 * PATTERN NOTE: Greedy Sieve / Harmonic Sum
 * 1. What class of problem was this? 
 * Number Theory (Multiples) with a Greedy cost optimization.
 * 2. What was the key insight that made the approach obvious? 
 * The "smallest existing multiple" rule implies that if a multiple is in T (s[i]=='1'), 
 * that specific cost k is blocked for all larger multiples.
 * 3. What signal in the problem should trigger this pattern next time? 
 * "Iterating through multiples of i up to N" combined with N=10^6. This signals O(N log N) Harmonic Series complexity.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (X).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    string s;
    cin >> s;

    vector<bool> removed(n+1, false);
    ll cost = 0;

    // try every k
    for(int k = 1; k <= n; k++) {
        for(int i = k; i <= n; i += k) {
            if(s[i-1] == '1') break; // this k wont work, get the next k

            if(!removed[i]) {
                cost += k;
                removed[i] = true;
            } else {
                // if the element is removed then go for next multiple
                continue; 
            }
        }
    }

    cout << cost << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1; 
    cin >> t;
    while(t--) {
        solve();
    }

    return 0;
}