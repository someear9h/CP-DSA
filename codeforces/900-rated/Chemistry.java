/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class Chemistry {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();

                int oddCnt = 0;
                int[] cnt = new int[26];
                for(char ch : s.toCharArray()) {
                    cnt[ch-'a']++;
                }

                for(int c : cnt) {
                    if(c % 2 != 0) oddCnt++;
                }

                boolean ans = oddCnt - k <= 1;
                if(ans) out.println("YES");
                else out.println("NO");
            }
        }
    }

    
}