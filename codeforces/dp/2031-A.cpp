// https://codeforces.com/problemset/problem/2031/A

#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

void solve() {
    int n;
    cin >> n;

    vector<int> a(n);
    for(int i = 0; i < n; i++) {
        cin >> a[i];
    }

    unordered_map<int, int> mp;

    for(int i = 0; i < n; i++) {
        mp[a[i]]++;
    }

    // find most frequent height
    int max_freq = 0;
    for(auto& entry : mp) {
        max_freq = max(max_freq, entry.second);
    }

    cout << (n - max_freq) << endl;
}

int main() {
    int t;
    cin >> t;
    while(t--) {
        solve();
    }

    return 0;
}