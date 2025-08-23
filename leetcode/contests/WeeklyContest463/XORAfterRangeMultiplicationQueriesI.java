public class XORAfterRangeMultiplicationQueriesI {
    private static int xorAfterQueries(int[] nums, int[][] queries) {
        final int mod = (int)1e9 + 7;
        for(int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];

            while(l <= r) {
                nums[l] = (int)(((long)nums[l] * v) % mod);
                l += k;
            }
        }

        int ans = 0;
        for(int num : nums) {
            ans = ans ^ num;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int[][] queries = {{0,2,1,4}};

        System.out.println(xorAfterQueries(nums, queries));
    }
}
