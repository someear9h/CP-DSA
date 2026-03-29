/**
* someear1h 
*/

/**
 * PATTERN: Greedy Interval Intersection
 * * INTUITION:
 * - The inequality |v - a[i]| <= x translates to a valid range for v: [a[i]-x, a[i]+x].
 * - To eat consecutive piles without changing 'v', the valid ranges of those piles must overlap.
 * * APPROACH:
 * - We maintain a running intersection window [l, r].
 * - As we move to a new pile, we shrink the window: l = max(l, new_l) and r = min(r, new_r).
 * - If at any point l > r, the intersection is broken. We MUST change 'v' (ans++).
 * - When broken, we completely reset our running window to the new pile's range and continue.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n, x;
    cin >> n >> x;

    vector<pair<int, int>> intervals(n);
    for(int i = 0; i <n; i++) {
        int num;
        cin >> num;

        // |v - val| <= x -> -x <= v - val <= +x -> v = [val-x, val+x]
        intervals[i].first = num - x;
        intervals[i].second = num + x;
    }

    int l = intervals[0].first;
    int r = intervals[0].second;
    int ans = 0;
    for(int i = 0;i < n; i++) {
        l = max(l, intervals[i].first);
        r = min(r, intervals[i].second);

        if(l > r) {
            ans++;
            l = intervals[i].first;
            r = intervals[i].second;
        }
    }

    cout << ans << "\n";
}

// TC = (O)N
// SC = (O)N

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