import java.io.*;
import java.util.*;

public class MakeitDivisibleby25 {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    try (var out = new PrintWriter(System.out)) {
      int t = Integer.parseInt(br.readLine());

      while (t-- > 0) {
        long n = Long.parseLong(br.readLine());

        String s = String.valueOf(n);

        List<String> poss = List.of("25", "50", "75", "00");
        int mini = Integer.MAX_VALUE;
        for (String p : poss) {
          mini = Math.min(mini, solve(s, p));
        }

        out.println(mini);
      }
    }
  }

  static int solve(String n, String possibleValue) {
    int idx = possibleValue.length() - 1;
    int ops = 0;
    for (int i = n.length() - 1; i >= 0; i--) {
      if (n.charAt(i) == possibleValue.charAt(idx)) {
        idx--;

        if (idx < 0) {
          break;
        }
      } else {
        ops++;
      }
    }

    return ops;
  }
}