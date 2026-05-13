/**
 * author: someear1h 
*/
/**
 * Class: Lazy Updates / Timestamping.Key Insight: When a "global" operation is too expensive to 
 * execute immediately, store it in a single variable and only apply it to individual elements 
 * "on-demand" (when they are accessed later).Signal: Problems with "Update Range/Global" and 
 * "Update Point" where $N$ and $Q$ are both large. 
*/
import java.io.*;
import java.util.*;

public class StoneAgeProblem {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            var st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            long[] a = new long[n];
            long sum = 0;
            for(int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
                sum += a[i];
            }

            int[] lastUpdated = new int[n];
            Arrays.fill(lastUpdated, -1);
            int lastAllTime = -1;
            long lastAllValue = 0;

            for(int currTime = 0; currTime < q; currTime++) {
                st = new StringTokenizer(br.readLine());
                int t = Integer.parseInt(st.nextToken());
                if(t == 1) {
                    int ind = Integer.parseInt(st.nextToken());
                    ind--; // 0 based indexing
                    long x = Long.parseLong(st.nextToken());
                    
                    if(lastUpdated[ind] < lastAllTime) {
                        a[ind] = lastAllValue;
                    }
                    
                    sum -= a[ind];
                    a[ind] = x;
                    sum += x;
                    lastUpdated[ind] = currTime;
                } else {
                    long x = Long.parseLong(st.nextToken());
                    
                    lastAllValue = x;
                    lastAllTime = currTime;
                    sum = x * (long)n;
                }

                out.println(sum);
            }
        }
    }       
}
