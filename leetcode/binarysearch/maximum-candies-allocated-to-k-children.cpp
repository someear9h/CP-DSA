// https://leetcode.com/problems/maximum-candies-allocated-to-k-children/description/

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maximumCandies(vector<int>& candies, long long k) {
        int left = 1, right = *max_element(candies.begin(), candies.end());
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long long sum = 0; // number of children can be fed mid canides

            for (int& c : candies) {
                sum += c / mid;
            }

            if (sum < k) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }

        return ans;
    }
};

int main() {
    Solution sol;
    
    vector<int> candies = {5,8,6};
    int k = 3;
    cout << sol.maximumCandies(candies, k) << endl;
    return 0;
}