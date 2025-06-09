// https://leetcode.com/problems/minimum-path-sum/description/

#include <iostream>
#include <vector>
#include <climits>
using namespace std;

int solve(vector<vector<int>> &grid) {
    int n = grid.size();
    int m = grid[0].size();
    vector<int> prev(m, 0);
    for(int i = 0; i < n; i++) {
        vector<int> temp(m, 0);
        for(int j = 0;j < m; j++) {
            if(i == 0 && j == 0) {
                temp[j] = grid[i][j];
            } else {
                int up = 0;
                if(i > 0) {
                   up = grid[i][j] + prev[j]; // Include the minimum path sum from above (previous row)
                } else {
                    up += 1e9;
                }

                int left = grid[i][j];
                if(j > 0) {
                    left += temp[j - 1];
                } else {
                    left += 1e9;
                }

                // store the minimum path sum in temp[j]
                temp[j] = min(up, left);
            }
        }
        prev = temp;
    }
    return prev[m - 1];
    
}

int main() {
    vector<vector<int>> grid = {
        {5, 9, 6},
        {11, 5, 2}
    };

    cout << solve(grid) << endl;

    return 0;
}