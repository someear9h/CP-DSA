import java.io.*;
import java.util.*;

public class DistinctPointsReachableAfterSubstringRemoval {
    public static int distinctPoints(String s, int k) {
        Set<String> seen = new HashSet<>();
        seen.add("0, 0");

        int x = 0, y = 0;

        for(int i = k; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case 'U':
                    y++;
                    break;

                case 'D':
                    y--;
                    break;

                case 'L':
                    x--;
                    break;

                case 'R':
                    x++;
                    break;
            }

            switch(s.charAt(i - k)) {
                case 'U':
                    y--;
                    break;

                case 'D':
                    y++;
                    break;

                case 'L':
                    x++;
                    break;

                case 'R':
                    x--;
                    break;
            }

            seen.add(x + ", " + y);
        }

        return seen.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = st.nextToken();
        int k = Integer.parseInt(st.nextToken());

        out.println(distinctPoints(s, k));

        out.close();
    }
}
