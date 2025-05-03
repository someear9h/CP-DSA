// https://codeforces.com/contest/670/problem/D1 with smaller constraints
// https://codeforces.com/contest/670/problem/D2 with large constraints

#include <iostream>
#include <vector>
#define ll long long
using namespace std;

bool canMakeCookies(ll x, vector<int> &a, vector<int> &b, ll k) {
    ll total_needed = 0;
    int n = a.size();

    for(int i = 0; i < n; i++) {
        ll required = a[i] * x;
        if(required > b[i]) {
            total_needed += required - b[i];
        }

        if(total_needed > k) {
            return false;
        }
    }
    return true;
}

int main() {
    int n;
    ll k;
    cin >> n >> k;

    vector<int> a(n), b(n);
    for(int i = 0; i < n; i++) cin >> a[i];
    for(int i = 0; i < n; i++) cin >> b[i];

    ll low = 0, high = 2e9, ans = 0;

    while(low <= high) {
        ll mid = low + (high - low) / 2;
        if(canMakeCookies(mid, a, b, k)) {
            ans = mid;
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    
    cout << ans << endl;
    return 0;
}