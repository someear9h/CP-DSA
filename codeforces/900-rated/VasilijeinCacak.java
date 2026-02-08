/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class VasilijeinCacak {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                long n = Long.parseLong(st.nextToken());
                long k = Long.parseLong(st.nextToken());
                long x = Long.parseLong(st.nextToken());

                long minSum = (k * (k + 1)) / 2;
            
                // Calculate Max Sum using the total sum - prefix sum logic
                long totalSum = (n * (n + 1)) / 2;
                long remainingCount = n - k;
                long prefixSum = (remainingCount * (remainingCount + 1)) / 2;
                
                long maxSum = totalSum - prefixSum;

                if(x >= minSum && x <= maxSum) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            }
        }
    }
}