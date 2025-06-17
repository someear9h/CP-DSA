public class CountSpecialTriplets {
    public static int specialTriplets(int[] nums) {
        int n = nums.length;
        int max = 0; 
        int MOD = 1_000_000_007;

        for(int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
        }

        int cap = max * 2 + 1;
        int[] leftcnt = new int[cap];
        int[] rightcnt = new int[cap];
        long ans = 0;

        for(int x : nums) rightcnt[x]++;

        for(int j = 0; j < n; j++) {
            int curr = nums[j];
            rightcnt[curr]--;
            ans = (ans + 1L * leftcnt[2 * curr] * rightcnt[2 * curr]) % MOD;
            leftcnt[curr]++;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        int[] nums = {8,4,2,8,4};
        System.out.println(specialTriplets(nums));
    }
}