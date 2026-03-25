/**
* someear1h 
*/

/**
 * PATTERN: Top-K Greedy / Conflict Resolution
 * 1. CLASS: Greedy / Sorting.
 * 2. INSIGHT: When picking a very small number of items (e.g., exactly 3) from large overlapping sets, you only need to keep the "Top K" candidates from each set, where K is the number of sets.
 * 3. TRIGGER: "Maximize sum of EXACTLY X distinct elements from X different arrays". 
 * 4. APPROACH: Pair values with their original indices. Sort descending. Take the top 3 from each array. Run a brute-force nested loop (3x3x3) checking for distinct indices to find the max valid combination.
 * 5. TRAP: Do not use DP if the problem asks for a fixed, small number of actions overall rather than an action *every step* of a sequence.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back


/**
tc: (O) N -> for taking inputs, (O) N log N for sorting the arrays, (O) 27 the 3 nested loops
tc: (O)N log N - we always take dominant (O) notation

sc: (O) N -> we used pairs for n days and 3 vectors so if n = 10 then we made 10*3=30 pairs.
*/
void solve() {
    int n;
    cin >> n;

    // pairs to store indices which represent 'day' here 
    // and we cant do more than 1 actvity in same day 'i'
    vector<pair<ll, ll>> a(n), b(n), c(n);

    for(int i = 0; i <n;i++) {
        cin >> a[i].first;
        a[i].second = i;
    }

    for(int i = 0; i < n; i++) {
        cin >> b[i].first;
        b[i].second = i;
    }

    for(int i = 0; i <n; i++) {
        cin >> c[i].first;
        c[i].second = i;
    }

    // sort by decreasing so we get highest friends first
    sort(a.rbegin(), a.rend());
    sort(b.rbegin(), b.rend());
    sort(c.rbegin(), c.rend());

    // we have to only pick one activity once so we only need 3 greatest friends
    // we loop 3 * 3* 3 which has 27 -> (O)27 -> acceptable
    ll mx = 0;
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            for(int k = 0; k < 3; k++) {
                
                // check if indices(days) are not same
                if(a[i].second != b[j].second && a[i].second != c[k].second
                    && b[j].second != c[k].second) {
                        ll total = a[i].first + b[j].first + c[k].first;
                        mx = max(mx, total);
                    }
            }
        }
    }

    cout << mx << "\n";
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