/**
* someear1h 
*/

/**
 * PATTERN NOTE: Frequency Map + Difference Counting
 * 1. What class of problem was this? 
 * Greedy / Counting / Hash Map.
 * 2. What was the key insight that made the approach obvious? 
 * You only are forced to start a new sequence when you have MORE of the current number than the previous number. If you have less or equal, they just attach to the sequences you've already started.
 * 3. What signal in the problem should trigger this pattern next time? 
 * "Consecutive integers", "Minimum number of subsets", or any grouping problem where items must be adjacent in value. Map the frequencies, sort them, and sum up max(0, curr_freq - prev_freq).
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    vector<ll> a(n);
    for(auto& i : a) cin >> i;

    map<int, int> mp;
    for(auto& i : a) {
        mp[i]++;
    }

    ll ans = 0;
    for(auto& i : mp) {
        ans += max(0, mp[i.first] - mp[i.first -1]);
    }    

    cout << ans << "\n";
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