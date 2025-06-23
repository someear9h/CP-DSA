#include <iostream>
#include <string>
using namespace std;

class Solution {
public:
    int maxVowels(string s, int k) {
        int l = 0, ans = INT_MIN, count = 0;

        for(int r = 0; r < s.size(); r++) {
            // shrinking 
            while(k < r - l + 1) {
                if(s[l] == 'a' || s[l] == 'e' || s[l] == 'i' || s[l] == 'o' || s[l] == 'u') {
                    count--;
                }
                l++;
                
            }

            if(s[r] == 'a' || s[r] == 'e' || s[r] == 'i' || s[r] == 'o' || s[r] == 'u') {
                count++;
            }
            
            ans = max(ans, count);
        }
        return ans;
    }
};

int main() {
    Solution sol;
    string s = "abciiidef";
    int k = 3;

    cout << sol.maxVowels(s, k) << endl;

    return 0;
}