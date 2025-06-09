// https://leetcode.com/problems/delete-operation-for-two-strings/

#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Solution {
public:
    int longestCommonSubsequence(string s, string t) {
        int m = s.size();
        int n = t.size();
        vector<int> prev(m + 1, 0), curr(m + 1, 0);
        
        for(int i = 1; i <= n; i++) {  
            for(int j = 1; j <= m; j++) {
                if(s[j - 1] == t[i - 1]) { 
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }
        return prev[m];
    }
    int minDistance(string word1, string word2) {
        int n = word1.size(), m = word2.size();
        int lcs = longestCommonSubsequence(word1, word2);
        return (m - lcs) + (n - lcs); // main thing here
    }
};

int main() {
    Solution sol;
    string word1 = "sea";
    string word2 = "eat";

    cout << sol.minDistance(word1, word2) << endl;
    return 0;
}