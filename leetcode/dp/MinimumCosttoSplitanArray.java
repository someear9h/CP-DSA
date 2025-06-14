import java.util.HashMap;
import java.util.Map;

public class MinimumCosttoSplitanArray {
    public static int minCost(int[] a, int k) {
        int n = a.length;

        // value to index map
        Map<Integer, Integer> M = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int j = M.getOrDefault(a[i], -1);
            if (j == -1) {
                M.put(a[i], idx);
                a[i] = idx++;
            } else a[i] = j;
        }

        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            int[] m = new int[idx];
            int score = k;
            for (int j = i-1; j >= 0; j--) {
                if (++m[a[j]] == 2) score += 2;
                else if (m[a[j]] > 2) score++;
                dp[i] = Math.min(dp[i], dp[j] + score);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,1,2,1,3,3};
        int k = 2;

        System.out.println(minCost(arr, k));
    }
}
