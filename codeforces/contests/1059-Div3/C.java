import java.io.*;
import java.util.*;

public class C {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // Impossible Case: msb(b) > msb(a)
                // Integer.numberOfLeadingZeros() is the Java equivalent of __builtin_clz()
                if(Integer.numberOfLeadingZeros(a) > Integer.numberOfLeadingZeros(b)) {
                    out.println(-1);
                } 
                // Already equal
                else if(a == b) {
                    out.println(0);
                }
                // Constructive Case
                else {
                    List<Integer> val = new ArrayList<>();

                    // Phase 1: "Pump a up"
                    // Note: a += x is a trick. When (a & x) == 0,
                    // a + x is the same as a ^ x.
                    for(int i = 0; i < 31; i++) {
                        int x = 1 << i; // x = 2^i
                        if(x <= a && (a & x) == 0) {
                            a += x; // a = a ^ x
                            val.add(x);
                        }
                    }

                    // Phase 2: "Filter down to b"
                    // 'a' is now the "all 1s" number from Phase 1
                    for(int i = 0; i < 31; i++) {
                        int x = 1 << i;
                        // Add an operation for every bit that is '0' in b
                        if(x <= a && (b & x) == 0) {
                            val.add(x);
                        }
                    }

                    out.println(val.size());
                    // Print the list of operations
                    for(int i = 0; i < val.size(); i++) {
                        out.print(val.get(i) + (i == val.size() - 1 ? "" : " "));
                    }
                    out.println();
                }
            }
        }
    }
}