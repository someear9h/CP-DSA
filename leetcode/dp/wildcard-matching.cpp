// https://leetcode.com/problems/wildcard-matching/

#include <iostream>
#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    bool isAllStars(string& pattern, int i) {
        for (int j = 1; j <= i; j++) {
            if (pattern[j - 1] != '*') return false;
        }
        return true;
    }

    bool isMatch(string s, string p) {
        int n = s.size(), m = p.size();
        vector<vector<bool>> dp(n + 1, vector<bool>(m + 1, false));

        dp[0][0] = true; // empty string matches empty pattern

        for (int j = 1; j <= m; j++) {
            dp[0][j] = isAllStars(p, j); // if pattern is all '*'
        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = false; // non-empty string can't match empty pattern
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p[j - 1] == s[i - 1] || p[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[n][m];
    }
};


int main() {
    Solution sol;
    string s = "aa";
    string t = "*";

    if(sol.isMatch(s, t)) {
        cout << "True" << endl;
    } else {
        cout << "False" << endl;
    }
    return 0;
}
