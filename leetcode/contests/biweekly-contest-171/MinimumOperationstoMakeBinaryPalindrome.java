import java.io.*;
import java.util.*;

public class MinimumOperationstoMakeBinaryPalindrome {
    public static int[] minOperations(int[] nums) {
        int n = nums.length;

        int[] ans = new int[n];

        for(int i = 0; i < n; i++) {
            ans[i] = solve(nums[i]);
        }

        return ans;
    }

    private static int solve(int num) {
        // find a number smaller than num which has binary palindrome
        int low = 0;
        for(int i = num; i >= 0; i--) {
            if(isPalindrome(i)) {
                // i has binary palindrome so calculate distance from num 
                low = num - i;
                break;
            }
        }

        // now find a number j greater than num which has binary palindrome
        int up = 0;
        int j = num;
        while(true) {
            if(isPalindrome(j)) {
                // calculate distance and get out of the loop
                up = j - num;
                break;
            }
            j++;
        }

        // minimum distance will be correct answer
        return Math.min(low, up);
    }

    // function to check if a number has binary palindrome
    private static boolean isPalindrome(int num) {
        String s = Integer.toBinaryString(num);
        int left = 0, right = s.length() - 1;

        while(left <= right) {
            if(s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                int n = Integer.parseInt(br.readLine());
                int[] arr = new int[n];

                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int i=0; i < n; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }

                int[] ans =minOperations(arr);
                for(int i = 0; i < n; i++) {
                    out.print(ans[i] + " ");
                }

                out.println();
            }
        }
    }
}
