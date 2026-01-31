/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class WalkingMaster {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                var st =new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if(d < b) {
                    out.println("-1");
                    continue;
                }

                int diff = d-b;
                a += diff;

                if(a < c) {
                    out.println("-1");
                    continue;
                } 

                out.println(diff + (Math.abs(a-c)));
            } 
        }
    }    
}
