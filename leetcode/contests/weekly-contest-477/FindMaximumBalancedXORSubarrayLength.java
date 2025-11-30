import java.util.*;

public class FindMaximumBalancedXORSubarrayLength {
    public static int maxBalancedSubarray(int[] nums) {
        int n = nums.length;
        // for nums[i...j] if xor[j] == xorsum[i-1] then xor = 0
        // for nums[i...j] if diff of even and odd numbers 
        // is 0 then we added same even and odd numbers so balanced
        Map<String, Integer> mp = new HashMap<>();
        mp.put("0_0", -1);
        int xor = 0;
        int diff = 0;
        int even = 0, odd = 0;
        int len = 0;

        for(int i = 0; i < n; i++) {
            xor = xor ^ nums[i];

            if(nums[i] % 2 == 0) even++;
            else odd++;

            diff = even - odd;

            String key = diff + "_" + xor;

            // if map has already the same key then we have diff = 0 and xor = 0
            if(mp.containsKey(key)) {
                int prevIdx = mp.get(key);
                len = Math.max(len, i - prevIdx);
            } else {
                mp.put(key, i);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = {3,1,3,2,0};
        System.out.println(maxBalancedSubarray(nums));
    }
}
