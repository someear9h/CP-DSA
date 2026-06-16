// author: someear1h

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        
        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            
            long minOps = Long.MAX_VALUE;
            long div = 0;
            long ops = 0;

            while(a != b) {
                long diff = Math.abs(b-a);
                ops = diff + div;
                minOps = Math.min(minOps, ops);

                if(a > b) {
                    a /= x;
                } else {
                    b /= x;
                }

                div++;
            }
            
            minOps = Math.min(minOps, div);
            out.println(minOps);
        }


        out.close();
    }
}