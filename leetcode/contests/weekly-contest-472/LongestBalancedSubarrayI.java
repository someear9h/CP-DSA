import java.io.*;
import java.util.*;

public class LongestBalancedSubarrayI {
    public static int longestBalanced(int[] nums) {
        int ans = 0;

        for(int i = 0; i < nums.length; i++) {
            Set<Integer> e = new HashSet<>();
            Set<Integer> o = new HashSet<>();

            for(int j = i; j < nums.length; j++) {
                if(nums[j] % 2 == 0) e.add(nums[j]);
                else o.add(nums[j]);

                if(e.size() == o.size()) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    } 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int n = Integer.parseInt(br.readLine());
            

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] nums = new int[n];
            for(int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            out.println(longestBalanced(nums));

        }
    }
}
