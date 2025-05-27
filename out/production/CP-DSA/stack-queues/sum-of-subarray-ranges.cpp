// https://leetcode.com/problems/sum-of-subarray-ranges/description/

#include <iostream>
#include <stack>
#include <vector>
#define ll long long
using namespace std;

class Solution {
public:
    ll subArrayRanges(vector<int> &nums) {
        int n = nums.size();
        ll sumMax = 0, sumMin = 0;
        stack<int> sMin, sMax;

        // find no of subarrays element is maximum
        for(int i = 0; i <= n; i++) {
            while(!sMax.empty() && (i == n || nums[sMax.top()] < nums[i])) {
                int idx = sMax.top(); // index of current maximum element
                sMax.pop();
                int left = sMax.empty() ? -1 : sMax.top(); // Previous greater element index
                sumMax += (ll) nums[idx] * (idx - left) * (i - idx);
            }
            sMax.push(i);
        }

        // find no of subarrays element is minimum
        for(int i = 0; i <= n; i++) {
            while(!sMin.empty() && (i == n || nums[sMin.top()] > nums[i])) {
                int idx = sMin.top(); // index of current minimum element
                sMin.pop();
                int left = sMin.empty() ? -1 : sMin.top(); // Previous smaller element index
                sumMin += (ll) nums[idx] * (idx - left) * (i - idx);
            }
            sMin.push(i);
        }

        return sumMax - sumMin;
    }
};

int main() {
    Solution sol;
    vector<int> nums = {1, 2, 3};
    cout << sol.subArrayRanges(nums) << endl;

    return 0;
}
                
                
