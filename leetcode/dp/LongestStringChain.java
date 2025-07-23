import java.util.*;

public class LongestStringChain {
    static Comparator<String> comp = (a, b) -> a.length() - b.length();

    private static boolean isPredec(String s1, String s2) {
        int first = 0, second = 0;
        
        if(s1.length() != s2.length() + 1) return false;

        while(first < s1.length()) {
            if(second < s2.length() && s1.charAt(first) == s2.charAt(second)) {
                first++;
                second++;
            } else {
                first++;
            }
        }

        return first == s1.length() && second == s2.length();
    }

    public static int longestStrChain(String[] words) {
        int n = words.length;
        int[] dp = new int[n];
        int mx = 1;

        Arrays.sort(words, comp);
        Arrays.fill(dp, 1);

        for(int i = 0; i < n; i++) {
            for(int pi = 0; pi < i; pi++) {
                if((isPredec(words[i], words[pi]) && dp[i] < dp[pi] + 1)) {
                    dp[i] = dp[pi] + 1;
                }
            }

            if(dp[i] > mx) mx = dp[i];
        }

        return mx;
    }

    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};

        System.out.println(longestStrChain(words));
    }
}