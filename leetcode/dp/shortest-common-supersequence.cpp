// https://leetcode.com/problems/shortest-common-supersequence/description/

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

string shortestCommonSupersequence(string str1, string str2) {
    // find lcs
    int n = str1.size(), m = str2.size();
    vector<vector<int>> dp(n + 1, vector<int>(m + 1));

    for(int i = 0; i <= n; i++) {
        dp[i][0] = 0;
    }

    for(int i = 0; i <= m; i++) {
        dp[0][i] = 0;
    }

    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= m; j++) {
            if(str1[i - 1] == str2[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    int len = dp[n][m];
    int i = n;
    int j = m;
    int idx = len - 1; // index of the ans (resultant) string
    string ans = "";

    // take lcs characters
    while(i > 0 && j > 0) {
        if(str1[i - 1] == str2[j - 1]) {
            ans += str1[i - 1];
            idx--, i--, j--;
        } else if(dp[i - 1][j] > dp[i][j - 1]) {
            ans += str1[i - 1];
            i--;
        } else {
            ans += str2[j - 1];
            j--;
        }
    }

    // take non lcs characters
    while(i > 0) {
        ans += str1[i - 1];
        i--;
    }
    while(j > 0) {
        ans += str2[j - 1];
        j--;
    }

    reverse(ans.begin(), ans.end());
    return ans;
}

int main() {
    string str1 = "brute";
    string str2 = "groot";

    cout << shortestCommonSupersequence(str1, str2) << endl;
    return 0;
}