import java.io.*;
import java.util.*;

public class Hamburgers {

    static long nb, ns, nc; 
    static long pb, ps, pc; 
    static long ss, sb, sc;
    static long r; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try (PrintWriter out = new PrintWriter(System.out)) {
            String s = br.readLine();
            for(char ch : s.toCharArray()) {
                if(ch == 'S') ss++;
                if(ch == 'B') sb++;
                if(ch == 'C') sc++;
            }

            var st = new StringTokenizer(br.readLine());

            nb = Integer.parseInt(st.nextToken());
            ns = Integer.parseInt(st.nextToken());
            nc = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            pb = Integer.parseInt(st.nextToken());
            ps = Integer.parseInt(st.nextToken());
            pc = Integer.parseInt(st.nextToken());

            r = Long.parseLong(br.readLine());

            // binary search
            long l = 0, h = 2_000_000_000_000L;
            long ans = 0;
            while(l <= h) {
                long mid = l + (h-l) /2;
                if(canMake(mid)) {
                    // if we can make 'mid' burgers with money 'r'
                    // then lets see if we can make some more
                    ans = mid;
                    l = mid+1;
                } else {
                    h = mid-1;
                }
            }

            out.println(ans);
        }
    }

    // parameter as x -> do we have money to make x burgers
    static boolean canMake(long x) {
        long needB = x * sb;
        long needC = x * sc;
        long needS = x * ss;

        // if we dont have sufficient in the kitchn then 
        // we go to market here
        // cnt = says how much "number" do we need to buy 
        long cntB = Math.max(0, needB - nb);
        long cntC = Math.max(0, needC - nc);
        long cntS = Math.max(0, needS - ns);

        // cntB * pb -> if we have to buy 2 things and each have price 2 then totalSpent 
        // for that product = 4 (2 * 2)
        long totalCost = (cntB * pb) + (cntC * pc) + (cntS * ps);

        return totalCost <= r;
    }
}