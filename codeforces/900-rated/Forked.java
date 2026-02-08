/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class Forked {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());

                long a = Long.parseLong(st.nextToken());
                long b = Long.parseLong(st.nextToken());

                st = new StringTokenizer(br.readLine());
                long k1 = Long.parseLong(st.nextToken());
                long k2 = Long.parseLong(st.nextToken());


                st = new StringTokenizer(br.readLine());
                long q1 = Long.parseLong(st.nextToken());
                long q2 = Long.parseLong(st.nextToken());

                Set<String> kingAttack = makeMoves(k1, k2, a, b);
                Set<String> queenAttack = makeMoves(q1, q2, a, b);

                long cnt = 0;
                for (String pos : kingAttack) {
                    if (queenAttack.contains(pos)) {
                        cnt++;
                    }
                }

                out.println(cnt);
            }
        }
    }
    static int[][] dirs = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    private static Set<String> makeMoves(long x, long y, long a, long b) {
        Set<String> set = new HashSet<>();

        for (int[] d : dirs) {
            long x1 = x + d[0] * a;
            long y1 = y + d[1] * b;
            set.add(x1 + " " + y1);

            long x2 = x + d[0] * b;
            long y2 = y + d[1] * a;
            set.add(x2 + " " + y2);
        }

        return set;
    }

    
}