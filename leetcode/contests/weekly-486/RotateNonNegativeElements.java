public class RotateNonNegativeElements {
    public static int[] rotateElements(int[] nums, int k) {
        int n = nums.length;
        int m = 0;
        int[] pos = new int[n];

        for(int x : nums) {
            if(x >= 0) {
                pos[m++] = x;
            }
        }

        // m -> number of positive elements in nums
        if(m == 0) return nums;

        // get the effective k
        k = k % m;

        for(int i = 0, j = 0; i < n; i++) {
            if(nums[i]>= 0) {
                nums[i] = pos[(j++ +k) % m];
            }
        }

        return nums;
    }  
    
    public static void main(String[] args) {
        int[] nums = {1,-2,3,-4};
        int k = 3;

        int[] ans = rotateElements(nums, k);
        for(int a : ans) System.out.print(a + " ");
    }
}
