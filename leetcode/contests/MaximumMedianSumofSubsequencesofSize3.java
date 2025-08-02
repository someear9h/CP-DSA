import java.util.Arrays;

public class MaximumMedianSumofSubsequencesofSize3 {
    private static long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        long res = 0;

        int d = n / 3;

        for(int i = d; i < n; i += 2) {
            res += (long)nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,3,2,1,3};

        System.out.println(maximumMedianSum(nums));
    }
}
