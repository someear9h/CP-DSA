/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    char c;
    cin >> n >> c;

    string s;
    cin >> s;

    if(c == 'g') {
        cout << 0 << "\n";
        return;
    }

    s +=s;
    int mx = 0, lastGIdx = -1;

    for(int i = 2*n-1; i>= 0; i--) {
        if(s[i] == 'g') {
            lastGIdx = i;
        }

        if(s[i] == c && lastGIdx != -1) {
            mx = max(mx, lastGIdx - i);
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