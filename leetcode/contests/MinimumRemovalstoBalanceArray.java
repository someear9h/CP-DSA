import java.util.Arrays;

public class MinimumRemovalstoBalanceArray {
    public static int minRemoval(int[] nums, int k) {
        int n = nums.length;
        if(n == 1) return 0; // already balanced
        Arrays.sort(nums);

        int l = 0, maxLen = (int)-1e9;

        for(int r = 0; r < n; r++) {
            // shrinking condition
            while(nums[r] > (long) nums[l] * k) {
                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);

        }
        return n - maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {1,6,2,9};
        int k = 3;

        System.out.println(minRemoval(nums, k));
    }
}
