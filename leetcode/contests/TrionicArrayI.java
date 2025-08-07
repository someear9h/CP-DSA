public class TrionicArrayI {
    private static boolean isTrionic(int[] nums) {
        int n = nums.length;
        
        // find p
        int p = 0;
        while(p < n - 1 && nums[p] < nums[p + 1]) p++;

        if(p == n - 1 || p == 0) return false;

        // find q ahead of p
        int q = p;
        while(q < n - 1 && nums[q] > nums[q + 1]) q++;

        if(q == 0 || q == n - 1) return false;

        // loop for the remaining part
        for(int i = q; i < n - 1; i++) {
            if(nums[i] >= nums[i + 1]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,4,2,6};

        System.out.println((isTrionic(nums)) ? "True" : "False");
    }
}