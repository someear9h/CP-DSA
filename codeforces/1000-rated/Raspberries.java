/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class Raspberries {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {

            int t = Integer.parseInt(br.readLine());
            
            while(t-->0) {

                var st = new StringTokenizer(br.readLine());
                
                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                
                st = new StringTokenizer(br.readLine());
                
                int ans = k;
                int evenCnt = 0;
                for(int i = 0; i < n; i++) {
                    int val = Integer.parseInt(st.nextToken());
                    
                    if(val % k == 0) ans = 0;
                    else ans = Math.min(ans, k - (val % k));
                    
                    if(val % 2 == 0) evenCnt++;
                }
                
                if(k == 4) {
                    int ops = Math.max(0, 2 - evenCnt);
                    ans = Math.min(ans, ops);
                }
                
                out.println(ans);
            }
        }
    }    
}
