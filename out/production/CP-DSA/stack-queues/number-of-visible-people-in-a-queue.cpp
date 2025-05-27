// https://leetcode.com/problems/number-of-visible-people-in-a-queue/description/

#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class Solution {
public:
    vector<int> canSeePersonCount(vector<int> &heights) {
        int n = heights.size();
        stack<int> st;
        vector<int> res(n, 0);
        
        for(int i = 0; i < n; i++) {
            while(!st.empty() && heights[st.top()] <= heights[i]) {
                res[st.top()]++;
                st.pop();
            }

            if(!st.empty()) {
                res[st.top()]++;
            }

            st.push(i);
        }
        return res;
    }
};

int main() {
    Solution sol;
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    vector<int> heights1 ={5,1,2,3,10};
    vector<int> heights2 = {10,6,8,5,11,9};
    for(int i = 0; i < heights1.size(); i++) {
        cout << sol.canSeePersonCount(heights1)[i] << " ";
    }
    cout << endl;
    for(int i = 0; i < heights2.size(); i++) {
        cout << sol.canSeePersonCount(heights2)[i] << " ";
    }

    return 0;
}

               