/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define pb push_back

void solve() {
    string s;
    cin >> s;

    int zeros = count(all(s), '0');
    int n = s.size();
    int ones = n - zeros;
    int total = min(zeros, ones);

    if((1 & total) != 0) {
        cout << "DA" << "\n";
    } else {
        cout << "NET" << "\n";
    }
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