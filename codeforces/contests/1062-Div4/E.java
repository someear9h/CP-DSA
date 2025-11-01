import java.io.*;
import java.util.*;

public class E {
    static boolean check(long m, long[] arr, int n, long x, long k) {
        long spots = 0;

        // start gap
        spots += Math.max(0, (arr[0] - m) - 0 + 1);

        // middle gaps
        for(int i = 1; i < n; i++) {
            long start = arr[i - 1] + m, end = arr[i] - m;
            spots += Math.max(0, end - start + 1);
        }

        // end gap
        spots += Math.max(0, x - (arr[n - 1] + m) + 1);

        return spots >= k;
    }

    static String solve(int n, long k, long x, long[] arr) {

        Arrays.sort(arr);
        // binary search
        long l = 0, r = x + 1;
        while(l + 1 < r) {
            long mid = l + (r - l) / 2;

            if(check(mid, arr, n, x, k)) {
                l = mid;
            } else {
                r = mid;
            }
        }

        // now l is minimum time to portal
        StringBuilder sb = new StringBuilder();
        long j = 0;

        long start = 0, end = arr[0] - l;
        while(j <= end && k > 0) {
            sb.append(j).append(" ");
            j++;
            k--;
        }

        // middle
        for(int i = 1; i < n && k > 0; i++) {
            start = arr[i - 1] + l;
            end = arr[i] - l;

            j = Math.max(j, start); // Jump j forward to the start of the next safe zone.

            while(j <= end && k > 0) {
                sb.append(j).append(" ");
                j++;
                k--;
            }
        }

        if(k > 0) {
            start = arr[n - 1] + l;
            end = x;

            j = Math.max(j, start);

            while(j <= end && k > 0) {
                sb.append(j).append(" ");
                k--;
                j++;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int n = Integer.parseInt(st.nextToken());
                long k = Long.parseLong(st.nextToken());
                long x = Long.parseLong(st.nextToken());

                long[] arr = new long[n];
                st = new StringTokenizer(br.readLine());

                for(int i = 0; i < n; i++) {
                    arr[i] = Long.parseLong(st.nextToken());
                }

                out.println(solve(n, k, x, arr));
            }
        }
    }
}
