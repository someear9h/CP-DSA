// https://codeforces.com/problemset/problem/1941/C

#include <iostream>
#include <string>
using namespace std;

void solve() {
    int n;
    cin >> n;

    string s;
    cin >> s;

    int ans = 0;
    for(int i = 0; i <= n - 3; i++) {
        if(s.substr(i, 3) == "pie" || s.substr(i, 3) == "map") {
            ans++;
            i += 2; // skip next two characters to avoid overlapping
        }
    }
    cout << ans << endl;
}

int main() {
    int t;
    cin >> t;

    while(t--) {
        solve();
    }

    return 0;
}