#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    bool f(int idx, int target, vector<int> &nums, vector<vector<int>> &dp) {
        // base cases
        if(target == 0) return true;

        if(idx == 0) return nums[0] == target;

        // check dp
        if(dp[idx][target] != -1) return dp[idx][target];

        bool nonPick = f(idx - 1, target, nums, dp);
        bool pick = false;
        if(nums[idx] <= target) {
            pick = f(idx - 1, target - nums[idx], nums, dp);
        }

        return dp[idx][target] = pick || nonPick;
    }

    bool solve(vector<int> &nums) {
        int n = nums.size();
        
        int total_sum = 0;
        for(int i = 0; i < n; i++) {
            total_sum += nums[i];
        }

        if(total_sum % 2 == 1) {
            return false;
        } else {
            int k = total_sum / 2;
            vector<vector<int>> dp(n, vector<int>(k + 1, -1));
            return f(n - 1, k, nums, dp);
        }
    }
};

int main() {
    Solution sol;
    vector<int> nums = {2, 3, 3, 3, 4, 5};
    cout << (sol.solve(nums) ? "True" : "False" )<< endl;

    return 0;
}
        
