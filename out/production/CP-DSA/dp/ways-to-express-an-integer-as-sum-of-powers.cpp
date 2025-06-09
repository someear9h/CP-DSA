// https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    const int MOD = 1e9 + 7;
    int numberOfWays(int n, int x) {
        vector<int> nums;
        for(int k = 1; ;k++) {
            int num = pow(k, x);
            if(num > n) break;
            nums.push_back(num);
        }

        vector<int> dp(n + 1, 0);
        dp[0] = 1;
        for(const auto& num : nums) {
            for(int t = n; t >= num; t--) {
                dp[t] = (dp[t] + dp[t - num]) % MOD;
            }
        }
        return dp[n];
    }
};

int main() {
    Solution sol;
    int n = 4, x = 1;
    cout << sol.numberOfWays(n, x) << endl;
    return 0;
}