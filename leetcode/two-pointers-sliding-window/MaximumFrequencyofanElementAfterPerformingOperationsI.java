import java.util.*;

public class MaximumFrequencyofanElementAfterPerformingOperationsI {
    public static int maxFrequency(int[] nums, int k, int numOperations) {
        // map to store the frequency of elements in nums
        Map<Integer, Integer> mp = new HashMap<>();

        for(int num : nums) {
            mp.put(num, mp.getOrDefault(num, 0) + 1);
        }

        // sort the array to look for potential frequency of a value
        Arrays.sort(nums);

        int left = 0, right = 0, n = nums.length, ans = 0;

        for(int mid = 0; mid < n; mid++) {
            // shrink the window if the smallest element and mid element in window
            // exceeds -k, +k
            while(nums[mid] - nums[left] > k) {
                left++;
            }

            // do the same for right
            while(right < n && nums[right] - nums[mid] <= k) {
                right++;
            }

            int total = right - left; // total elements in the window who can reach to mid 
            ans = Math.max(ans, Math.min(total - mp.get(nums[mid]), numOperations) + mp.get(nums[mid]));
        }

        // when we consider of making existing elements to non existent element in nums
        left = 0;
        for(right = 0; right < n; right++) {
            int mid = (nums[left] + nums[right]) / 2;
            
            while(mid - nums[left] > k || nums[right] - mid > k) {
                left++;
                // recalculate the mid for changed left and right pointers
                mid = (nums[left] + nums[right]) / 2;
            }

            ans = Math.max(ans, Math.min(right - left + 1, numOperations));
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 5};
        int k = 1, numOps = 2;

        System.out.println(maxFrequency(nums, k, numOps));
    }
}
