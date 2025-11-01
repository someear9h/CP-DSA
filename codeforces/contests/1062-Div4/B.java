import java.io.*;
import java.util.*;

public class B {
    
    static boolean solve(String s, String t, int n) {
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();

        Arrays.sort(sarr);
        Arrays.sort(tarr);

        StringBuilder sbs = new StringBuilder();
        StringBuilder sbt = new StringBuilder();

        for(int i = 0; i < sarr.length; i++) {
            sbs.append(sarr[i]);
            sbt.append(tarr[i]);
        }

        return sbs.toString().equals(sbt.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-->0) {

                int n = Integer.parseInt(br.readLine());

                StringTokenizer st = new StringTokenizer(br.readLine());
                
                String s = st.nextToken();
                String target = st.nextToken();
                
                String ans = solve(s, target, n) ? "YES" : "NO";
                out.println(ans);
            }
        }
    }
}