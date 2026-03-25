/**
* someear1h 
*/

/**
we have to change only when we have more closed brackets than open brackets
count open brackets and when we encounted closed brackets and if we have open brackets to complete them
use them and decrement open if no then we have to do the operation on them so increament 'moves'++
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    string s;
    cin >> s;

    int open = 0;
    int moves= 0;
    for(int i = 0; i < s.size(); i++) {
        if(s[i] == ')') {
            if(open > 0) open--;
            else moves++;
        } 
        else if(s[i] == '(') {
            open++;
        }
    }

    cout << moves << "\n";
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