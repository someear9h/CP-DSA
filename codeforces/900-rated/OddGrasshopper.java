
/**
 * someear1h
*/

import java.util.*;
import java.io.*;

public class OddGrasshopper {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while (t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                long start = Long.parseLong(st.nextToken());
                long jumps = Long.parseLong(st.nextToken());
                long finalPos = 0;

                if (jumps % 4 == 1) {
                    finalPos = -jumps;
                } else if (jumps % 4 == 2) {
                    finalPos = 1;
                } else if (jumps % 4 == 3) {
                    finalPos = jumps + 1;
                } else {
                    finalPos = 0;
                }

                if (start % 2 == 0) {
                    finalPos = start + finalPos;
                } else {
                    finalPos = start - finalPos;
                }

                out.println(finalPos);
            }
        }
    }
}