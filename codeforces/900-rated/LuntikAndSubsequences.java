import java.io.*;
import java.util.*;

public class LuntikAndSubsequences {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    try (var out = new PrintWriter(System.out)) {
      int t = Integer.parseInt(br.readLine());

      while (t-- > 0) {
        int n = Integer.parseInt(br.readLine());

        long[] a = new long[n];
        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
          a[i] = Long.parseLong(st.nextToken());
        }

        out.println(solve(a, n));

      }
    }
  }

  static long solve(long[] nums, int n) {
    int c1 = 0, c0 = 0;
    for (int i = 0; i < n; i++) {
      if (nums[i] == 1)
        c1++;
      if (nums[i] == 0)
        c0++;
    }

    return c1 * (1L << c0);
  }

}