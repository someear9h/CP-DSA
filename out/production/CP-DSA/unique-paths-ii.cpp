// https://leetcode.com/problems/unique-paths-ii/description/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    // tabulation approach
    int solve(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size();
        int n = obstacleGrid[0].size();
        vector<vector<int>> dp(m, vector<int>(n, 0));

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                
                // base case
                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0; // cant be counted
                    continue;
                }
                int up = 0, left = 0;

                if(i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if(i > 0) {
                    up = dp[i - 1][j];
                }

                if(j > 0) {
                    left = dp[i][j - 1];
                }
                
                dp[i][j] = up + left;
            }
        }
        return dp[m - 1][n - 1];
    }
};
int main() {
    Solution sol;
    vector<vector<int>> maze = {
        {0, 0, 0},
        {0, 1, 0},
        {0, 0, 0}
    };
    cout << sol.solve(maze) << endl;
    return 0;
}