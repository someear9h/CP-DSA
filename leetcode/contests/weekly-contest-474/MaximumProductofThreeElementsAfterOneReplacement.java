public class MaximumProductofThreeElementsAfterOneReplacement {
    public static long maxProduct(int[] nums) {
        long max1 = -1, max2 = -1;
        for(int num : nums) {
            int val = Math.abs(num);

            if(val > max1) {
                max2 = max1;
                max1 = val;
            } else if(val > max2) {
                max2 = val;
            }
        }

        return max1 * max2 * (long)1e5;
    }

    public static void main(String[] args) {
        int[] nums = {-5, 7, 0};
        System.out.println(maxProduct(nums));
    }
}
