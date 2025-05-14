// https://leetcode.com/problems/cherry-pickup/description/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int cherryPickup(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<vector<int>>> dp(n, vector<vector<int>>(n, vector<int>(n, -1e9)));

        // Base case
        dp[n - 1][n - 1][n - 1] = grid[n - 1][n - 1];

        for (int r1 = n - 1; r1 >= 0; r1--) {
            for (int c1 = n - 1; c1 >= 0; c1--) {
                for (int r2 = n - 1; r2 >= 0; r2--) {
                    int c2 = r1 + c1 - r2;
                    if (c2 < 0 || c2 >= n) continue;

                    if (grid[r1][c1] == -1 || grid[r2][c2] == -1) continue;

                    int maxCherries = -1e9;

                    // Try 4 directions from the next moves
                    if (r1 + 1 < n && r2 + 1 < n)
                        maxCherries = max(maxCherries, dp[r1 + 1][c1][r2 + 1]);
                    if (r1 + 1 < n && c2 + 1 < n)
                        maxCherries = max(maxCherries, dp[r1 + 1][c1][r2]);
                    if (c1 + 1 < n && r2 + 1 < n)
                        maxCherries = max(maxCherries, dp[r1][c1 + 1][r2 + 1]);
                    if (c1 + 1 < n && c2 + 1 < n)
                        maxCherries = max(maxCherries, dp[r1][c1 + 1][r2]);

                    if (maxCherries < 0 && !(r1 == n - 1 && c1 == n - 1)) continue;

                    int cherries = grid[r1][c1];
                    if (r1 != r2 || c1 != c2) cherries += grid[r2][c2];

                    dp[r1][c1][r2] = cherries + max(0, maxCherries);
                }
            }
        }
        return max(0, dp[0][0][0]);
    }
};

int main() {
    Solution sol;
    vector<vector<int>> grid = {
        {0, 1, -1},
        {1, 0, -1},
        {1, 1,  1}
    };

    cout << sol.cherryPickup(grid) << endl;
    return 0;
}
