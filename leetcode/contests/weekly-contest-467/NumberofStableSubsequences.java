import java.util.Arrays;

public class NumberofStableSubsequences {
    private final static  int MOD = (int)1e9 + 7;

    public static int countStableSubsequences(int[] nums) {
        int[][][] dp = new int[nums.length][3][4];
        for(int[][] mat : dp) {
            for(int[] m : mat) {
                Arrays.fill(m, -1);
            }
        }

        // -1 because we dont want to count empty subsequence
        return f(nums.length - 1, 0, 3, nums, dp) - 1;
    }

    private static int f(int idx, int cnt, int flag, int[] nums, int[][][] dp) {
        if(idx < 0) return 1;
        if(dp[idx][cnt][flag] != -1) return dp[idx][cnt][flag];

        int taken = 0, notTaken = 0;

        int parity = nums[idx] & 1; // parity = 1 if odd and 0 if even
            
        if(!(flag == parity && cnt == 2)) {
            // we can then pick the current element

            if(flag == 3) {
                // we havent picked anything we pick this element
                taken = f(idx - 1, 1, parity, nums, dp);
            } else if(parity == flag) {
                // same parity but not 3 length
                taken = f(idx - 1, cnt + 1, parity, nums, dp);
            } else {
                // we start a new subsequence of new parity
                taken = f(idx - 1, 1, parity, nums, dp);
            }
        }

        notTaken = f(idx - 1, cnt, flag, nums, dp);
    

        return dp[idx][cnt][flag] = (taken + notTaken) % MOD;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,4,2};
        System.out.println(countStableSubsequences(nums));
    }
}
