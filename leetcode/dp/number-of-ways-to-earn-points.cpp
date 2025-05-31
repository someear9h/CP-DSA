// https://leetcode.com/problems/number-of-ways-to-earn-points/description/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    const int MOD = 1e9 + 7;
    int waysToReachTarget(int target, vector<vector<int>>& types) {
        // make dp table
        vector<int> dp(target + 1, 0);
        dp[0] = 1; // 1 way of getting zero points

        for(const auto& type : types) {
            int count = type[0];
            int marks = type[1];

            for(int t =  target; t >= 0; t--) {
                int ways = 0;
                for(int k = 1; k <= count; k++) {
                    if((t - k * marks) >= 0) {
                        ways = (ways + dp[t - k * marks]) % MOD;
                    } else {
                        break;
                    }
                }
                dp[t] = (dp[t] + ways) % MOD;
            }
        }
        return dp[target];
    }
};

int main() {
    Solution sol;

    vector<vector<int>> types = {
        {6, 1}, {3, 2}, {2, 3}
    };

    int target = 6;

    cout << sol.waysToReachTarget(target, types) << endl;
    return 0;
}