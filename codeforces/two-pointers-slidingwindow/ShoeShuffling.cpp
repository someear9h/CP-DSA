#include <iostream>
#include <vector>
#include <algorithm>
#define ll long long
using namespace std;

void solve() {
    int n;
    cin >> n;

    vector<int> s(n);
    for(int i = 0; i < n; i++) {
        cin >> s[i];
    }

    vector<int> p(n);
    for(int i = 0; i < n; i++) {
        p[i] = i + 1;
    }
    
    bool ans = true; 
    ll l = 0, r = 0;
    while(r < n) {
        // put l to start of the group of same shoe size and r to the end of the group
        while(r < n - 1 && s[r] == s[r + 1]) {
            r++;
        }
        if(l == r) {
            // if the same shoe size group has length 1 then its impossible
            ans = false;
            
        } else {
            // rotate the group of same size in the array by 1 
            rotate(p.begin() + l, p.begin() + r, p.begin() + r + 1);
        }

        l = r + 1;
        r++;
    }

    if(ans) {
        for(auto& x : p) {
            cout << x << " ";
        }
        cout << endl;   
    } else {
        cout << -1 << endl;
    }

}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t;
    cin >> t;
    while(t--) {
        solve();
    }

    return 0;
}