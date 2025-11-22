import java.io.*;
import java.util.*;

public class LongestPalindromicSubsequenceAfteratMostKOperations {
    public static int longestPalindromicSubsequence(String s, int k) {
        int n = s.length();
        // dp[i][j][k] = palindromic length from i to j using k ops
        int[][][] dp = new int[n][n][k + 1];

        // base case: if n == 1 then its palindrome of length 1
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= k; j++) {
                dp[i][i][j] = 1;
            }
        }

        // base case: if n == 2 
        for(int i = 0; i < n-1; i++) {
            int ops = calOps(s.charAt(i), s.charAt(i + 1));
                
            for(int j = 0; j <= k; j++) {
                    
                if(j >= ops) {
                    dp[i][i+1][j] = 2;
                } else {
                    // we dont have enough ops -> we have max 1 length
                    dp[i][i+1][j] = 1;
                }
            }
        }

        for(int len = 3; len <= n; len++) {
            for(int i = 0; i <= n - len; i++) {
                int st = i, end = i + len - 1;
                int ops = calOps(s.charAt(st), s.charAt(end));

                for(int j = 0; j <= k; j++) {
                    if(j >= ops) {
                        dp[st][end][j] = Math.max(dp[st +1][end-1][j], 
                                        2 + dp[st + 1][end - 1][j - ops]);
                    }
                    int moveStart = dp[st + 1][end][j];
                    int moveEnd = dp[st][end - 1][j];

                    dp[st][end][j] = Math.max(dp[st][end][j], 
                                    Math.max(moveStart, moveEnd));
                    
                }
            }
        }

        return dp[0][n-1][k];
    }

    private static int calOps(char x, char y) {
        return Math.min(Math.abs(x - y), 26 - Math.abs(x -y));
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t--> 0) {
                var st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                int k = Integer.parseInt(st.nextToken());

                out.println(longestPalindromicSubsequence(s, k));
            }
        }
    }
}
