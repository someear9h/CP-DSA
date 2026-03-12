
/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class BadBoy {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while (t-- > 0) {
                var st = new StringTokenizer(br.readLine());

                long n = Long.parseLong(st.nextToken());
                long m = Long.parseLong(st.nextToken());
                Long.parseLong(st.nextToken());
                Long.parseLong(st.nextToken());

                out.println(1 + " " + 1 + " " + n + " " + m);
            }
        }
    }
}