public class LongestSubsequenceWithNonZeroBitwiseXOR {
    public static int longestSubsequence(int[] nums) {
        int xorSum = 0;
        boolean allZero = true;
        
        for(int num : nums) {
            xorSum = xorSum ^ num;
            if(num != 0) allZero = false;
        }

        if(allZero) return 0;
        return xorSum == 0 ? nums.length - 1 : nums.length;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};

        System.out.println(longestSubsequence(nums));
    }
}
