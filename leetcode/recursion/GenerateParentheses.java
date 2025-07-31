import java.util.*;

public class GenerateParentheses {
    static List<String> solve(int n) {
        List<String> res = new ArrayList<>();
        helper(n, res, "", 0, 0);

        return res;
    } 

    static void helper(int n, List<String> res, String s, int open, int close) {
        // base case 
        if(s.length() == 2 * n) {
            res.add(s);
            return;
        }

        if(open < n) {
            // we have to open the parentheses
            helper(n, res, s + "(", open + 1, close);
        }

        if(close < open) {
            // close the parentheses which are open
            helper(n, res, s + ")", open, close + 1);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        List<String> res = solve(n);

        for(String s : res) {
            System.out.print(s);
        }

        System.out.println();
    }
}
