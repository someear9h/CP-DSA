#include <iostream>
#include <vector>
using namespace std;

using ll = long long;

int main() {
    ll t;
    cin >> t;

    while(t-->0) {
        ll n, q;
        cin >> n >> q;

        vector<ll> a(n);
        ll totSum = 0;
        for(int i = 0; i < n; i++) {
            cin >>a[i];
            totSum +=a[i];
        }

        vector<ll> preSum(n+1, 0);
        for(int i =1; i <=n; i++) {
            preSum[i] = preSum[i-1] + a[i-1];
        }

        while(q-->0) {
            ll l, r, k;

            cin >> l >> r >> k;

            ll rangeSum = preSum[r] - preSum[l-1];
            ll newSum = (r-l+1) * k;
            ll ans = totSum - rangeSum + newSum;

            if((ans & 1) == 0) {
                cout << "NO\n";
            } else {
                cout << "YES\n";
            }
        }
    }

    return 0;
}