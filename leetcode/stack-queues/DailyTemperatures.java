import java.io.*;
import java.util.*;

public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] nums = temperatures;
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];

        for(int i = n - 1; i >= 0; i--) {
            while(!st.isEmpty() && nums[st.peek()] <= nums[i]) {
                st.pop();
            }

            res[i] = st.isEmpty() ? 0 : st.peek() - i;

            st.push(i);
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int n = Integer.parseInt(br.readLine());
            int[] nums = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            int[] res = dailyTemperatures(nums);

            for(int i = 0; i < res.length; i++) {
                out.print(res[i] + " ");
            }

            out.println();
        }
    }
}
