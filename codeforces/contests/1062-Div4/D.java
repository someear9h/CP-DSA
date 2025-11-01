import java.io.*;
import java.util.*;

public class D {

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static long solve(long[] nums, int n) {
        long g = nums[0];
        for (int i = 1; i < n; i++) {
            g = gcd(g, nums[i]);
        }

        // Find smallest x >= 2 that is coprime with g
        for (long x = 2; x <= 100; x++) {
            if (gcd(x, g) == 1) return x;
        }

        return -1; // theoretically never happens
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Long.parseLong(st.nextToken());
            }
            out.println(solve(nums, n));
        }
        out.flush();
    }
}