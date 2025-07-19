class Solution {
    public static char processStr(String s, long k) {
        long len = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) len++;
            else if (c == '*' && len > 0) len--;
            else if (c == '#') len *= 2;
        }
        if (k >= len) return '.';

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                if (k == len - 1) return c;
                len--;
            } else if (c == '*') {
                len++;
            } else if (c == '#') {
                len /= 2;
                if (k >= len) k -= len;
            } else if (c == '%') {
                k = len - 1 - k;
            }
        }
        return '.';
    }

    public static void main(String[] args) {
        String s = "a#b%*";
        long k = 1;

        System.out.println(processStr(s, k));
    }
}
