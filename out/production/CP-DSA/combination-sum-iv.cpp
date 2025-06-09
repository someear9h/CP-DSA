// https://leetcode.com/problems/combination-sum-iv/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int combinationSum4(vector<int>& nums, int target) {
        vector<unsigned int> dp(target + 1, 0);
        dp[0] = 1;
        for (int t = 1; t <= target; t++) {
            for (int num : nums) {
                if (t >= num) {
                    dp[t] += dp[t - num];
                }
            }
        }
        return dp[target];
    }
};

int main() {
    Solution sol;

    vector<int> nums = {1, 2, 3};
    int target = 4;

    cout << sol.combinationSum4(nums, target) << endl;

    return 0;
}