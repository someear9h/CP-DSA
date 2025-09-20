import java.util.*;

public class SubsequenceSumAfterCappingElements {
    public static boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);
        boolean[] ans = new boolean[n];
        boolean[] dp = new boolean[k + 1];
        dp[0] = true; // we can always form sum 0

        int i = 0;
        for(int x = 1; x <= n; x++) {
            for(; i < n && nums[i] < x; i++) {
                for(int j = k; j >= nums[i]; j--) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }

            int capped = n - i;
            for(int j = 0; j <= capped; j++) {
                int times = j * x; // contribution of j capped elements 
                if(times > k) break;

                // dp[k - times] -> uncapped elements would make k - times sum
                if(dp[k - times]) {
                    ans[x - 1] = true;
                    break;
                }
            }
        }
        
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 4};
        int k = 5;

        boolean[] ans = subsequenceSumAfterCapping(nums, k);

        for(boolean a : ans) {
            System.out.print(a + " ");
        }
    }
}
