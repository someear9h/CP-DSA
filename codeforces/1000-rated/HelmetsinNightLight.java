/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class HelmetsinNightLight {

    static class Pair {
        int first;
        int second;

        Pair(int f, int s) {
            first = f;
            second = s;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());

                int n = Integer.parseInt(st.nextToken());
                long p = Long.parseLong(st.nextToken());

                st = new StringTokenizer(br.readLine());
                long[] a = new long[n];
                for(int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                }

                st = new StringTokenizer(br.readLine());
                long[] b = new long[n];
                for(int i = 0; i < n; i++) {
                    b[i] = Long.parseLong(st.nextToken());
                }

                List<Pair> lis = new ArrayList<>();
                for(int i = 0; i < n; i++) {
                    lis.add(new Pair((int)b[i], (int)a[i]));
                }

                Collections.sort(lis, (x, y) -> x.first - y.first);

                int cnt = 1;
                long minCost = p;
                for(Pair pr : lis) {
                    if(n <= cnt) break;
                    
                    int cost = pr.first;
                    int cap = pr.second;

                    if(cost >= p) {
                        break;
                    }

                    long toTell = Math.min(cap, n-cnt);

                    minCost += toTell * cost;
                    cnt += toTell;
                }

                if(n > cnt) {
                    minCost += p * (n-cnt);
                }

                out.println(minCost);
            }
        }
    }
}