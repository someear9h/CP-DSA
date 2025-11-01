import java.io.*;
import java.util.*;

public class C {

    static int[] solve(int[] nums, int n) {
        boolean flag = true;
        for(int i = 1; i < n; i++) {
            if(nums[i - 1] % 2 != nums[i] % 2) {
                flag = false;
            }
        }

        if(flag) {
            return nums;
        }

        Arrays.sort(nums);
        return nums;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-->0) {
                int n = Integer.parseInt(br.readLine());

                StringTokenizer st = new StringTokenizer(br.readLine());
                int[] nums = new int[n];
                for(int i = 0; i < n; i++) {
                    nums[i] = Integer.parseInt(st.nextToken());
                }

                int[] res = solve(nums, n);
                
                for(int num : res) {
                    out.print(num + " ");
                }

                out.println();
            }
        }
    }
}
