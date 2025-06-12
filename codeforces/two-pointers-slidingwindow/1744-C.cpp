// https://codeforces.com/contest/1744/problem/C

#include <iostream>
#include <string>
using namespace std;

void solve() {
    int n;
    char c;
    cin >> n >> c;

    string s;
    cin >> s;

    string s2 = s + s;
    int lastGIdx = -1;

    int len = s2.size(), ans = 0;

    for(int i = len - 1; i >= 0; i--) {
        if(s2[i] == 'g') {
            lastGIdx = i;
        }

        if(i < n && s2[i] == c) {
            if(lastGIdx != -1) {
                ans = max(ans, lastGIdx - i);
            }
        }
    }
    cout << ans << endl;
}

int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int t;
    cin >> t;
    while(t--) {
        solve();
    }
    return 0;
}