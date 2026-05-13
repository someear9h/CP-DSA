package codeforces.contests.Codeforces_Round_1093_Div2;

/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class A_Blocked {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            var st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());

            while(t-- > 0) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(br.readLine());
                long[] a = new long[n];
                long largest = 0;
                Set<Long> set =new HashSet<>();
                List<Long> sorted = new ArrayList<>();
                for(int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                    largest = Math.max(largest, a[i]);
                    set.add(a[i]);
                    sorted.add(a[i]);
                }
                
                if(set.size() < n) {
                    out.println("-1");
                } else {
                    Collections.sort(sorted, Collections.reverseOrder());
                    for(long i : sorted) {
                        out.print(i + " ");
                    }
                    out.println();
                }

            }
        }
    }
}