import java.util.HashSet;
import java.util.Set;

public class DistinctEchoSubstrings {
    private static final long mod = (long)1e9 + 7;
    private static final long base = 31;

    public static int distinctEchoSubstrings(String text) {
        int n = text.length();
        long[] h = new long[n+1];
        long[] p = new long[n+1];
        p[0] = 1; // powers

        Set<String> seen = new HashSet<>();

        for(int i = 0; i < n; i++) {
            p[i+1] = (p[i] * base) % mod;
            h[i+1] = (h[i] * base + (text.charAt(i) - 'a' + 1)) % mod;
        }

        for(int len = 1; len <= n / 2; len++) {
            for(int i = 0; i <= n - len * 2; i++) {
                long hashLeft = getHash(i, i+len, h, p);
                long hashRight = getHash(i+len, i + 2*len, h, p);

                if(hashLeft == hashRight) {
                    seen.add(text.substring(i, i + 2* len));
                }
            }
        }

        return seen.size();
    }

    // calculate hash of substring from index l to r
    private static long getHash(int l, int r, long[] h, long[] p) {
        long val = (h[r] - h[l] * p[r - l]) % mod;
        if(val < 0) val += mod;
        return val;
    }

    public static void main(String[] args) {
        String text = "abcabcabc";
        System.out.println(distinctEchoSubstrings(text));
    }
}
