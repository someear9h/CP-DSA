/**
 * 
 * 1. Minimum Time to Type Word Using Special Typewriter (LeetCode 1974)
 * 2. Minimum Changes To Make Alternating Binary String (LeetCode 1758)
 * 3. Minimum Operations to Make the Array Alternating (LeetCode 2170)
*/


public class B_MinimumOperationstoMakeArrayModuloAlternatingI {
    public static int minOperations(int[] nums, int k) {
        int n = nums.length;
        int minOps = 1000000004;

        // check every possible combination of x and y
        for(int x = 0; x < k; x++) {
            for(int y = 0; y < k; y++) {
                // x and y values cant be same
                if(x == y) {
                    continue;
                }

                // go through the elements or array
                // round each element with k and find distance
                // from x for even indices and from y for odd indices
                // get minimum distance (operations) for whole array
                // and then after the whole array iteration
                // for every x and y state store the global minimum
                // and this is the answer
                int ops = 0;
                for(int i = 0; i < n; i++) {
                    if(i % 2 == 0) {
                        ops += Math.min((nums[i] % k - x + k) % k, 
                        (x - nums[i] % k + k) % k);
                    } else {
                        ops += Math.min((nums[i] % k - y + k) % k, 
                        (y - nums[i] % k + k) % k);
                    }
                }

                minOps = Math.min(ops, minOps);
            }
        }

        return minOps;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,4,2,8};
        int k = 3;

        System.out.println(minOperations(nums, k));
    }
}
