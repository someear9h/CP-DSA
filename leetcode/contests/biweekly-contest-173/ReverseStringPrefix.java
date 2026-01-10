public class ReverseStringPrefix {
    static class Solution {
        public String reversePrefix(String s, int k) {
            String sama = s.substring(0, k);
            String sama1 = s.substring(k);

            StringBuilder sb = new StringBuilder(sama).reverse();
            String sb1 = sb.toString();

            return sb1 + sama1;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        String s = "abcd";
        int k = 2;

        System.out.println(sol.reversePrefix(s, k));
    }
}
