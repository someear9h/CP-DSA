public class LongestCommonSubstring {
    public static int longestCommonSubstr(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        
        int[][] dp = new int[n + 1][m + 1];
        
        for(int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }
        
        for(int j = 0; j < m + 1; j++) {
            dp[0][j] = 0;
        }
        
        int mx = 0;
        
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < m + 1; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    mx = Math.max(dp[i][j], mx);
                } else {
                    // only change from longest common subsequence if put 0 if the continuity breaks
                    dp[i][j] = 0;
                }
            }
        }
        
        return mx;
    }

    public static void main(String[] args) {
        String s1 = "ABCDGH";
        String s2 = "ACDGHR";

        System.out.println(longestCommonSubstr(s1, s2));
    }
}