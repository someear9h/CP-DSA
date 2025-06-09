// https://leetcode.com/problems/next-greater-element-ii/description/

#include <iostream>
#include <vector>
#include <stack>

using namespace std;

class Solution {
public:
    vector<int> nextGreaterCircular(vector<int> &nums) {
        
        int n = nums.size();
        vector<int> result(n, -1); // -1 for elements with no greater elements
        
        stack<int> idxStack;
        
        for(int i = 0; i < 2 * n; i++) {
            int currIdx = i % n; // for circular behaviour
            
            while(!idxStack.empty() && nums[currIdx] > nums[idxStack.top()]) {
                
                // nums[currIdx] is greater element for nums[idxStack.top()]
                result[idxStack.top()] = nums[currIdx];
                idxStack.pop();
            }
            
            
            // push in stack only for the first pass
            if(i < n) {
                idxStack.push(i);
            }
        }
        
        return result;
    }
};
    
int main() {
    Solution sol;

    vector<int> nums1 = {1,2,3,4,3};
    vector<int> res = sol.nextGreaterCircular(nums1);
    for(int i = 0; i < nums1.size(); i++) {
        cout << res[i] << " ";
    }
    cout << "\n";

    return 0;
}