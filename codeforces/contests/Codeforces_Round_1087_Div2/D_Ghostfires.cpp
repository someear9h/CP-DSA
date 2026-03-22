/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int r, b, g;
    cin >> r >> g >> b;

    int rgPairs = 0, rbPairs = 0, bgPairs= 0;

    int availCol = (r > 0) + (b >0) + (g>0);
    while(availCol >= 2) {
        if(r <= b && r <= g) {
            bgPairs++;
            b--, g--;
        } else if(b <= g && b <= r) {
            rgPairs++;
            r--, g--;
        } else {
            rbPairs++;
            r--, b--;
        }

        availCol = (r > 0) + (b >0) + (g>0);
    }

    if(g >0) {
        cout << "G";

        while(bgPairs > 0) {
            cout << "BG";
            bgPairs--;
        }

        bool placedMid = false;
        while(rgPairs > 0) {
            cout << "RG";
            placedMid = true;
            rgPairs--;
        }
        while(rbPairs > 0) {
            if(placedMid && rbPairs > 0) {
                cout << "RB";
                rbPairs--;
            } else if(!placedMid && rbPairs > 0) {
                cout << "BR";
                rbPairs--;
            }
        }
    } else if(r > 0) {
        cout << "R";
        while(rbPairs > 0) {
            cout << "BR";
            rbPairs--;
        }

        bool placedMid = false;
        while(rgPairs > 0) {
            cout << "GR";
            rgPairs--;
            placedMid = true;
        }

        while(bgPairs>0) {
            if(placedMid)  {
                cout << "GB";
                bgPairs--;
            } else if(!placedMid) {
                cout << "BG";
                bgPairs--;
            }
        }
    } else {
        if(b > 0) cout << "B";
        
        while(rbPairs >0){
            cout << "RB";
            rbPairs--;
        }

        bool placedMid = false;
        while(bgPairs > 0) {
            cout << "GB";
            bgPairs--;
            placedMid = true;
        }

        while(rgPairs > 0) {
            if(placedMid) {
                cout <<"GR";
                rgPairs--;
            } else {
                cout << "RG";
                rgPairs--;
            }
        }
    }

    cout << "\n";
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