// https://leetcode.com/problems/longest-repeating-character-replacement/description/

#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Solution {
public:
    int solve(string s, int k) {
        int l = 0;
        int maxFreq = 0;
        int n = s.size();
        vector<int> freq(26, 0);
        int ans = 0;

        for(int r = 0; r < n; r++) {
            
            freq[s[r] - 'A']++;
            maxFreq = max(maxFreq, freq[s[r] - 'A']);
            
            if(r - l + 1 - maxFreq > k) {
                freq[s[l] - 'A']--;
                l++;
            }

            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};

int main() {
    Solution sol;
    string s = "AABABBA";
    int k = 1;
    cout << sol.solve(s, k) << endl;
}