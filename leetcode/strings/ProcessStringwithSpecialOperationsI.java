public class ProcessStringwithSpecialOperationsI {
    private static String processStr(String s) {
        
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(Character.isLetter(curr)) {
                res.append(curr);
            }

            else if(curr == '#') {
                res.append(res);
            }
            
            else if(curr == '%') {
                res.reverse();
            }

            else if(curr == '*') {
                if(res.length() > 0) 
                    res.deleteCharAt(res.length() - 1);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "a#b%*";

        System.out.println(processStr(s));
    }
}
