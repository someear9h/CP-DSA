// https://www.geeksforgeeks.org/problems/geek-jump/1

#include <iostream>
#include <vector>
#include <climits>
using namespace std;

int main() {
    vector<int> heights = {30,10,60,10,60,50};
    int n =  heights.size();
    vector<int> dp(n, -1);
    dp[0] = 0;

    for(int i = 1; i < n;  i++) {
        int jumpTwo = INT_MAX;
        int jumpOne = dp[i - 1] + abs(heights[i] - heights[i - 1]);
        if(i > 1) {
            jumpTwo = dp[i - 2] + abs(heights[i] - heights[i - 2]);
        }

        dp[i] = min(jumpOne, jumpTwo);
    }
    cout << dp[n - 1] << endl;
}