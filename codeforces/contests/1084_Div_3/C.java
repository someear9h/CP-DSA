
/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine().trim());
            while (t-- > 0) {
                int n = Integer.parseInt(br.readLine().trim());
                String s = br.readLine().trim();

                Deque<Character> st = new ArrayDeque<>();

                for (int i = 0; i < n; i++) {
                    char curr = s.charAt(i);
                    if (!st.isEmpty() && st.peek() == curr) {
                        st.pop();
                    } else {
                        st.push(curr);
                    }
                }

                if (st.isEmpty()) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            }
        }
    }
}