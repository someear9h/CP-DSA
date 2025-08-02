public class MaximumNumberofSubsequencesAfterOneInserting {
    private static long numOfSubsequences(String s) {
        int n = s.length();
        long[] prefix = new long[n + 1];
        long[] suffix = new long[n + 1];

        // prefix for number of L's before ith idx
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'L') {
                prefix[i + 1] = 1;
            }

            prefix[i + 1] += prefix[i];
        }

        // suffix for number of T's after the ith index
        for(int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'T') {
                suffix[i] = 1;
            }
            suffix[i] += suffix[i + 1];
        }

        long resWithNoChange = 0;
        long resWithL = 0;
        long resWithT = 0;
        long resWithC = 0;

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'C') {
                resWithNoChange += prefix[i] * suffix[i + 1];
                resWithL += (prefix[i] + 1) * suffix[i + 1];
                resWithT += prefix[i] * (suffix[i + 1] + 1);
            } else {
                // add C
                resWithC = Math.max(resWithC, prefix[i] * suffix[i]);
            }
        }
        // When you add a 'C' to the string (to get extra subsequences),
        // you don’t lose the ones that were already there.
        resWithC += resWithNoChange;
        return Math.max(resWithL, Math.max(resWithC, resWithT));
    }

    public static void main(String[] args) {
        String s = "LMCT";

        System.out.println(numOfSubsequences(s));
    }
}
