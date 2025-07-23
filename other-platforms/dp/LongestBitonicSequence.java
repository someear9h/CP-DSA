import java.util.*;

public class LongestBitonicSequence {
    public static int longestBitonicSequence(int[] arr, int n) {
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        // fill the dp1 from left to right
        for(int i = 0; i < n; i++) {
            for(int pi = 0; pi < i; pi++) {
                if(arr[i] > arr[pi] && dp1[i] < dp1[pi] + 1) {
                    dp1[i] = dp1[pi] + 1;
                }
            }
        }

        // fill the dp from right to left
        for(int i = n - 1; i >= 0; i--) {
            for(int pi = n - 1; pi > i; pi--) {
                if(arr[i] > arr[pi] && dp2[i] < dp2[pi] + 1) {
                    dp2[i] = dp2[pi] + 1;
                }
            }
        }

        int mx = -1;
        for(int i = 0; i < n; i++) {
            mx = Math.max(mx, dp1[i] + dp2[i] - 1);
        }

        return mx;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] arr = {1, 2, 1, 2, 1};

        System.out.println(longestBitonicSequence(arr, n));
    }
}
