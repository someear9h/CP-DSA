// https://leetcode.com/problems/unique-paths/description/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<int> dp(n, 1);

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }
};

int main() {
    Solution sol;
    int m = 3, n = 7;
    cout << sol.uniquePaths(m, n) << endl;
    
    return 0;
}