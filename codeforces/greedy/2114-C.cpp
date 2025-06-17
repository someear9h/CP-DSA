#include <iostream>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int last = -1, ans = 0;

    for(int i = 0; i < n; i++) {
        int a;
        cin >> a;

        if(a - last > 1) {
            ans++;
            last = a;
        }
    }
    cout << ans << endl;
}

int main() {
    int t; 
    cin >> t;
    while(t--) {
        solve();
    }

    return 0;
}