import java.util.*;

public class FrequencyoftheMostFrequentElement {
    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = 0;
        long res = 0, total = 0;

        while(right < nums.length) {
            total += nums[right];
            
            while((long)nums[right] * (right - left + 1) - total > k) {
                total -= nums[left];
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }

        return (int)res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4};
        int k = 5;

        System.out.println(maxFrequency(nums, k));
    }
}
