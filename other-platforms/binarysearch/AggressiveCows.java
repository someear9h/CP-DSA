// https://takeuforward.org/plus/dsa/problems/aggressive-cows

import java.util.Arrays;

public class AggressiveCows {
    public static boolean canWePlace(int[] nums, int dist, int k) {
        int cowcnt = 1;
        int lastStall = nums[0];

        for(int i = 1; i < nums.length; i++) {
            
            if(nums[i] - lastStall >= dist) {
                cowcnt++;
                lastStall = nums[i];
            }

            if(cowcnt >= k) {
                return true;
            }
        }
        return false;
    }

    public static int aggressiveCows(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 1, right = nums[nums.length - 1] - nums[0];
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(canWePlace(nums, mid, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        int[] stalls = {0, 3, 4, 7, 10, 9};
        int k = 4;
        
        System.out.println(aggressiveCows(stalls, k));
    }
}