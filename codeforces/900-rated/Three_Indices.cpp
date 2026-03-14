#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;

    vector<int> a(n);
    
    for(int i = 0; i < n; i++) {
        cin >> a[i]; 
    }

    for(int i = 0; i < n; i++) {
        if(i > 0 && i < n-1 && a[i-1] < a[i] && a[i] > a[i+1]) {
            cout << "YES" << "\n";
            cout << i-1+1 << " " << i+1 << " " << i + 1 + 1 << "\n";
            return;
        }
    }

    cout << "NO" << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(NULL);

    int t;
    cin >> t;
    while(t--> 0) {
        solve();
    }
}