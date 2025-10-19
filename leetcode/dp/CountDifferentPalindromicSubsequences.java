public class CountDifferentPalindromicSubsequences {
    public static int countPalindromicSubsequences(String s) {
        int MOD = (int)1e9 + 7;
        int n = s.length();

        int[][] dp = new int[n][n];
        // dp[i][j] -> gives distinct no of palindromes from substring s[i..j];

        // base case: if i == j (substring length = 1) 
        // then single character from String s which is palindrome
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // len -> substring length, we handled len = 1 above
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i <= n - len; i++) {
                int j = len + i - 1; // window length = j - i + 1 -> sliding window
            
                if(s.charAt(i) != s.charAt(j)) {
                    // if chars are not same then go inside
                    // also subtract the overlapping substrings
                    dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]) % MOD;
                } else {
                    // internal pointers l and r inside i and j
                    int l = i + 1, r = j - 1;
                    while(l <= r && s.charAt(i) != s.charAt(l)) l++;
                    while(l <= r && s.charAt(r) != s.charAt(j)) r--;

                    // if s.charAt(i) == s.charAt(j) there are 3 cases to consider
                    // case 1: if chars at i and j say 'ch' is same but there is no ch in substring s[i..j]
                    if(l > r) {
                        // then we add + 2 for 'ch' at i and j
                        dp[i][j] = (2 * dp[i + 1][j - 1] + 2) % MOD; 
                    } 

                    // case 2: if there is one same char in the substring s[i..j] 
                    // then add 1 as i and j have been counted
                    else if(l == r) {
                        dp[i][j] = (2 * dp[i + 1][j - 1] + 1) % MOD;
                    } 
                    
                    // case 3: if there are 2 or more chars same as s[i] & s[j]
                    // then we have to subtract s[l...r] because i and j already calculated it
                    else {
                        dp[i][j] = (2 * dp[i + 1][j - 1] - dp[l + 1][r - 1]) % MOD;
                    }

                }
                
                if(dp[i][j] < 0) dp[i][j] += MOD;
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba";
        System.out.println(countPalindromicSubsequences(s));
    }
}
