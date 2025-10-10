public class RemoveKBalancedSubstrings {
    // tc: O(n * k), sc: O(n)
    public String removeSubstring(String s, int k) {
        StringBuilder ans = new StringBuilder(s);
        StringBuilder patternBuild = new StringBuilder();

        for(int i = 0; i < k; i++) {
            patternBuild.append('(');
        }

        for(int i = 0; i < k; i++) {
            patternBuild.append(')');
        }

        String pattern = patternBuild.toString();

        int i = 0, patternLen = 2 * k;
        while(i <= ans.length() - patternLen) {
            if(ans.substring(i, i + patternLen).equals(pattern)) {
                // this pattern should be removed from input string
                ans.delete(i, i + patternLen);

                // go back to check new formed k balanced strings
                i = Math.max(0, i - patternLen); 
            } else {
                i++;
            }
        }

        return ans.toString();
    }


    public String removeSubstring1(String s, int k) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < k; i++) sb.append('(');
        for(int i = 0; i < k; i++) sb.append(')');

        String removal = sb.toString();

        StringBuilder ans = new StringBuilder();
        for(char ch : s.toCharArray()) {
            ans.append(ch);
            int ansLen = ans.length();
            int removalLen = removal.length();

            if(ansLen >= removalLen && ans.substring(ansLen - removalLen).equals(removal)) {
                ans.setLength(ansLen - removalLen);
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "((()))()()()";
        int k = 3;

        RemoveKBalancedSubstrings sol = new RemoveKBalancedSubstrings();

        System.out.println(sol.removeSubstring(s, k));
        System.out.println(sol.removeSubstring1(s, k));
    }
}
