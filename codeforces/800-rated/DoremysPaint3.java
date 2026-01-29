/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class DoremysPaint3 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());

                int[] a = new int[n];

                Map<Integer, Integer> mp = new HashMap<>();
                var st = new StringTokenizer(br.readLine());
                for(int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken());
                    mp.put(a[i], mp.getOrDefault(a[i], 0) + 1);
                }

                if(mp.size() > 2) {
                    out.println("no");
                } else {
                    if(mp.size() == 1) {
                        out.println("YES");
                    } else {

                        List<Integer> cnts = new ArrayList<>(mp.values());
                        int f1 = cnts.get(0);
                        int f2 = cnts.get(1);
    
                        if(f1 == f2) {
                            out.println("YES");
                        } else if(n % 2 != 0 && Math.abs(f1-f2) <= 1) {
                            out.println("yes");
                        } else {
                            out.println("NO");
                        }
                    }
                }
            }
        }
    }
}