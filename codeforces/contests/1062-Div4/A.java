import java.io.*;
import java.util.*;

public class A {
    static boolean solve(int a, int b, int c, int d) {
        return (a == b && b == c && c== d);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-->0) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                int D = Integer.parseInt(st.nextToken());
                
                String s = solve(A, B, C, D) ? "YES" : "NO";
                out.println(s);
            }
        }
    }
}