import java.io.*;
import java.util.*;

public class LexicographicallySmallestPalindromicPermutationGreaterThanTarget {
    private static String res = null;
    private static void dfs(int[] freq, int idx, int halfLen, char mid, String target, StringBuilder half, boolean greater) {

        if(res != null) return;
        // base case
        if(idx == halfLen) {
            String left = half.toString();
            StringBuilder right = new StringBuilder(left).reverse();
            
            // if mid is not there then left + right and if mid is there we add it in mid 
            String palindrome = mid == 0 ? left + right : left + mid + right;
            
            if (palindrome.compareTo(target) > 0) res = palindrome;
            return;
        }

        for(int c = 0; c < 26; c++) {
            if(freq[c] < 2) continue;
            if(!greater && c < target.charAt(idx) - 'a') continue;
            boolean newGreater = greater || (c > target.charAt(idx) - 'a');

            freq[c] -= 2;
            half.append((char) (c + 'a'));

            dfs(freq, idx + 1, halfLen, mid, target, half, newGreater);

            // backtrack
            half.deleteCharAt(half.length() - 1);
            freq[c] += 2;
        }
    }

    public static String lexPalindromicPermutation(String s, String target) {
        int[] freq = new int[26];
        for(char ch : s.toCharArray()) freq[ch - 'a']++;
        int oddcnt = 0;
        char mid = 0;

        for(int i = 0; i < 26; i++) {
            if(freq[i] % 2 != 0) {
                oddcnt++;
                mid = (char) (i + 'a');
            }
        }

        if(oddcnt > 1) return "";
        int halfLen = s.length() / 2;
        res = null;
        dfs(freq, 0, halfLen, mid, target, new StringBuilder(), false);

        return res == null ? "" : res;
    }

    public static void main(String[] args) throws IOException {
        var br  = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {

            var st =new StringTokenizer(br.readLine());
    
            String s = st.nextToken(), t = st.nextToken();
    
            out.println(lexPalindromicPermutation(s, t));
            System.out.println(lexPalindromicPermutation(s, t));
        }

    }
}
