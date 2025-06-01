#include <iostream>
#include <vector>
#include <algorithm>
#define ll long long
using namespace std;

int main() {
    int n;
    cin >> n;

    vector<int> arr(n);
    for(int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    // find max element in arr
    int max_val = *max_element(arr.begin(), arr.end());
    vector<long long> freq(max_val + 1, 0);

    for(int a : arr) {
        freq[a]++;
    }
    
    // base case
    if(max_val == 1) {
        cout << freq[1] << endl;
        return 0;
    }

    ll dp_prev_prev = 0; 
    ll dp_prev = freq[1];
    ll curr = 0;

    for(int i = 2; i <= max_val; i++) {
        curr = max(dp_prev, dp_prev_prev + i * freq[i]);

        dp_prev_prev = dp_prev;
        dp_prev = curr;
    }
    cout << dp_prev << endl;
    return 0;
}