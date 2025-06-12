// https://leetcode.com/problems/koko-eating-bananas/description/

#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

class Solution {
public:
    int minEatingSpeed(vector<int>& piles, int h) {
    int l = 1, r = *max_element(piles.begin(), piles.end());
    int minSpeed = r;

    while(l <= r) {
        int k = l + (r -l) / 2;
        long long hours = 0;

        for(int p : piles) {
            hours += ceil((double) p / k);
        }

        if(hours <= h) {
            minSpeed = min(minSpeed, k);
            r = k - 1; // reduce speed
        } else {
            l = k + 1; // increase speed
        }
    }

    return minSpeed;
    }
};

int main() {
    Solution sol;
    vector<int> piles = {3,6,7,11};
    int h = 8;
    cout << sol.minEatingSpeed(piles, h) << endl;
}