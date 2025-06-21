#include <iostream>
#include <vector>
#include <deque>
using namespace std;

int solve(vector<int>& nums, int k) {
    // make a prefix sum array and store prefix sum of nums
    int n =  nums.size();
    vector<long long> preSum(n + 1, 0);

    for(int i = 0; i < n; i++) {
        preSum[i + 1] = preSum[i] + nums[i];
    }
    
    deque<int> dq;
    int minLen = n + 1;

    // loop through prefix sum array
    for(int r = 0; r <= n; r++) {
        
        // shrinking condtion: preSum[r + 1] - preSum[l] >= k   
        while(!dq.empty() && preSum[r] - preSum[dq.front()] >= k) {
            minLen = min(minLen, r - dq.front());
            dq.pop_front();
        }


        // avoid bad subarrays
        while(!dq.empty() && preSum[r] <= preSum[dq.back()]) {
            dq.pop_back();
        }
        
        dq.push_back(r);
    }

    return (minLen == n + 1) ? -1 : minLen;
}

int main() {
    
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    vector<int> nums = {2,-1,2};
    int k = 3;

    cout << solve(nums, k) << endl;
}