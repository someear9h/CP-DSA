/**
someear1h 
*/
 
public class Solution {
    public static void main(String[] args) {
        int i = 3, n = 0;
        f(i, n);
    }

    // pritnt name n times
    static void f(int i, int n) {
        if(i <= n) return;

        System.out.println(i);

        f(i-1, n);
    }
}