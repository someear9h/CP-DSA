/**
* someear1h 
*/

/**
 * PATTERN: Two Pointers + STL Geometry (std::rotate)
 * * INTUITION:
 * - The array is sorted. Students can only swap with adjacent students of the EXACT same size.
 * - If any isolated student exists (a group of size 1), a valid swap is impossible (-1).
 * - For valid groups (size >= 2), a left cyclic shift of 1 position guarantees everyone gets a different ID.
 * - std::rotate(first, middle, last) effortlessly performs this shift in-place.
 * * APPROACH:
 * - Create a 'students' array initialized with 1-based IDs: [1, 2, 3... n].
 * - Use Two Pointers (l, r) to find ranges where sizes[l] == sizes[r].
 * - If (r - l) == 1, output -1 and abort.
 * - Otherwise, apply std::rotate to the 'students' array strictly within the bounds [l, r).
 * - We set the 'middle' iterator to 'l + 1' so it shifts exactly one position to the left.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    vector<int> s(n);
    for(auto& i : s) {
        cin >> i;
    }

    vector<int> stu(n);
    for(int i = 0; i < n; i++) stu[i] = i+1;

    int l = 0, r = 0;
    while(r < n) {
        while(r < n && s[l] == s[r]) {
            r++;
        }

        if(r-l==1) {
            cout << "-1\n";
            return;
        }

        rotate(stu.begin() + l, stu.begin() + l + 1, stu.begin() + r);
        l = r;
    }

    for(auto& i : stu) {
        cout << i << " ";
    }

    cout << "\n";
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