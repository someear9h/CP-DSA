import java.io.*;
import java.util.*;

public class LexicographicallySmallestPermutationGreaterThanTarget {
    private static String result = null;
    private static void dfs(int[] freq, String target, int index, StringBuilder builder, boolean isGreater) {
        // If answer already found, prune search
        if (result != null) return;

        // Base case: complete string built
        if (index == target.length()) {
            if (isGreater) {
                result = builder.toString();
            }
            return;
        }

        int targetChar = target.charAt(index) - 'a';

        for (int c = 0; c < 26; c++) {
            if (freq[c] == 0) continue;

            // If still matching prefix and current char is smaller → skip
            if (!isGreater && c < targetChar) continue;

            boolean newGreater = isGreater || (c > targetChar);

            // choose
            freq[c]--;
            builder.append((char) ('a' + c));

            dfs(freq, target, index + 1, builder, newGreater);

            // undo (backtrack)
            builder.deleteCharAt(builder.length() - 1);
            freq[c]++;
        }
    }

    public static String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) freq[ch - 'a']++;

        dfs(freq, target, 0, new StringBuilder(), false);

        return result == null ? "" : result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                String target = st.nextToken();

                out.println(lexGreaterPermutation(s, target));
            }
        }
    }
}