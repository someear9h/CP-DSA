/**
* someear1h 
*/

/**
 * PATTERN: Peak-Valley Problem / Geometric Array Compression
 * 1. CLASS: Greedy / Geometry.
 * 2. INSIGHT: The sum of absolute differences between adjacent elements is simply the total vertical distance traveled. Intermediate points on a strictly increasing/decreasing slope, or flat areas, contribute nothing to the total distance.
 * 3. TRIGGER: "Subsequence", "Absolute Difference |A[i] - A[i+1]|", "Minimize Size".
 * 4. APPROACH: Iterate through the array. Keep only the points where the trajectory changes direction (Peaks and Valleys). Ignore flat areas and intermediate slope points.
 * 5. TRAP: Arrays with only 1 element, or arrays that are completely flat (e.g., [1, 1, 1, 1]). Your logic must safely compress completely flat arrays down to size 1.
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
    for(int i = 0; i < n; i++) {
        cin >> a[i];
    }
    
    if(n == 1) {
        cout << "1\n";
        return;
    }

    vector<ll> state;
    state.eb(a[0]);
    state.eb(a[1]);

    for(int i =2; i < n; i++) {
        int currSize = state.size();

        ll x = state[currSize-2] - state[currSize-1];
        ll y = state[currSize-1] - a[i];

        if(x > 0) {
            // in past we went down hill
            if(y > 0) {
                // still going downhill, so intermediate elements don matter
                // so we overwrite them
                state[currSize-1] = a[i];
            } else if (y < 0){
                // we will have to change the direction of the trajectory
                state.eb(a[i]);
            }
        } else {
            // in past we went uphill
            if(y < 0) {
                // we are going upgill, intermediate elements dont matter now
                state[currSize-1] = a[i];
            } else if (y > 0){
                // we have to change the direction of the trajectory
                state.eb(a[i]);
            } 
        }
    }
    
    int finalSize = state.size();

    // if trajecotry  remained flat and never changed then the first 2 elements must
    // be similar which has zero contrast
    // for eg this array [4, 4, 4, 4], finalSize is 2 -> [4, 4] but correct is 1 -> [4]
    if(state[0] == state[1]) {
        finalSize--;
    }

    cout << finalSize <<"\n";
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