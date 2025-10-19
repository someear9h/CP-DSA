public class PalindromicSubstrings {
    public static int countSubstrings(String s) {
        int n = s.length();
        int ans = 0;

        for(int i = 0; i < n; i++) {
            ans += cntPal(s, n, i, i); // count palindromes of odd length
            ans += cntPal(s, n, i, i + 1); // count palindromes of even length
        }

        return ans;
    }

    private static int cntPal(String s, int n, int left, int right) {
        int cnt = 0; // count palindromes
        while(left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            cnt++;
            left--;
            right++;
        }

        return cnt;
    }    

    public static void main(String[] args) {
        String s = "aaba";

        System.out.println(countSubstrings(s));
    }
}
