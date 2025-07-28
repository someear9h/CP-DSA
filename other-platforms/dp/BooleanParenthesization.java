import java.util.*;

public class BooleanParenthesization {
    static int countWays(String s) {
        // code here
        int n = s.length();
        long[][][] dp = new long[n][n][2];
        
        for(long[][] mat : dp) {
            for(long[] r : mat) {
                Arrays.fill(r, -1);
            }
        }
        
        return (int)f(s, 0, n - 1, 1, dp);
    }
    
    static long f(String s, int i, int j, int isTrue, long[][][] dp) {
        if (i > j) {
            return 0;
        }

        // Memoization Check: Must be the first thing after the invalid base case.
        if (dp[i][j][isTrue] != -1) {
            return dp[i][j][isTrue];
        }

        // Base Case 2: Single character. Must calculate AND return immediately.
        if (i == j) {
            if (isTrue == 1) {
                return dp[i][j][isTrue] = (s.charAt(i) == 'T') ? 1L : 0L;
            } else { // isTrue == 0
                return dp[i][j][isTrue] = (s.charAt(i) == 'F') ? 1L : 0L;
            }
        }

        long ways = 0;
        for (int k = i + 1; k < j; k += 2) {
            char op = s.charAt(k);

            long lt = f(s, i, k - 1, 1, dp);
            long lf = f(s, i, k - 1, 0, dp);
            long rt = f(s, k + 1, j, 1, dp);
            long rf = f(s, k + 1, j, 0, dp);

            if (op == '&') {
                if (isTrue == 1) { // T & T
                    ways += (lt * rt);
                } else { // T&F, F&T, F&F
                    ways += (lt * rf) + (lf * rt) + (lf * rf);
                }
            } else if (op == '|') {
                if (isTrue == 1) { // T|T, T|F, F|T
                    // This was the incorrect formula. Now fixed.
                    ways += (lt * rt) + (lt * rf) + (lf * rt);
                } else { // F|F
                    ways += (lf * rf);
                }
            } else if (op == '^') {
                if (isTrue == 1) { // T^F, F^T
                    ways += (lt * rf) + (lf * rt);
                } else { // T^T, F^F
                    ways += (lt * rt) + (lf * rf);
                }
            }
        }

        return dp[i][j][isTrue] = ways;
    }

    public static void main(String[] args) {
        String s = "T|T&F^T";

        System.out.println(countWays(s));
    }
}
