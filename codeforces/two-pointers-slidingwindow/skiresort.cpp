#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

int main() {
    int t; cin >> t;

    while(t--) {
        int n, k, q;
        cin >> n >> k >> q;

        vector<int> a(n);
        for(int i = 0; i < n; i++) {
            cin >> a[i];
        }

        int l = 0;
        long long ans = 0;

        for(int r = 0; r < n; r++) {
            if(a[r] > q) {
                l = r + 1; // skip the window
                continue; // next iteration
            }

            if(r - l + 1 >= k) {
                ans += (r - l + 1) - k + 1;
            }
        }
        cout << ans << endl;
    } 
    
    return 0;
}