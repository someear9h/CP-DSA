// https://leetcode.com/problems/trapping-rain-water/description/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public: 
    int trap(vector<int> &height) {
        int n = height.size();
        int waterTrapped = 0;

        int l = 0, r = n - 1;
        int leftMax = height[l], rightMax = height[r];
        while(l <= r) {
            if(leftMax < rightMax) {
                waterTrapped += leftMax - height[l];
                l++;
                if(l < n) {
                    leftMax = max(leftMax, height[l]);
                }
            } else {
                waterTrapped += rightMax - height[r];
                r--;
                if(r >= 0) {
                    rightMax = max(rightMax, height[r]);
                }
            }
        }
        return waterTrapped;
    }
};

int main() {
    Solution sol;
    vector<int> height = {0,1,0,2,1,0,1,3,2,1,2,1}; // output = 6
    
    cout << sol.trap(height) << endl;
    return 0;
}