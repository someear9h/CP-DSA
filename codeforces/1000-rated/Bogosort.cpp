/**
* someear1h 
*/
/**
just sort by descending order thats it
*/
#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for(int i = 0; i <n; i++) {
        cin >> a[i];
    }

    sort(a.rbegin(), a.rend());
    for(auto& it : a) {
        cout << it << " ";
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