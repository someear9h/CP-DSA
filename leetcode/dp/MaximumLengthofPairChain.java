import java.util.Arrays;
import java.util.Comparator;

public class MaximumLengthofPairChain {
    public Comparator<int[]> comp = (a, b) -> a[0] - b[0];

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, comp);
        int n = pairs.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for(int i = 1; i < n; i++) {
            for(int pi = 0; pi < i; pi++) {
                if(pairs[i][0] > pairs[pi][1] && dp[i] < dp[pi] + 1) {
                    dp[i] = dp[pi] + 1;
                }
            }
        }

        int mx = 0;
        for(int i : dp) {
            mx = Math.max(i, mx);
        }

        return mx;
    }
}
