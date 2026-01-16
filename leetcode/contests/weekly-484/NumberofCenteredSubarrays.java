import java.util.*;

public class NumberofCenteredSubarrays {
    public static int centeredSubarrays(int[] nums) {
        int n = nums.length;
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            Set<Integer> s = new HashSet<>();
            int sum = 0;

            for(int j = i; j < n; j++) {
                sum += nums[j];
                s.add(nums[j]);
                if(s.contains(sum)) cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = {-1,1,0};
        System.out.println(centeredSubarrays(nums));
    }
}
