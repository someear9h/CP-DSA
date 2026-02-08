/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class OlyaandGamewithArrays {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                
                List<Long> secondMin = new ArrayList<>();
                long mn = Long.MAX_VALUE;
                for(int i = 0; i < n; i++) {
                    int m = Integer.parseInt(br.readLine());

                    var st = new StringTokenizer(br.readLine());
                    long[] a = new long[m];
                    for(int j =0; j < m; j++) {
                        a[j] = Long.parseLong(st.nextToken());
                    }

                    Arrays.sort(a);

                    secondMin.add(a[1]);
                    mn = Math.min(mn, a[0]);
                }
                
                // total score = sum of second mins - lowest second min + global minimum
                long sumSec = 0;
                for(long num : secondMin) {
                    sumSec += num;
                }
                Collections.sort(secondMin);
                long lowestSecMin = secondMin.get(0);

                long score = (sumSec - lowestSecMin) + mn;
                out.println(score);
            }
        }
    }
}