/**
* someear1h
*/

/**
 * PATTERN NOTE: Greedy Two Pointers + Permutation Properties
 * 1. What class of problem was this? 
 * Subarray Search / Two Pointers.
 * 2. What was the key insight that made the approach obvious? 
 * If an endpoint is the min or max of the current window, it can NEVER be part of a valid subsegment, so we must greedily discard it and shrink the window.
 * 3. What signal in the problem should trigger this pattern next time? 
 * "Find a subsegment", constraints on the "endpoints", and the word "Permutation" (which allows tracking min/max in O(1) without complex data structures).
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

bool isMinorMax(int val, set<int>& s) {
    if(val == *s.begin() || val == *s.rbegin()) {
        return true;
    }

    return false;
}

void solve() {
    int n;
    cin >> n;

    vector<int> a(n);
    for(int i = 0; i < n; i++) cin >> a[i];

    set<int> s(all(a)); // tc: (O)NlogN, sc: (O)N

    int l = 0, r = n-1;
    while(l < r) {
        if(isMinorMax(a[l], s)) {
            s.erase(a[l]); // (O)logN
            l++;
        }

        else if(isMinorMax(a[r], s)) {
            s.erase(a[r]);
            r--;
        }

        else if(!isMinorMax(a[l], s) && !isMinorMax(a[r], s)) {
            cout << l + 1 << " " << r+1 << "\n";
            return;
        }
    }

    cout << "-1\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1;
    cin >> t;
    while(t--> 0) {
        solve();
    }

    return 0;
}