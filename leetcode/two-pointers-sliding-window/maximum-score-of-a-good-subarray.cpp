// https://leetcode.com/problems/maximum-score-of-a-good-subarray/description/

#include <iostream>
#include <vector>
using namespace std;

int maximumScore(vector<int> &nums, int k) {
    int l = k, r = k;
    int min_val = nums[k];
    int max_score = nums[k];

    while(l > 0 || r < nums.size() - 1) {
        if(l == 0 || (r < nums.size() - 1 && nums[r + 1] > nums[l - 1])) {
            r++;
        } else {
            l--;
        }
        min_val = min(min_val, min(nums[l], nums[r]));
        max_score = max(max_score, (r -l + 1) * min_val);
    }
    return max_score;
}

int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    int t;
    cin >> t;
    while(t--) {

        int n, k;
        cin >> n >> k;
    
        vector<int> nums(n);
        for(int i = 0; i < n; i++) {
            cin >> nums[i];
        }
    
        cout << maximumScore(nums, k) << endl;
    }
    
    return 0;
}