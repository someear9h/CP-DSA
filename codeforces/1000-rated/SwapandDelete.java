/**
someear1h 
*/
 
import java.io.*;

public class SwapandDelete {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                String s = br.readLine();

                int n = s.length();

                if(n == 0) {
                    out.println("0");
                    continue;
                }

                int ones = 0;
                int zeros = 0;

                for(char ch : s.toCharArray()) {
                    if(ch == '1') ones++;
                    else zeros++;
                }

                int tLen = 0;
                for(char ch : s.toCharArray()) {
                    if(ch == '0' && ones > 0) {
                        ones--;
                        tLen++;
                    } else if(ch =='1' && zeros >0) {
                        zeros--;
                        tLen++;
                    } else {
                        break;
                    }
                }
                
                out.println(n-tLen);
            }
        }
    }
}