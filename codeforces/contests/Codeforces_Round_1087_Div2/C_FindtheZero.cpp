/**
* someear1h
*/

#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;

    bool found = false;
    int ans = -1;

    for(int i = 1; i < n; i++) {
        // always use endl for cout operations in interactive problems
        cout << "? " << 2*i+1 << " " << 2*i+2 << endl;
        int resp;
        cin >> resp;

        if(resp == -1) exit(0);
        else if(resp == 1) {
            ans = 2 * i + 1;
            found = true;
            break;
        }
    }

    if(!found) {
        cout << "? " << 1 << " " << 3 << endl;
        
        int resp2;
        cin >> resp2;

        if(resp2 == -1) {
            exit(0);
        } 

        if(resp2 == 1) {
            ans = 1;
        } else {
            cout << "? " << 1 << " " << 4 << endl;
            int resp3;
            cin >> resp3;

            if(resp3 == -1) exit(0);

            if(resp3 == 1) ans =1;
            else {
                ans = 2;
            }
        }
    }

    cout << "! " << ans << endl;
}

int main() {
    // dont use fast i/o for interactive problems 
    int t;
    cin >> t;
    while(t-->0) {
        solve();
    }
    
    return 0;
}