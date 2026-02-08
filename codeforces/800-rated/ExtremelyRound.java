/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class ExtremelyRound {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            
            List<Integer> round = new ArrayList<>();
            for(int i = 1; i <= 999999; i++) {
                if(checkRound(i)) {
                    round.add(i);
                }
            }
            
            int t = Integer.parseInt(br.readLine());

            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                int cnt = 0;
                for(int r : round) {
                    if(r <= n) {
                        cnt++;
                    } else {
                        break;
                    }
                }

                out.println(cnt);
            }
        }
    }

    private static boolean checkRound(int num) {
        int zeros = 0, digits= 0;
        while(num > 0) {
            if(num % 10 == 0) {
                zeros++;
            }

            digits++;
            num = num /10;
        }

        return zeros == digits-1; // - 1 -> for non zero digit
    }
}