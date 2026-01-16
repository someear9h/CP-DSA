import java.util.*;

public class CountCaesarCipherPairs {
    static class Solution {
        public long countPairs(String[] words) {
            Map<String, Integer> mp = new HashMap<>();
            long pairs = 0;

            for(String word : words) {
                char[] charArr = word.toCharArray();
                int firstChar = charArr[0];

                // normalisation
                for(int i = 0; i < charArr.length; i++) {
                    int normal = (charArr[i] - firstChar + 26) % 26;
                    charArr[i] = (char) normal;
                }

                // then we have the pattern here
                // conver that into string and map it
                String code = new String(charArr);
                int count = mp.getOrDefault(code, 0);
                pairs += count;

                mp.put(code, count+1);
            }

            return pairs;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"ab","aa","za","aa"};

        System.out.println(sol.countPairs(words));
    }
}
