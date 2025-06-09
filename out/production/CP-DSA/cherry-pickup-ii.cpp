// https://leetcode.com/problems/cherry-pickup-ii/description/

#include <iostream>
#include <vector>
#include <climits> // INT_MIN
using namespace std;

int solve(vector<vector<int>> &grid) {
    int n = grid.size();
    int m = grid[0].size();

    vector<vector<int>> curr(m, vector<int>(m, 0));
    vector<vector<int>> front(m, vector<int>(m, 0));

    // fill the last row of dp
    for(int j1 = 0; j1 < m; j1++) {
        for(int j2 = 0; j2 < m; j2++) {
            if(j1 == j2) {
                front[j1][j2] = grid[n - 1][j1];
            } else {
                front[j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
            }
        }
    }

    for(int i = n - 2; i >= 0; i--) {
        for(int j1 = 0; j1 < m; j1++) {
            for(int j2 = 0; j2 < m; j2++) {

                int maxi = INT_MIN;
                for(int di = -1; di <= 1; di++) {
                    for(int dj = -1; dj <= 1; dj++) {
                        int ans = 0;
                        if(j1 == j2) {
                            ans = grid[i][j1];
                        } else {
                            ans = grid[i][j1] + grid[i][j2];
                        }

                        // check if movement doesnt get out of bonds
                        if((j1 + di < 0 || j1 + di >= m) || (j2 + dj < 0 || j2 + dj >= m)) {
                            ans += -1e9; // return small number so this path isnt considered
                        } 
                        else {
                            ans += front[j1 + di][j2 + dj];
                        }

                        maxi = max(maxi, ans);
                    }
                }
                curr[j1][j2] = maxi;
            }
        }
        front = curr;
    }
    return front[0][m -1];
}

int main() {
    vector<vector<int>> matrix{
        {2, 3, 1, 2},
        {3, 4, 2, 2},
        {5, 6, 3, 5},
    };

    cout << solve(matrix) << endl;
    return 0;
}