// https://leetcode.com/problems/longest-common-subsequence/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int longestCommonSubsequence(string text1, string text2) {
        int n = text1.size(), m = text2.size();

        vector<int> prev(m + 1, 0), curr(m + 1, 0);

        for(int idx1 = 1; idx1 <= n; idx1++) {
            for(int idx2 = 1; idx2 <= m; idx2++) {
                if(text1[idx1 - 1] == text2[idx2 - 1]) {
                    curr[idx2] = 1 + prev[idx2 - 1];
                } else {
                    curr[idx2] = max(prev[idx2], curr[idx2 - 1]);
                }
            }
            prev = curr;
        }
        return prev[m];
    }
};

int main() {
    Solution sol;
    string text1 = "abcde", text2 = "ace";

    cout << sol.longestCommonSubsequence(text1, text2) << endl;
    return 0;
}