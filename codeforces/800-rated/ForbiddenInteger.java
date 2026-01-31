/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class ForbiddenInteger {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            String line = br.readLine();
            if (line == null) return;
            int t = Integer.parseInt(line);
            
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                if (x != 1) {
                    out.println("YES");
                    out.println(n);
                    for (int i = 0; i < n; i++) {
                        out.print("1 ");
                    }
                    out.println();
                } 
                else {
                    if (k == 1) {
                        out.println("NO");
                    }

                    else if (n % 2 == 0) {
                        out.println("YES");
                        out.println(n / 2); 
                        for (int i = 0; i < n / 2; i++) {
                            out.print("2 ");
                        }
                        out.println();
                    }

                    else {
                        if (k >= 3) {
                            out.println("YES");

                            int numTwos = (n - 3) / 2;
                            out.println(1 + numTwos); 
                            out.print("3 ");
                            for (int i = 0; i < numTwos; i++) {
                                out.print("2 ");
                            }
                            out.println();
                        } else {
                            out.println("NO");
                        }
                    }
                }
            }
        }
    }
}