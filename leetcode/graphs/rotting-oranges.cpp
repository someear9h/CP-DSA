#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution {
public:
    int orangesRotting(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int processed = 0, totalOranges = 0;
        queue<pair<int, int>> q;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] != 0) totalOranges++;
                if(grid[i][j] == 2) q.push({i, j}); // push the rotten orange in the queue
            }
        }

        // Directions for 4-connected neighbors (up, down, left, right)
        int rowd[4] = {-1, 1, 0, 0};
        int cold[4] = {0, 0, -1, 1};
        int time = 0;

        while(!q.empty()) {
            int currentLayerSize = q.size();
            processed += currentLayerSize;

            for(int i = 0; i < currentLayerSize; i++) {
                auto [row, col] = q.front();
                q.pop();

                // explore all 4 directions
                for(int d = 0; d < 4; d++) {
                    int newRow = row + rowd[d];
                    int newCol = col + cold[d];

                    if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                        continue;
                    }

                    if(grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        q.push({newRow, newCol});
                    }

                }
            }

            if(!q.empty()) time++;
        }

        return (processed == totalOranges) ? time : -1;
    }
};

int main() {
    Solution sol;

    vector<vector<int>> grid = {
        {2, 1, 1},
        {1, 1, 0},
        {0, 1, 1}
    };

    int result = sol.orangesRotting(grid);
    cout << result << endl;
    return 0;
}