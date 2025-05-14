// https://leetcode.com/problems/triangle/description/

#include <iostream>
#include <vector>
using namespace std;

int solve(vector<vector<int>> &triangle) {
    int n = triangle.size();
    vector<int> front(n, 0);
    vector<int> curr(n, 0);

    for(int j = 0; j < n; j++) {
        front[j] = triangle[n - 1][j];
    }

    for(int i = n - 2; i >= 0; i--) {
        for(int j = i; j >= 0; j--) {
            int down = triangle[i][j] + front[j];
            int diagonal = triangle[i][j] + front[j + 1];
            
            // Store the minimum of the two possible paths in the current row
            curr[j] = min(down, diagonal);
        }
        front = curr;
    }
    return front[0];
}

int main() {
    vector<vector<int>> m = {
        {2},
        {3, 4},
        {6, 5, 7},
        {4, 1, 8, 3}
    };

    cout << solve(m) << endl;
    return 0;
}