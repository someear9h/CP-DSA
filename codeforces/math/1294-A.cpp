// https://codeforces.com/contest/1294/problem/A

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int t;
    cin >> t;
    while(t--) {
        vector<int> a(3);
        int n;
        cin >> a[0] >> a[1] >> a[2] >> n;

        sort(a.begin(), a.end());

        int coins_needed = (a[2] - a[0]) + (a[2] - a[1]);
        int remaining_coins = n - coins_needed;

        if(coins_needed > n) {
            cout << "NO" << endl;
        } else {
            if(remaining_coins % 3 == 0) {
                cout << "YES" << endl;
            } else {
                cout << "NO" << endl;
            }
        }
        
    }
    return 0;
}