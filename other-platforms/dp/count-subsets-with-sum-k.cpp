// https://www.naukri.com/code360/problems/number-of-subsets_3952532
// space optimised approach

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int findWays(vector<int> &arr, int k) {
        int n = arr.size();
        vector<int> prev(k + 1, 0);

        // write base cases
        prev[0] = 1;
        if(arr[0] <= k) {
            prev[arr[0]] = 1;
        }

        for(int idx = 1; idx < n; idx++) {
            vector<int> curr(k + 1, 0);
            curr[0] = 1;

            for(int target = 1; target <= k; target++) {
                int notTaken = prev[target];

                int taken = 0;
                if(arr[idx] <= target) {
                    taken = prev[target - arr[idx]];
                }

                curr[target] = taken + notTaken;
            }
            prev = curr;
        }
        return prev[k];
    }
};

int main() {
    Solution sol;
    
    vector<int> arr = {1, 2, 2, 3};
    int k = 3;

    cout << sol.findWays(arr, k) << endl;  // Output: 0 (empty not allowed)
    
    return 0;
}