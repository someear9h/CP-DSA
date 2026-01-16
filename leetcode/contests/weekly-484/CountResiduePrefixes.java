public class CountResiduePrefixes {
    
    public static int residuePrefixes(String s) {
        int[] freq = new int[26];
        int dis = 0;
        int cnt = 0;

        for(int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';

            freq[ch]++;
            if(freq[ch] == 1) {
                dis++;
            }

            if((i+1) % 3 == dis) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        String s = "bob";
        System.out.println(residuePrefixes(s));
    }
}

