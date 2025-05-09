// https://www.geeksforgeeks.org/problems/minimal-cost/1

#include <iostream>
#include <vector>
using namespace std;

int solve(int k, vector<int> &arr, vector<int> &dp, int idx) {
    if(idx == 0) return 0;
    if(dp[idx] != -1) return dp[idx];
    
    int minSteps = INT_MAX;
    for(int j = 1; j <= k; j++) {
        if(idx - j >= 0) {
            int jump = solve(k, arr, dp, idx - j) + abs(arr[idx] - arr[idx - j]);
            minSteps = min(minSteps, jump);
        }
    }
    
    return dp[idx] = minSteps;
}

int minimizeCost(int k, vector<int>& arr) {
    // Code here
    int n = arr.size();
    vector<int> dp(n, -1);  
    return solve(k, arr, dp, n - 1);
}

int main() {
    int n, k;
    cin >> n >> k;

    vector<int> arr(n);
    for(int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    int result = minimizeCost(k, arr);
    cout << result << endl;

    return 0;
}
