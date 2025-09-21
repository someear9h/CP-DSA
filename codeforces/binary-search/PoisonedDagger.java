import java.io.*;
import java.util.*;

public class PoisonedDagger {
    static void solve(BufferedReader br, PrintWriter out) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long h = Long.parseLong(st.nextToken());

        long[] a = new long[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        long low = 1, high = h, ans = h; // initialise ans with max possible value

        while(low <= high) {
            long mid = low + (high - low) / 2;

            if(canKill(mid, n, h, a)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        out.println(ans);
    }

    static boolean canKill(long k, int n, long h, long[] a) {
        long damage = 0;

        for(int i = 0; i < n - 1; i++) {
            long duration = Math.min(k, a[i + 1] - a[i]);
            damage += duration;
        }

        damage += k; // last attack will deal k damage 

        return damage >= h;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            solve(br, out);
        }

        out.flush();
    }
}
