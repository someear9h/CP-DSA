import java.util.*;

public class FindMaximumValueinaConstrainedSequence {
    public static int findMaxVal(int n, int[][] restrictions, int[] diff) {
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for(int[] r : restrictions) {
            int idx = r[0];
            int maxVal = r[1];
            dp[idx] = maxVal;
        }

        // left to right
        for(int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[i], dp[i-1] + diff[i-1]);
        }

        for(int i = n-2; i>= 0; i--) {
            dp[i] = Math.min(dp[i], dp[i+1] + diff[i]);
        }

        int mx = (int)-1e9;
        for(int d : dp) {
            mx = Math.max(mx, d);
        }

        return mx;
    }

    public static void main(String[] args) {
        
        int[][] res = {{3, 1}, {8, 1}};
        int n = 10;
        int[] diff = {2,2,3,1,4,5,1,1,2};

        System.out.println(findMaxVal(n, res, diff));
    }
}
