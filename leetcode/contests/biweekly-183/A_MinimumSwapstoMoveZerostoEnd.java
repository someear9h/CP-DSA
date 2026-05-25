public class A_MinimumSwapstoMoveZerostoEnd {
    public static int minimumSwaps(int[] nums) {
        int zeroCnt = 0;
        for(int num : nums) {
            if(num == 0) zeroCnt++;
        }

        int ans = 0;

        int n = nums.length;
        int start = n-zeroCnt;

        for(int i = start; i < n; i++) {
            if(zeroCnt > 0 && nums[i] != 0) {
                ans++;
                zeroCnt--;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {0,1,0,3,12};
        int[] nums2 = {0,1,0,2};

        System.out.println(minimumSwaps(nums1));
        System.out.println(minimumSwaps(nums2));
    }
}
