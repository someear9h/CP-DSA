// https://leetcode.com/problems/out-of-boundary-paths/description/

#include <iostream>
#include <vector>
using namespace std;

int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
    int MOD = 1e9 + 7;
    vector<vector<int>> prev(m, vector<int>(n, 0));
    vector<vector<int>> curr(m, vector<int>(n, 0));

    for(int move = 1; move <= maxMove; move++) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int count = 0;

                // up
                if(i == 0) count += 1;
                else count += prev[i - 1][j];

                // down
                if(i == m - 1) count += 1;
                else count += prev[i + 1][j];

                // left
                if(j == 0) count += 1;
                else count += prev[i][j - 1];

                // right
                if(j == n - 1) count += 1;
                else count += prev[i][j + 1];

                curr[i][j] = count % MOD;
            }
        }
        prev = curr; 
    }
    return prev[startRow][startColumn];
}

int main() {
    int m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0;
    int result = findPaths(m, n, maxMove, startRow, startColumn);
    cout << result << endl;
    return 0;
}
