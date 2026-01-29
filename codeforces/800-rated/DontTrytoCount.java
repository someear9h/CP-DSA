/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class DontTrytoCount {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());


                String x = br.readLine();
                String s = br.readLine();
                boolean f = false;
                for(int i = 0; i < 6; i++) {
                    if(x.contains(s)) {
                        out.println(i);
                        f = true;
                        break;
                    }

                    x+=x;
                }

                if(!f) out.println("-1");
            }
        }
    }
}