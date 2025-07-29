import java.util.*;

public class PalindromePartitioningII {
    public int minCut(String s) {
        int n = s.length();
        
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return f(s, 0, dp) - 1;
    }

    // The function now finds the minimum cuts for the suffix s[i...n-1].
    public int f(String s, int i, int[] dp) {

        if(i == s.length()) return 0;
        if(dp[i] != -1) return dp[i];
        
        int minCuts = (int)1e9;
        for(int k = i; k < s.length(); k++) {
            if(isPal(s, i, k)) {
                int cuts = 1 + f(s, k + 1, dp);
                minCuts = Math.min(minCuts, cuts);
            }
        }

        return dp[i] = minCuts;
    }

    // Your isPal function is perfect and remains unchanged.
    public boolean isPal(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}