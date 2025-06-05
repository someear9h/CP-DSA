#include <iostream>
#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    int numDistinct(string s, string t) {
        int n1=s.size();
        int n2=t.size();
        vector<vector<double>> dp(n1+1,vector<double>(n2+1,0));
        
        // Initialize the first row: empty string s2 can be matched with any non-empty s1 in one way
        for(int i=0;i<n1+1;i++)  dp[i][0]=1;

        // dp[0][j] for j > 0 remains 0 by default because empty s can't form non-empty t

        for(int i=1;i<n1+1;i++){
            for(int j=1;j<n2+1;j++){
                if(s[i-1]==t[j-1]){
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return int(dp[n1][n2]);
    }
};

int main() {
    Solution sol;
    string s = "babgbag";
    string t = "bag";
    cout << sol.numDistinct(s, t) << endl;
}