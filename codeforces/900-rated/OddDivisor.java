
/**
someear1h 
*/

import java.io.*;

public class OddDivisor {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while (t-- > 0) {
                long n = Long.parseLong(br.readLine());

                if ((n & (n - 1)) == 0) {
                    out.println("NO");
                }

                else {
                    out.println("YES");
                }
            }
        }
    }
}