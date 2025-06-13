// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/

#include <iostream>
#include <vector>
#include <algorithm> // for *max_element
#include <numeric> // for accumulate
using namespace std;

int findMinDays(vector<int>& weights, int min_capacity) {
    int days = 1;
    int load = 0;
    for(int i = 0 ; i < weights.size(); i++) {
        if(load + weights[i] > min_capacity) {
            days++;
            load = weights[i];
        } else {
            load += weights[i];
        }
    }
    return days;
}

int shipWithDays(vector<int>& weights, int days) {
    int left =  *max_element(weights.begin(), weights.end());
    int right = accumulate(weights.begin(), weights.end(), 0);

    while(left <= right) {
        int mid = left + (right - left) / 2;
        if(findMinDays(weights, mid) <= days) {
            
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return left;
}

int main() {
    vector<int> weights = {3,2,2,4,1,4};
    int days = 3;
    cout << shipWithDays(weights, days) << endl;
    return 0;
}