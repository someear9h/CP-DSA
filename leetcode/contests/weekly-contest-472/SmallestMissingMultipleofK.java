import java.io.*;
import java.util.*;

public class SmallestMissingMultipleofK {
    public static int missingMultiple(int[] nums, int k) {
        if(nums.length == 1 && k == nums[0]) return k * 2;
        
        int[] multiple = new int[101];
        for(int i = 0; i < 101; i++) {
            multiple[i] = k * (i + 1);
        }
        
        Map<Integer, Boolean> mp = new HashMap<>();
        Arrays.sort(nums);
        
        for(int num : nums) {
            mp.put(num, true);
        }

        for(int i = 0; i < 101; i++) {
            if(!mp.containsKey(multiple[i])) {
                return multiple[i];
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                
                StringTokenizer st = new StringTokenizer(br.readLine());
            
                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(br.readLine());

                int[] nums = new int[n];
                for(int i = 0; i < n; i++) {
                    nums[i] = Integer.parseInt(st.nextToken());
                }

                out.println(missingMultiple(nums, k));
            }
        }
    }
}
