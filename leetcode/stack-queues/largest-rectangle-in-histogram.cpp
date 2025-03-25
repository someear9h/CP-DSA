#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class Solution {
public:
    int largestRectangleArea(vector<int> &heights) {
        int maxArea = 0;
        stack<int> st;

        for(int i = 0; i < heights.size(); i++) {
            while(!st.empty() && heights[st.top()] > heights[i]) {
                int elementIndex = st.top();
                st.pop();

                // nse = next smaller element, pse = previous smaller element
                // nse and pse for heights[elementIndex]
                int nseIdx= i, pseIdx = st.empty() ? -1 : st.top();
                int width = nseIdx - pseIdx - 1;
                maxArea = max(maxArea, heights[elementIndex] * width);
            }
            st.push(i);
        }

        // for elements which dont have nse or pse
        while(!st.empty()) {
            int nseIdx = heights.size();
            int elementIdx = st.top();
            st.pop();
            int pseIdx = st.empty() ? -1 : st.top();

            maxArea = max(maxArea, (nseIdx - pseIdx - 1) * heights[elementIdx]);
        }

        return maxArea;
    }
};

int main() {
    Solution sol;
    vector<int> heights = {2, 1, 5, 6, 2, 3};
    cout << sol.largestRectangleArea(heights) << endl;

    return 0;
}