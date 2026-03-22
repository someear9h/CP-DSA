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
    cin >> n;
    vector<ll> a(n);

    for(int i = 0; i<n; i++) {
        cin >> a[i];
    }

    for(int i = 0; i < n;i++) {
        int large_count = 0, small_count = 0;

        for(int j = i+1; j < n; j++) {
            if(a[i] < a[j]) large_count++;
            if(a[i] > a[j]) small_count++;
        }

        cout << max(large_count, small_count) << ((i == n) ? "" : " ");
    }

    cout <<"\n";
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