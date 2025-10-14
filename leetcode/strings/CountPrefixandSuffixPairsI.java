public class CountPrefixandSuffixPairsI {
    public static int countPrefixSuffixPairs(String[] words) {
        int n = words.length;
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                String str1 = words[i], str2 = words[j];

                if(str2.startsWith(str1) && str2.endsWith(str1))
                    cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        String[] words = {"pa","papa","ma","mama"};

        System.out.println(countPrefixSuffixPairs(words));
    }
}
