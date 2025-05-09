// https://takeuforward.org/plus/dsa/problems/subset-sum-equals-to-target

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    bool subsetSumToK(vector<int> &arr, int k) {
        int n = arr.size();

        vector<vector<bool>> dp(n, vector<bool>(k + 1, false));

        // base case: if target is zero we can take any number from 0 to n - 1 so first column zero
        for(int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // base case: if arr[0] <= target then set dp[0][arr[0]] to true;
        if(arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        for(int i = 1; i < n; i++) {
            for(int target = 1; target <= k; target++) {
                bool nonPick = dp[i - 1][target];

                bool pick = false;
                if(arr[i] <= target) {
                    pick = dp[i - 1][target - arr[i]];
                }

                dp[i][target] = pick || nonPick;
            }
        }

        return dp[n - 1][k];
    }
};

int main() {
    Solution sol;
    vector<int> arr = {1, 2, 3, 4};
    int k = 4;

    
    if (sol.subsetSumToK(arr, k))
        cout << "Subset with the given target found";
    else
        cout << "Subset with the given target not found";

    return 0;
}