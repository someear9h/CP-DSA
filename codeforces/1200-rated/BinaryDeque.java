/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class BinaryDeque {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine().trim());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine().trim());
                int n = Integer.parseInt(st.nextToken().trim());
                int s = Integer.parseInt(st.nextToken().trim());
                
                int[] a = new int[n];
                st = new StringTokenizer(br.readLine().trim());
                for(int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken().trim());
                }

                int l = 0, mxLen = -1;
                int currSum = 0;
                for(int r = 0; r < n; r++) {
                    currSum += a[r];
                    while(currSum > s && l <= r) {
                        currSum -= a[l];
                        l++;
                    }
                    
                    if(currSum == s) {
                        mxLen = Math.max(mxLen, r -l +1);
                    }
                }

                if(mxLen == -1) out.println("-1");
                else out.println(n - mxLen);
            }
        }
    }
}