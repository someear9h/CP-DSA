/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class Virus {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine().trim());
            while(t-->0) {

                var st = new StringTokenizer(br.readLine().trim());
                
                long n = Long.parseLong(st.nextToken().trim());
                long m = Long.parseLong(st.nextToken().trim());
                
                st = new StringTokenizer(br.readLine().trim());
                long[] arr = new long[(int)m];
                
                for(int i = 0; i < m; i++) {
                    arr[i] = Long.parseLong(st.nextToken().trim());
                }
                
                Arrays.sort(arr);
                
                List<Long> gaps = new ArrayList<>();
                for(int i = 0; i < m-1; i++) {
                    gaps.add(arr[i+1] - arr[i] - 1);
                }
                
                gaps.add(arr[0] + (n - arr[(int)m-1]) - 1);
                
                Collections.sort(gaps, Collections.reverseOrder());
                
                int days = 0;
                long saved = 0;
                for(long gapLen : gaps) {
                    long currGapLen = gapLen - (2* days);
                    
                    if(currGapLen > 2) {
                        saved += currGapLen - 1;
                        days += 2;
                    }
                    
                    else if(currGapLen == 1 || currGapLen == 2) {
                        saved++;
                        days++;
                    }
                    
                    else if(currGapLen <= 0) {
                        continue;
                    }
                }
                
                out.println(n - saved);
            }
        }
    }
}