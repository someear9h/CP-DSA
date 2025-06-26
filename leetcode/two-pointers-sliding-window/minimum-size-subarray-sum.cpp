#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int l = 0, windowSum = 0, minLen = nums.size();
        bool isValid = false; // always false if windowSum never equals target
        
        for(int r = 0; r < nums.size(); r++) {
            windowSum += nums[r];

            while(windowSum >= target) {
                // shrink and try to find smaller lengths
                isValid = true;
                minLen = min(minLen, r - l + 1);
                windowSum -= nums[l];
                l++;
            }

        }
        return isValid ? minLen : 0;
    }
};

int main() {
    Solution sol;
    vector<int> nums = {2,3,1,2,4,3};
    int target = 7;

    cout << sol.minSubArrayLen(target, nums) << endl;
}