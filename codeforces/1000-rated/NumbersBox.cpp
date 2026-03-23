/**
* someear1h
*/

/*
If the initial count of negative numbers is even, you can make every number positive. 
If the count is odd, you must leave one number negative. 
To maximize the sum, which number should you choose to leave negative?
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n, m;
    cin >> n >> m;

    int negCnt = 0;
    ll sum = 0;
    int minNeg = INT_MAX;

    for(int i = 0;i < n; i++) {
        for(int j = 0; j < m; j++) {
            int x;
            cin >> x;

            if(x < 0) {
                negCnt++;   
                
            }

            minNeg = min(minNeg, abs(x));
            sum +=abs(x);
            
        }
    }

    if(negCnt % 2 != 0) {
        sum -= (2*minNeg);
        cout << sum << "\n";
    }else {
        cout << sum << "\n";
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t=1;
    cin >> t;
    while(t--> 0) {
        solve();
    }

    return 0;
}