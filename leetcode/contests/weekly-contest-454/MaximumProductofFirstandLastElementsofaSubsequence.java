public class MaximumProductofFirstandLastElementsofaSubsequence {
    public static long maximumProduct(int[] nums, int m) {
        long ans =  1L * nums[0] * nums[m - 1];;
        long minFirstElement = nums[0];
        long maxFirstElement = nums[0];

        for(int i = m - 1; i < nums.length; i++) {
            // first element's index in window of m size: i - m + 1
            minFirstElement = Math.min(minFirstElement, nums[i - m + 1]);
            maxFirstElement = Math.max(maxFirstElement, nums[i - m + 1]);

            ans = Math.max(ans, Math.max(minFirstElement * nums[i], maxFirstElement * nums[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -5, 5, 6, -4};
        int m = 3;

        System.out.println(maximumProduct(nums, m));
    }
}
