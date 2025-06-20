#include <iostream>
#include <map>
#include <string>
using namespace std;

int solve(string s) {
    map<pair<char, int>, int> mp;
    int n = s.size();

    for(int i = 0; i < n;) {
        int j = i;

        while(j < n && s[j] == s[i]) {
            j++;
        }
        int len = j - i;

        for(int k = 1; k <= len; k++) {
            mp[{s[i], k}] += len - k + 1;
        }

        i = j;
    }

    int ans = -1;

    for(auto x: mp) {
        if(x.second >= 3) {
            ans = max(ans, x.first.second);
        }
    }

    return ans;
}

int main() {
    
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int t; cin >> t;
    while(t--) {

        string s;
        cin >> s;
    
        cout << solve(s) << endl;
    }
    return 0;
}