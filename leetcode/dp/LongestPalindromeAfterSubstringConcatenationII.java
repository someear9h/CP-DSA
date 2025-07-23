public class LongestPalindromeAfterSubstringConcatenationII {
    private static int longestPalindrome(String s, String t) {
        int m = s.length(), n = t.length();
        int maxLen = 0;
        int[][] dp = new int[m + 1][n + 1];

        int[] ss = new int[m + 1], tt = new int[n + 1];

        // Precompute longest palindromic substrings starting at i in s
        for (int i = 0; i < m; i++) {
            for (int j = m - 1; j >= i; j--) {
                if (isPalindrome(s, i, j)) {
                    ss[i] = j - i + 1;
                    break;
                }
            }
        }

        // Precompute longest palindromic substrings ending at i in t
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalindrome(t, j, i)) {
                    tt[i + 1] = i - j + 1;  // Store in tt[i+1] to align with 1-based index for dp
                    break;
                }
            }
        }

        // Initialize base cases
        for (int i = 0; i < m; i++) {
            dp[i][0] = ss[i];
        }

        for (int i = 1; i <= n; i++) {
            dp[m][i] = tt[i];  // dp[m][i] — no char taken from s
        }

        // Fill the dp table from bottom-up
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i) == t.charAt(j - 1)) {
                    dp[i][j] = Math.max(ss[i], tt[j]);
                    dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
                } else {
                    dp[i][j] = Math.max(ss[i], tt[j]);
                }

                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        return maxLen;
    }

    // Helper to check if a substring is a palindrome
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abcde", t = "ecdba";
        System.out.println(longestPalindrome(s, t));  // Output: 5
    }
}
