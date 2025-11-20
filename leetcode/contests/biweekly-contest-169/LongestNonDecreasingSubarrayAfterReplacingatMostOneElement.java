import java.io.*;
import java.util.*;

public class LongestNonDecreasingSubarrayAfterReplacingatMostOneElement {
    public static int longestSubarray(int[] nums) {
        // pre compute the left and right arrays
        // left[i] -> store the longest non decreasing subarray ending at i
        // right[i] -> store longest non decreasing subarray starting at i

        int n = nums.length;
        if(n == 1) return 1;
        int[] left = new int[n], right = new int[n];

        left[0] = 1;
        for(int i = 1; i < n; i++) {
            if(nums[i - 1] <= nums[i]) left[i] = left[i - 1] + 1;
            else left[i] = 1;
        }

        right[n - 1] = 1;
        for(int i = n - 2; i >= 0; i--) {
            if(nums[i + 1] >= nums[i]) right[i] = right[i + 1] + 1;
            else right[i] = 1;
        }

        // now there are 4 cases
        // case1: if i == 0 -> use right[i] + 1 as there is no left
        // case2: if i == n-1 -> use left[i] + 1 as there is no right
        // case3: if nums[i - 1] <= nums[i + 1] means we can replace nums[i]
        // so get left[i] and right[i] + 1
        // case4: else just get max(left[i], right[i]) + 1
        
        int res = 1;
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                res = Math.max(res, right[i + 1] + 1);
            } else if(i == n- 1) {
                res = Math.max(res, left[i - 1] + 1);
            } else if(nums[i - 1] <= nums[i + 1]) {
                res = Math.max(res, left[i - 1] + right[i + 1] + 1);
            } else {
                res = Math.max(res, Math.max(left[i - 1], right[i + 1]) + 1);
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                var st = new StringTokenizer(br.readLine());
                
                int[] arr = new int[n];
                for(int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }

                out.println(longestSubarray(arr));
            }
        }
    }
}