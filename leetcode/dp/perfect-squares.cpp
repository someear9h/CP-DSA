// https://leetcode.com/problems/perfect-squares/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int numSquares(int n) {
        vector<int> dp(n + 1, INT_MAX);
        dp[0] = 0; // 0 ways to form if n is 0

        for(int x = 1; x * x <= n; x++) {
            int num = x * x;
            for(int t = num; t <= n; t++) {
                dp[t] = min(dp[t], dp[t - num] + 1);
            }
        }
        return dp[n];
    }
};

int main() {
    Solution sol;
    int n = 12;

    cout << sol.numSquares(n) << endl;

    return 0;
}