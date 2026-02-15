#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;

    while(t-->0) {
        long long n;
        cin >> n;
        
        if(n % 2 != 0 || n < 4) {
            cout << -1 << endl;
            continue;
        }

        long long maxBus = n/4;

        long long minbus;
        if(n % 6 ==0) {
            minbus = n/6;
        } else {
            minbus = (n/6)+1;
        }

        cout <<minbus << " " << maxBus << endl;
    }
}