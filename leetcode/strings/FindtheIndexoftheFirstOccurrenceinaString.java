public class FindtheIndexoftheFirstOccurrenceinaString {
    // ---------- This problem can also be done using Single Rolling hash ------------
    // ---------- Double Hashing Helper Class ----------
    static class StringHash {
        private static final long MOD1 = 1_000_000_007L; // Use 53 or 101 if string has uppercase/special chars
        private static final long MOD2 = 1_000_000_009L;
        private static final long BASE1 = 31;
        private static final long BASE2 = 37;

        private long[] h1, p1;
        private long[] h2, p2;

        public StringHash(String s) {
            int n = s.length();
            h1 = new long[n + 1];
            p1 = new long[n + 1];
            h2 = new long[n + 1];
            p2 = new long[n + 1];

            p1[0] = 1;
            p2[0] = 1;

            for (int i = 0; i < n; i++) {
                long val = s.charAt(i) - 'a' + 1;

                h1[i + 1] = (h1[i] * BASE1 + val) % MOD1;
                p1[i + 1] = (p1[i] * BASE1) % MOD1;

                h2[i + 1] = (h2[i] * BASE2 + val) % MOD2;
                p2[i + 1] = (p2[i] * BASE2) % MOD2;
            }
        }

        // Hash of substring s[l..r-1] (left inclusive, right exclusive)
        public long getHash(int l, int r) {
            long v1 = (h1[r] - h1[l] * p1[r - l]) % MOD1;
            if (v1 < 0) v1 += MOD1;

            long v2 = (h2[r] - h2[l] * p2[r - l]) % MOD2;
            if (v2 < 0) v2 += MOD2;

            return (v1 << 32) | v2;
        }
    }

    // ---------- Main Problem Logic ----------
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        if (m > n) return -1;

        // Build hasher for haystack
        StringHash sh = new StringHash(haystack);

        // Compute hash for needle
        long needleHash = computeNeedleHash(needle);

        // Sliding window check
        for (int i = 0; i <= n - m; i++) {
            if (sh.getHash(i, i + m) == needleHash) {
                return i;
            }
        }
        return -1;
    }

    // ---------- Hash computation for needle ----------
    private long computeNeedleHash(String s) {
        long h1 = 0, h2 = 0;
        long MOD1 = 1_000_000_007L;
        long MOD2 = 1_000_000_009L;
        long BASE1 = 31;
        long BASE2 = 37;

        for (char c : s.toCharArray()) {
            long val = c - 'a' + 1;
            h1 = (h1 * BASE1 + val) % MOD1;
            h2 = (h2 * BASE2 + val) % MOD2;
        }
        return (h1 << 32) | h2;
    }

    public static void main(String[] args) {
        FindtheIndexoftheFirstOccurrenceinaString sol = new FindtheIndexoftheFirstOccurrenceinaString();
        String haystack = "sadbutsad", needle = "sad";

        System.out.println(sol.strStr(haystack, needle));
    }
}