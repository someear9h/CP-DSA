/**
* someear1h
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define pb push_back

void solve() {
    int n, k;
    cin >> n >> k;

    vector<pair<int, int>> pairs(n);
    for(int i = 0; i < n; i++) {
        int x;
        cin >> x;
        pairs[i].first = x;
        pairs[i].second= i;
    }

    for(int i = 0; i < n; i++) {
        pairs[i].first = pairs[i].first % k;

        if(pairs[i].first == 0) pairs[i].first = k;
    }

    sort(pairs.begin(), pairs.end(), [&] (pair<int, int> a, pair<int, int> b) {
        if(a.first != b.first) {
            return a.first > b.first;
        } 
        return a.second < b.second;
    });

    for(int i = 0; i < n;i++) {
        cout << pairs[i].second +1 << " ";
    }
    cout << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(NULL);

    int t = 1; 
    cin >> t;
    while(t-->0) {
        solve();
    }

    return 0;
}