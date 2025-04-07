#include <iostream>
#include <vector>
#include <climits> // for INT_MAX;
using namespace std;

class Solution {
public:

    int solve(vector<vector<int>> &matrix) {
        
        int n = matrix.size();
        int m = matrix[0].size();
        vector<vector<int>> dp(n, vector<int>(m, 0));
        
        // fill the first row of the dp (base case)
        for(int j = 0;j < m; j++) {
            dp[0][j] = matrix[0][j];
        }
        
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int up = matrix[i][j] + dp[i - 1][j];

                int diagonalLeft = matrix[i][j];
                if(j - 1 >= 0) {
                    diagonalLeft += dp[i - 1][j - 1];
                } else {
                    diagonalLeft += 1e9;
                }

                int diagonalRight = matrix[i][j];
                if(j + 1 < m) {
                    diagonalRight += dp[i - 1][j + 1];
                } else {
                    diagonalRight += 1e9;
                }

                dp[i][j] = min(up, min(diagonalLeft, diagonalRight));
            }
        }
        int mini = INT_MAX;
        for(int j = 0; j < m; j++) {
            mini = min(mini, dp[n - 1][j]);
        }
        return mini;
    }
};

int main() {
    Solution sol;
    vector<vector<int>> matrix = {
        {1, 2, 10, 4},
        {100, 3, 2, 1},
        {1, 1, 20, 2},
        {1, 2, 2, 1}
    };

    cout << sol.solve(matrix) << endl;
    return 0;
}
        