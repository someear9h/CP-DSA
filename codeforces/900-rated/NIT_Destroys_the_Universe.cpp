#include <bits/stdc++.h>
using namespace std;
using ll = long long;

int main() {
    int t;
    cin >> t;

    while(t-->0) {
        int n;
        cin >> n;
        
        int cntZ = 0;
        vector<ll> a(n);
        for(int i= 0; i < n; i++) {
            cin >> a[i];
            if(a[i] == 0) cntZ++;
        }

        if(n == cntZ) {
            cout << "0" << endl;
            continue;
        }

        int left = 0, right = n-1;
        while(a[left] == 0) left++;

        while(a[right] == 0) right--;
        
        bool zeroInBet = false;
        for(int i =left; i <= right; i++) {
            if(a[i] == 0) zeroInBet = true;
        }

        if(!zeroInBet) {
            cout << 1 << endl;
        } else {
            cout << 2 << endl;
        }

    }

    return 0;
}