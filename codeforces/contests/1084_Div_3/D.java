
/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class D {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine().trim());
            while (t-- > 0) {
                var st = new StringTokenizer(br.readLine().trim());
                int n = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                x--;
                y--;

                List<Integer> outside = new ArrayList<>();
                List<Integer> inside = new ArrayList<>();

                st = new StringTokenizer(br.readLine().trim());
                for (int i = 0; i < n; i++) {
                    int val = Integer.parseInt(st.nextToken());

                    if (i <= x || i > y) {
                        outside.add(val);
                    } else {
                        inside.add(val);
                    }
                }

                if (!inside.isEmpty()) {
                    int minIdx = 0;
                    int minVal = inside.get(0);
                    for (int i = 1; i < inside.size(); i++) {
                        if (minVal > inside.get(i)) {
                            minVal = inside.get(i);
                            minIdx = i;
                        }
                    }

                    List<Integer> round = new ArrayList<>(inside.size());
                    for (int i = minIdx; i < inside.size(); i++) {
                        round.add(inside.get(i));
                    }

                    for (int i = 0; i < minIdx; i++) {
                        round.add(inside.get(i));
                    }

                    inside = round;
                }

                int minVall = inside.isEmpty() ? -1 : inside.get(0);
                int insertIdx = 0;

                while (insertIdx < outside.size() && outside.get(insertIdx) < minVall) {
                    insertIdx++;
                }

                outside.addAll(insertIdx, inside);

                for (int s : outside) {
                    out.print(s + " ");
                }

                out.println();
            }
        }
    }
}