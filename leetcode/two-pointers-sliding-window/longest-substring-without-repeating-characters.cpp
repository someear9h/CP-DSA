// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

#include <iostream> // for input/output
#include <unordered_set> // for unordered_set
#include <climits> // for INT_MIN
using namespace std;

class Solution {
public:
    int solve(string s) {
        if(s.size() == 0) return 0;

        unordered_set<char> set;
        int l = 0, maxLength = INT_MIN;

        for(int r = 0; r < s.size(); r++) {
            
            if(set.find(s[r]) != set.end()) {

                while(l < r && set.find(s[r]) != set.end()) {

                    set.erase(s[l]);
                    l++;
                }
            }
            
            set.insert(s[r]);
            maxLength = max(maxLength, r - l + 1);
        }
        return maxLength;
    } 
};

int main() {
    Solution sol;
    string s = "pwwkew";
    cout << sol.solve(s) << endl;
    return 0;
}