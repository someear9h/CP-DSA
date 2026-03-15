#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define pb push_back

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for(int i = 0; i < n; i++) cin >> a[i];

    int maxDiff = a[n-1] - a[0];

    // fix first element
    for(int i = 1; i < n; i++) {
        maxDiff = max(maxDiff, a[i] - a[0]);
    }

    // fix last element
    for(int i = 0; i < n-1; i++) {
        maxDiff = max(maxDiff, a[n-1]-a[i]);
    }

    // rotate full array then adjacent element would be first and last after rotation
    for(int i = 0; i < n-1; i++) {
        maxDiff = max(maxDiff, a[i] - a[i+1]);
    }

    cout << maxDiff << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(NULL);

    int t;
    cin >> t;
    while(t-->0) {
        solve();
    }

    return 0;
}