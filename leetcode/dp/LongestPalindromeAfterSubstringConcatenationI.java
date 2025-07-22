class Solution {
    public int longestPalindrome(String s, String t) {
        String revT = new StringBuilder(t).reverse().toString();
        int n = s.length(), m = revT.length();
        int[][] dp = new int[n+1][m+1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == revT.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    int len = dp[i][j];

                    // get substring from s and t
                    String subS = s.substring(i - len, i);
                    String subT = t.substring(m - j, m - j + len);

                    // check if subS + subT is a palindrome
                    String combined = subS + subT;
                    if (isPalindrome(combined)) {
                        maxLen = Math.max(maxLen, combined.length());
                    }
                }
            }
        }

        return maxLen;
    }

    private boolean isPalindrome(String str) {
        int l = 0, r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l++) != str.charAt(r--)) return false;
        }
        return true;
    }
}
