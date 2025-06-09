// https://leetcode.com/problems/maximal-rectangle/description/

#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class Solution {
public:
    int maximalRectangle(vector<vector<char>> &matrix) {
        int rows = matrix.size();
        int cols = matrix[0].size();
        vector<int> heights(cols + 1, 0);
        int max_area = 0;

        for(const auto &row : matrix) {
            for(int i = 0; i < cols; i++) {
                heights[i] = row[i] == '1' ? heights[i] + 1 : 0;
            }
            stack<int> st;
            for(int i = 0;i < heights.size(); i++) {
                while(!st.empty() && heights[st.top()] > heights[i]) {
                    int h = heights[st.top()];
                    st.pop();
                    int w = st.empty() ? i : i - st.top() - 1;
                    max_area = max(max_area, h * w);
                }
                
                st.push(i);
            }
        }
        return max_area;
    }
};

int main() {
    Solution sol;
    vector<vector<char>> matrix = {{'1','0','1','0','0'}, 
                               {'1','0','1','1','1'}, 
                               {'1','1','1','1','1'},
                               {'1','0','0','1','0'}};


    cout << sol.maximalRectangle(matrix) << endl;

    return 0;
}