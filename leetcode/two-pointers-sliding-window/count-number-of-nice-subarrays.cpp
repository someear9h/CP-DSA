// https://leetcode.com/problems/count-number-of-nice-subarrays/

#include <iostream>
#include <vector>
using namespace std;

int solve(vector<int>& nums, int k) {
    int l = 0, count = 0, res = 0;

    for(int r = 0; r < nums.size(); r++) {
        if(nums[r] % 2 == 1) {
            k--;
            count = 0;
        }

        while(k == 0) {
            if(nums[l] % 2 == 1) {
                k++;
            }
            l++;
            count++;
        }
        res += count;
    }
    return res;
}

int main() {
    vector<int> nums = {1, 1, 2, 1, 1};
    int k = 3;
    cout << solve(nums, k) << endl;
}