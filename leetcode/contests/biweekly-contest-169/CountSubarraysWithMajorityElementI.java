import java.io.*;
import java.util.*;

public class CountSubarraysWithMajorityElementI {
    public static int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        // we treat target as 1 and no target number as -1 
        // and store it in v
        int[] v = new int[n];

        for(int i = 0; i < n; i++) {
            if(nums[i] == target) v[i] = 1;
            else v[i] = -1;
        }

        // now we get the prefix sum of the 'v' array to get the sum of subarray
        // when sum of subarray is > 0 
        // then we have more target number than non target
        int[] ps = new int[n];
        ps[0] = v[0];
        for(int i = 1; i < n; i++) {
            ps[i] = ps[i - 1] + v[i];
        }
        
        int cnt = 0;

        // now maintain a subarray with left idx and right idx l and r
        // for that subarray if the sum > 0 then we add the cnt
        for(int l = 0; l < n; l++) {
            for(int r = l; r < n; r++) {
                // we add v[l] because ps[r] - ps[l]
                // gives sum from l + 1 to r so we consider v[l];
                int sum = ps[r] - ps[l] + v[l];

                if(sum > 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {

            var n = Integer.parseInt(br.readLine());
            int target = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int[] nums = new int[n];
            for(int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
    
            out.println(countMajoritySubarrays(nums, target));
        }

    }
}