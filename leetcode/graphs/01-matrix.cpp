#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution {
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> res(m, vector<int>(n, 0));
        vector<vector<bool>> vis(m, vector<bool>(n, false));
        queue<pair<pair<int, int>, int>> q;

        // loop through mat and if cell already has 0 then mark it visited and 0 steps
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 0) {
                    vis[i][j] = true;
                    q.push({{i, j}, 0});
                }
            }
        }

        // make vectors for going in all 4 directions
        vector<int> rowd = {-1, 1, 0, 0};
        vector<int> cold = {0, 0, -1, 1};

        // while q is not empty explore all directions
        while(!q.empty()) {
            int row = q.front().first.first;
            int col = q.front().first.second;
            int steps = q.front().second;
            q.pop();
            res[row][col] = steps;

            for(int d = 0; d < 4; d++) {
                int nRow = row + rowd[d];
                int nCol = col + cold[d];

                // if new row and column is valid and not visited, push into queue
                if(nRow < m && nRow >= 0 && nCol < n && nCol >= 0 && vis[nRow][nCol] == false) {
                    vis[nRow][nCol] = true;
                    q.push({{nRow, nCol}, steps + 1});
                }
            }
        }
        return res;
    }
};

int main() {
    Solution sol;
    vector<vector<int>> mat = {
        {0, 0, 0},
        {0, 1, 0},
        {1, 1, 1}
    };

    // Call updateMatrix and store result
    vector<vector<int>> result = sol.updateMatrix(mat);

    // Print the resulting matrix
    for(const auto& row : result) {
        for(int val : row) {
            cout << val << " ";
        }
        cout << endl;
    }

    return 0;
}