import java.io.*;
import java.util.*;

/*
 *  Time Complexity: O(n+26) → Counting + iteration over fixed 26 characters.
    Space Complexity: O(26) → Frequency and map storage.
 */

public class MajorityFrequencyCharacters {
    public static String majorityFrequencyGroup(String s) {
        int[] freq = new int[26];
        for(char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // create a map to store the frequency and the strings 
        Map<Integer, String> mp = new HashMap<>();

        for(int i = 0; i < 26; i++) {
            if(freq[i] != 0) {
                char ch = (char) (i + 'a');

                // we append the string having same frequency
                mp.put(freq[i], mp.getOrDefault(freq[i], "") + ch); 
            }
        }

        String ans = "";
        int maxCnt = 0;

        for(Map.Entry<Integer, String> entry : mp.entrySet()) {
            String curr = entry.getValue();
            int currCnt = entry.getKey();

            if(curr.length() > ans.length() || 
                (curr.length() == ans.length() && maxCnt < currCnt)) {
                    ans = curr;
                    maxCnt = currCnt;
                }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            String s = br.readLine();
            out.println(majorityFrequencyGroup(s));
        }

        out.flush();
    }
}
