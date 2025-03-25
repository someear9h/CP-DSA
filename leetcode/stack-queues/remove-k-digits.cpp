// https://leetcode.com/problems/remove-k-digits/

#include <iostream>
#include <string>
#include <stack>
#include <algorithm> // reverse
using namespace std;

class Solution {
public:
    string removeKDigits(string num, int k) {
        stack<char> st;
        for(char c : num) {
            while(!st.empty() && k > 0 && (st.top() - '0') > (c - '0')) {
                st.pop();
                k--;
            }
            st.push(c);
        }
        
        if(st.empty()) return "0";

        while(!st.empty() && k > 0) {
            st.pop();
            k--;
        }

        string res = "";
        while(!st.empty()) {
            res += st.top();
            st.pop();
        }

        reverse(res.begin(), res.end());

        // remove leading zeros
        int i = 0;
        while(i < res.size() && res[i] == '0') {
            i++;
        }

        res = res.substr(i); // gives string starting from ith index to end
        return res.empty() ? "0" : res;
    }
};

int main() {

    Solution sol;
    cout << sol.removeKDigits("1432219", 3) << endl; // Expected: "1219"
    cout << sol.removeKDigits("10200", 1) << endl;   // Expected: "200"
    cout << sol.removeKDigits("10", 2) << endl;      // Expected: "0"
    
    return 0;   
}