import java.io.*;
import java.util.*;

public class LexicographicallySmallestPermutationGreaterThanTarget {
    public static String lexGreaterPermutation(String s, String target) {
        char[] sArr = s.toCharArray();
        int[] freq = new int[26];

        for(char c : sArr) freq[c - 'a']++;

        Arrays.sort(sArr);

        StringBuilder sb = new StringBuilder();
        String ans = "";

        for(int i = 0; i < s.length(); i++) {
            int curr = target.charAt(i) - 'a';

            for(int j = curr; j < 26; j++) {
                if(freq[j] == 0) continue;

                freq[j]--;
                sb.append((char) (j + 'a'));
                String minTail = buildMinString(freq);
                String candidate = sb.toString() + minTail;
                
                // if candidate is lexographically > target
                if(candidate.compareTo(target) > 0) {
                    if(ans.equals("") || candidate.compareTo(ans) < 0) {
                        ans = candidate;
                    }
                }

                // backtrack
                sb.deleteCharAt(sb.length() - 1);
                freq[j]++;
            }

            // greedy
            if(freq[curr] > 0) {
                freq[curr]--;
                sb.append((char) (curr + 'a'));
            }
            else break;
        }

        return ans;
    }
    
    // helper function to build smallest possible string 
    // using remaning characters in freq
    private static String buildMinString(int[] freq) {
        int[] copy = Arrays.copyOf(freq, freq.length);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < copy.length; i++) {
            while(copy[i]-- > 0) {
                sb.append((char) (i + 'a'));
            }
        }

        return sb.toString();
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
