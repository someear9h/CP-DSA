#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int findWays(vector<int> &arr, int k) {
        int n = arr.size();
        vector<vector<int>> dp(n, vector<int>(k + 1, 0));

        // base case
        for(int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        // base case
        if(arr[0] <= k) {
            dp[0][arr[0]] = 1;
        }

        for(int i = 1; i < n; i++) {
            for(int target = 1; target <= k; target++) {
                int nonPick = dp[i - 1][target];
                int pick = 0;
                if(arr[i] <= target) {
                    pick = dp[i - 1][target - arr[i]];
                }

                dp[i][target] = pick + nonPick;
            }
        }
        return dp[n - 1][k];
    }
};

int main() {
    Solution sol;
    vector<int> arr = {1, 1, 4, 5};
    int k = 5;

    cout << sol.findWays(arr, k) << endl;

    return 0;
}
