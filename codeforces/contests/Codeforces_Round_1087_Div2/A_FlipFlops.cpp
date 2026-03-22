/**
* someear1h
*/

/**
 * PATTERN: Greedy Sorting / Snowball Accumulation
 * * INTUITION:
 * - To maximize combat power, we must kill as many monsters as possible.
 * - Always fight the weakest monster first to build up 'C' for harder fights. (SORT)
 * - Throwing a flip flop converts 1 flop into 1 permanent combat power, AS LONG AS we can still kill the monster.
 * - For monster 'i', we can safely throw (C - a[i]) flip flops without making it too strong to kill.
 * * APPROACH:
 * - Sort the monsters array in ascending order.
 * - Iterate through monsters. If a[i] > C, we hit a wall. BREAK.
 * - Otherwise, calculate allowed throws: max_throws = min(available_k, C - a[i]).
 * - Deduct max_throws from available_k.
 * - Add the monster's base power + max_throws to C.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    ll c, k;
    cin >> n >> c >> k;

    vector<ll> a(n);
    for(int i = 0; i < n; i++) {
        cin >> a[i];
    }

    sort(all(a));

    for(int i = 0; i < n; i++) {
        if(c < a[i]) {
            break;
        }

        ll throwable = min(k, c-a[i]);
        k -= throwable;
        c += a[i] + throwable;
    }

    cout << c << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t =1;
    cin >> t;
    while(t-->0) {
        solve();
    }

    return 0;
} 