#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while(t-->0) {
        int n;
        cin >> n;

        map<int, int> mp;
        for(int i =0; i < n; i++) {
            int x;
            cin >> x;
            mp[x]++;
        }
        
        int maxCnt = 0;
        for(auto& it : mp) {
            maxCnt = max(maxCnt, it.second);
        }
        int ops = 0;
        while(maxCnt < n) {
            int d = min(n-maxCnt, maxCnt);

            ops += 1 + d;
            maxCnt += d;
        }

        cout << ops << endl;
    }
    
    return 0;
}