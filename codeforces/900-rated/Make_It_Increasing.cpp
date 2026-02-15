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

        vector<long long> a(n);
        for(int i = 0; i < n; i++) {
            cin >> a[i];
        }

        int ops = 0;
        bool exists = true;

        for(int i =n-2; i >= 0; i--) {
            if(a[i+1] <= a[i]) {
                while(a[i] != 0 && a[i+1] <= a[i]) {
                    a[i] = a[i]/2;
                    ops++;
                }

                if(a[i] >= a[i+1]) {
                    exists = false;
                    break;
                }   
            }
        }

        if(exists)
            cout << ops << endl;
        else cout << -1 << endl;
        
    }

    return 0;
}