#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();
        if (n == 0) return 0; // No heights, no water
        
        stack<int> st; // Monotonic decreasing stack (stores indices)
        int trappedWater = 0;

        for (int i = 0; i < n; i++) {
            // If current height is greater than stack top, calculate trapped water
            while (!st.empty() && height[i] > height[st.top()]) {
                int valley = st.top();
                st.pop(); // Remove the valley
                
                if (st.empty()) break; // No left boundary, so no trapped water
                
                int leftBoundary = st.top();
                int width = i - leftBoundary - 1; // Distance between left boundary and current height
                int boundedHeight = min(height[leftBoundary], height[i]) - height[valley];

                trappedWater += width * boundedHeight; // Area formula
            }
            
            // Push current height index to stack
            st.push(i);
        }
        
        return trappedWater;
    }
};

int main() {
    Solution sol;
    vector<int> height = {4, 2, 0, 3, 2, 5};

    cout << sol.trap(height) << endl; // Output: 9

    return 0;
}
