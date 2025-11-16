import java.io.*;
import java.util.*;

public class MinimumMovestoEqualArrayElementsIII {
    public static int minMoves(int[] nums) {
        int maxEle = -1;
        for(int i = 0; i < nums.length; i++) {
            maxEle = Math.max(maxEle, nums[i]);
        }
        int cnt = 0;
        for(int i = 0; i < nums.length; i++) {
            cnt += (maxEle - nums[i]);
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {

            var n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int[] nums = new int[n];
            for(int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
    
            out.println(minMoves(nums));
        }

    }
}
