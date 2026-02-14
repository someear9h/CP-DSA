#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
using ll = long long;

int main() {
    int t;
    cin >> t;
    while(t-->0) {
        int n;
        cin >> n;

        vector<ll> a(n);
        for(int i = 0; i < n; i++) {
            cin >> a[i];
            if(a[i] == 1) {
                a[i] +=1;
            }
        }

        for(int i = 0; i < n-1; i++) {
            if(a[i+1] % a[i] == 0) {
                a[i+1] +=1;
            }
        }


        for(int num : a) {
            cout << num << " ";
        }
        cout << endl;
    }
    
    return 0;
}