import java.util.*;

public class MinimumSubarrayLengthWithDistinctSumAtLeastK {
    static class Solution {
        public int minLength(int[] nums, int k) {
            Map<Integer, Integer> mp = new HashMap<>();
            int l = 0, mn = Integer.MAX_VALUE;

            long currSum = 0;

            for(int r = 0; r < nums.length; r++) {
                mp.put(nums[r], mp.getOrDefault(nums[r], 0) + 1);

                if(mp.get(nums[r]) == 1){
                    currSum += nums[r];
                }

                while(currSum >= k) {
                    mn = Math.min(mn, r -l + 1);

                    mp.put(nums[l], mp.get(nums[l]) - 1);

                    if(mp.get(nums[l]) == 0) {
                        currSum = currSum - nums[l];
                        mp.remove(nums[l]);
                    }

                    l++;
                }
            }

            return mn == Integer.MAX_VALUE ? -1 : mn;
        }
    }    

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2, 2, 3, 1};
        int k = 4;

        System.out.println(sol.minLength(nums, k));
    }
}
