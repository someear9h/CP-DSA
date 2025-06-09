// https://leetcode.com/problems/coin-change-ii/description/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int f(int idx, int target, vector<int>& arr, vector<vector<int>>& dp) {
        // base cases
        if(idx == 0) {
            if(target % arr[0] == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        // check dp table
        if(dp[idx][target] != -1) {
            return dp[idx][target];
        }

        // taken notTaken logic
        int notTaken = f(idx - 1, target, arr, dp);
        int taken = 0;
        if(arr[idx] <= target) {
            taken = f(idx, target - arr[idx], arr, dp);
        }

        return dp[idx][target] = taken + notTaken;
    }

    int change(int amount, vector<int>& coins) {
        int n = coins.size();
        vector<vector<int>> dp(n, vector<int>(amount + 1, -1));

        return f(n - 1, amount, coins, dp);
    }   
};

int main() {
    Solution sol;
    vector<int> coins = {1, 2, 5};
    int amount = 5;

    cout << sol.change(amount, coins) << endl;
    return 0;
}