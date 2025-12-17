import java.util.*;

public class RepeatedDNASequences {
    public static List<String> findRepeatedDnaSequences(String s) {
        Set<String> repeated = new HashSet<>();
        Set<Integer> seen = new HashSet<>();

        Map<Character, Integer> mp = new HashMap<>();
        mp.put('A', 0);
        mp.put('C', 1);
        mp.put('G', 2);
        mp.put('T', 3);

        int n = s.length();
        if(n < 10) return new ArrayList<>();
        int hash = 0;

        // hash for the first window
        for(int i = 0; i < 10; i++) {
            hash = (hash * 4) + mp.get(s.charAt(i));
        }

        seen.add(hash);

        int pow4 = (int) Math.pow(4, 9); // leaving char would have 4^9 hash

        for(int i = 10; i < n; i++) {
            // subtract leaving char's hash contribution
            // when sliding window of size 10
            int leavingChar = mp.get(s.charAt(i - 10));
            hash = hash - (leavingChar * pow4);

            // make room for current character
            // shift by 4 
            hash = hash * 4;
            int currChar = mp.get(s.charAt(i));
            hash = hash + currChar;

            if(seen.contains(hash)) {
                repeated.add(s.substring(i - 9, i + 1));
            } else {
                seen.add(hash);
            }
        }

        return new ArrayList<>(repeated);
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

        System.out.println(findRepeatedDnaSequences(s));
    }
}
