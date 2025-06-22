#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <climits>
using namespace std;

bool solve(vector<int>& nums, int indexDiff, int valueDiff) {
    int i = 0, j = 0, n = nums.size();
    int minDiff = INT_MAX;
    map<int, int> mp; // store elements in the window and frequency of them
    
    while(j < n) {
        
        // shrinking condition
        // maintaing this condition: abs(i - j) <= indexDiff.
        if(abs(j - i) > indexDiff) {
            mp[nums[i]]--;
            if(mp[nums[i]] == 0) mp.erase(nums[i]);
            i++;
        }

        // find lower bound and upper bound and calculate diff
        auto upper = mp.upper_bound(nums[j]);
        if(upper != mp.end()) {
            // we have found an upperbound in mp
            minDiff = min(minDiff, abs(nums[j] - upper->first)); 
        }

        if(upper != mp.begin()) {
            auto lower = prev(upper);
            minDiff = min(minDiff, abs(nums[j] - lower->first));
        }
        
        
        mp[nums[j]]++;
        j++;
    }
    return minDiff <= valueDiff;
}

int main() {
    
    vector<int> nums = {1,2,3,1};
    int indexDiff = 3;
    int valueDiff = 0;

    cout << (solve(nums, indexDiff, valueDiff) ? "true" : "false")<< endl;
    return 0;
}