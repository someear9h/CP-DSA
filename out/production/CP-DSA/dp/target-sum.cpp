#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int f(int idx, int target, vector<int>& arr, vector<vector<int>>& dp) {
        // Base case
        if (idx == 0) {
            if (target == 0 && arr[0] == 0) return 2;
            if (target == 0 || arr[0] == target) return 1;
            return 0;
        }

        if (dp[idx][target] != -1) return dp[idx][target];

        int nonPick = f(idx - 1, target, arr, dp);
        int pick = 0;
        if (arr[idx] <= target) {
            pick = f(idx - 1, target - arr[idx], arr, dp);
        }

        return dp[idx][target] = (pick + nonPick);
    }

    int findTargetSumWays(vector<int>& nums, int target) {
        int n = nums.size();
        int total_sum = 0;
        for (int i = 0; i < n; i++) total_sum += nums[i];

        if (total_sum - target < 0) return 0;
        if ((total_sum - target) % 2 == 1) return 0;

        int s2 = (total_sum - target) / 2;
        vector<vector<int>> dp(n, vector<int>(s2 + 1, -1));
        return f(n - 1, s2, nums, dp);
    }
};

int main() {
    Solution sol;
    std::vector<int> nums = {1,1,1,1,1};
    int target = 3;

    std::cout << sol.findTargetSumWays(nums, target) << "\n";
    return 0;
}