// https://leetcode.com/problems/maximal-square/description/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
    public:
        int maximalSquare(vector<vector<char>>& matrix) {
            int n = matrix.size(), m = matrix[0].size();
            vector<vector<int>> dp(n, vector<int>(m, 0));
            int maxSide = 0;
    
            // Fill the first row
            for (int j = 0; j < m; j++) {
                dp[0][j] = matrix[0][j] - '0';
                maxSide = max(maxSide, dp[0][j]);
            }
    
            // Fill the first column
            for (int i = 0; i < n; i++) {
                dp[i][0] = matrix[i][0] - '0';
                maxSide = max(maxSide, dp[i][0]);
            }
    
            // Fill rest of the dp table
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (matrix[i][j] == '1') {
                        dp[i][j] = 1 + min({dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]});
                        maxSide = max(maxSide, dp[i][j]);
                    }
                }
            }
    
            return maxSide * maxSide;
        }
    };
    

int main() {
    Solution sol;
    vector<vector<char>> matrix = {
        {'1', '1', '0'},
        {'1', '1', '1'},
        {'0', '1', '1'}
    };

    cout << sol.maximalSquare(matrix) << endl;
}