/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define pb push_back

void solve() {
    int n;
    cin >> n;

    int cnt2 = 0, cnt3 = 0;
    while(n %2== 0) {
        n /= 2;
        cnt2++;
    }

    while(n % 3 == 0) {
        n /= 3;
        cnt3++;
    }

    // we can add 2s but not 3s so when we dont have enough 3's to pair with 2's we print -1
    // when we do have and we dont have another foreign prime number like 5, 7, 11
    // it means we have n == 1 and also have more or equal 3's for 2's, if 2's are less than 3's
    // no problem we can always increase 2's

    if(n == 1 && cnt2 <= cnt3) {
        cout << cnt3 + (cnt3-cnt2) << "\n";
        return;
    } else {
        cout << "-1" << "\n";
    }

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