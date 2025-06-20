#include <iostream>
#include <vector>
#include <unordered_set>
#include <unordered_map>
using namespace std;

int countCompleteSubarrays(vector<int>& nums) {
    int k = unordered_set<int>(nums.begin(), nums.end()).size();
    unordered_map<int, int> mp;
    int l = 0, count = 0;

    for(int r = 0; r < nums.size(); r++) {
        mp[nums[r]]++;

        while(mp.size() == k) {
            count += nums.size() - r;
            mp[nums[l]]--;
            if(mp[nums[l]] == 0) mp.erase(nums[l]);
            l++;
        }
    }
    return count;
}

int main() {
    vector<int> nums = {1,3,1,2,2};
    cout << countCompleteSubarrays(nums) << endl;
    
}