#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution {
public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int color) {
        int m = image.size();      // Number of rows
        int n = image[0].size();   // Number of columns

        int originalColor = image[sr][sc];
        if (originalColor == color) return image;

        queue<pair<int, int>> q;
        q.push({sr, sc});
        image[sr][sc] = color;

        // Directions: up, down, left, right
        vector<int> rowDir = {-1, 1, 0, 0};
        vector<int> colDir = {0, 0, -1, 1};

        while (!q.empty()) {
            auto [row, col] = q.front();
            q.pop();

            for (int d = 0; d < 4; d++) {
                int newRow = row + rowDir[d];
                int newCol = col + colDir[d];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n &&
                    image[newRow][newCol] == originalColor) {
                    image[newRow][newCol] = color;
                    q.push({newRow, newCol});
                }
            }
        }

        return image;
    }
};

int main() {
    vector<vector<int>> image = {
        {1, 1, 1},
        {1, 1, 0},
        {1, 0, 1}
    };
    int sr = 1, sc = 1, color = 2;

    Solution sol;
    vector<vector<int>> result = sol.floodFill(image, sr, sc, color);

    cout << "Modified Image:\n";
    for (const auto& row : result) {
        for (int pixel : row) {
            cout << pixel << " ";
        }
        cout << endl;
    }

    return 0;
}