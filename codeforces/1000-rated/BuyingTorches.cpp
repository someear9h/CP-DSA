/**
* someear1h 
*/

/**
- we need to get total sticks here for making k torches
- to make k torches we need k sticks and k coal
- y sticks to buy 1 coal, then how many sticks required to buy k coal? k*y sticks
- now we also need k sticks for k torches so total sticks required is? (y*k) + k -1
- -1 because we already had 1 stick in hand from start of the game
- now how many times we need to do trade 1 for total sticks? total_sticks/x-1
- (x-1) because everytime we give away 1 stick and then get x sticks, net gain x-1
- now for trade 2 we have to buy k coal, so we have to trade k times, thats it


*/

// TC = (O)1 -> no loops, just math
// SC = (O)1 -> no data structures, just variables

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    ll x, y, k;
    cin >> x >> y >> k;

    ll total_sticks = (y*k) + k-1;
    ll trade1 = (total_sticks + (x-1) - 1)/ (x-1); // ceiling
    ll trade2 = k; // we trade exactly k times to get k coal

    ll total_trades = trade1 + trade2;

    cout << total_trades << "\n";
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