#include <iostream> 
#include <cmath> // abs, pow and all
#include <vector> // vector stl
#include <numeric> // for gcd
using namespace std;

using ll = long long;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    ll t;
    cin >> t;
    while(t-->0) {
        int n;
        cin >> n;
        
        vector<int> a(n);
        for(int i = 0; i < n; i++) {
            cin >> a[i];
        }

        ll k = abs(a[0]-1);

        for(int i = 0;i < n; i++) {
            k = gcd(k, abs(a[i] - (i+1)));
        }

        cout << k << endl;
    }

    return 0;
}