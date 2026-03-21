/**
* someear1h 
*/

/**
 * PATTERN: Geometry / Independent Dimensions
 * * INTUITION:
 * - We need to maximize the triangle area: (base * height) / 2. Output requires doubled area, so just: base * height.
 * - The base is formed by 2 points on the SAME side. To maximize it, we just take the difference between the first and last point on that side.
 * - To maximize height, the 3rd point MUST be on the opposite side of the rectangle. 
 * - Since the problem guarantees points on all sides, the max height for horizontal bases is always 'h', and for vertical bases is always 'w'.
 * - We do not care about the exact coordinate of the 3rd point, only that it exists on the opposite side.
 * * APPROACH:
 * - Loop through the 4 sides (0: Bottom, 1: Top, 2: Left, 3: Right).
 * - For each side, read the points. Save the first point and the last point.
 * - Calculate base = last - first.
 * - Multiply by 'h' if it's a horizontal side, or 'w' if it's a vertical side.
 * - Track the global maximum area and print it.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int w, h;
    cin >> w >> h;
    ll mx = 0;
    
    for(int i = 0; i < 4; i++) {
        ll k;
        cin >> k;
        ll first = 0, last = 0;

        for(int j = 0; j < k; j++) {
            int x;
            cin >> x;

            if(j == 0) first = x;
            if(j ==k-1) last = x;
            
            ll height=1;
            if(i <= 1) {
                height = h;
            } else {
                height = w;
            }
            
            // last - first form the base
            ll area = height * (last-first);
            mx = max(mx, area);
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