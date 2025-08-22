public class SplitArrayLargestSum {
    private static int countSubarrays(int[] nums, int maxSum) {
        int currSum = 0;
        int subarrays = 1; // always 1 subarray will be there

        for(int i = 0; i < nums.length; i++) {
            if(currSum + nums[i] <= maxSum) {
                currSum += nums[i];
            } else {
                subarrays++;
                currSum = nums[i];
            }
        }

        return subarrays;
    }

    private static int splitArray(int[] nums, int k) {
        int low = 0, high = 0;
        for(int num : nums) {
            high += num;
            low = Math.max(low, num);
        }

        while(low <= high) {
            int mid = low + (high - low) / 2;
            
            int subarrays = countSubarrays(nums, mid);

            if(subarrays > k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] nums = {7,2,5,10,8};
        int k = 2;

        System.out.println(splitArray(nums, k));
    }
}
