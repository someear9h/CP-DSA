import java.io.*;
import java.util.*;

public class B {
   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                
                int n = Integer.parseInt(br.readLine());
                
                String s = br.readLine();
                List<Integer> ans = new ArrayList<>();

                for(int i = 0; i < n; i++) {
                    if(s.charAt(i) == '0') 
                        ans.add(i + 1);
                }

                out.println(ans.size());
                for(int i : ans) out.print(i + " ");
                out.println();
            }
        }
    }
}