// https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

// minimum insertions = length of the string - length longest palindromic sequence of string

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
    
    int longestPalindromeSubseq(string s) {
        string t = s;
        reverse(t.begin(), t.end());
        return longestCommonSubsequence(s, t);
    }

    int minimumInsertions(string s) {
        int n = s.size();
        return n - longestPalindromeSubseq(s);
    }
};

int main() {
    Solution sol;
    string test = "abcaa";
    cout << sol.minimumInsertions(test) << endl;
    return 0;
}