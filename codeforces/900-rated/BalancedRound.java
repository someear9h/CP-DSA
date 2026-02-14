/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class BalancedRound {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                long n= Long.parseLong(st.nextToken());
                long k =Long.parseLong(st.nextToken());

                st = new StringTokenizer(br.readLine());
                long[] a = new long[(int)n];

                for(int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                }
                
                Arrays.sort(a);
                long mxLen =-1;
                long len = 1;
                for(int i = 1; i < n; i++) {
                    if(Math.abs(a[i-1] - a[i]) <= k) {
                        len++;
                    } else {
                        len = 1;
                    }

                    mxLen = Math.max(mxLen, len);
                }
                out.println(mxLen == -1? "0" : n-mxLen);
            }   
        }
    }
}