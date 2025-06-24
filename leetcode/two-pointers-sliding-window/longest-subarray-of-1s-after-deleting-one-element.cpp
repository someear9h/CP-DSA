#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int left = 0;
        int max_length = 0;
        int last_zero = -1;

        for (int right = 0; right < nums.size(); right++) {
            if (nums[right] == 0) {
                left = last_zero + 1;
                last_zero = right;
            }

            max_length = max(max_length, right - left);
        }

        return max_length;        
    }
};

int main() {
    Solution sol;
    vector<int> nums = {1, 1, 0, 1, 1, 1, 0, 1};  // Example input
    int result = sol.longestSubarray(nums);
    cout << result << endl;
    return 0;
}