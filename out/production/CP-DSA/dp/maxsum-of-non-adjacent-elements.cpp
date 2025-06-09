// https://leetcode.com/problems/house-robber-ii/description/
// Note: we have the same version of it if house robber-i where only solve function would work

#include <iostream>
#include <vector>
using namespace std;

int solve(vector<int> &arr) {
    int n = arr.size();
    int prev = arr[0];
    int prev2 = 0;

    for(int i = 1; i < n; i++) {
        int pick = arr[i];
        if(i > 1) {
            pick += prev2;
        }

        int nonPick = 0 + prev;
        int curr = max(pick, nonPick); // Maximum sum ending at the current element
        prev2 = prev;
        prev = curr;
    }
    return prev;
}

int rob(vector<int> &nums) {
    int n = nums.size();
    vector<int> temp1, temp2;

    if(n == 1) return nums[0];

    for(int i = 0; i < n; i++) {
        if(i != 0) temp1.push_back(nums[i]);
        if(i != n-1) temp2.push_back(nums[i]);
    }

    return max(solve(temp1), solve(temp2));
}

int main() {
    vector<int> arr = {2,7,9,3,1};
    cout << solve(arr) << endl;

    return 0;
}