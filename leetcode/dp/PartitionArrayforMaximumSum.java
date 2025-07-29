public class PartitionArrayforMaximumSum {
    static int solve(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];

        for(int i = n - 1; i >= 0; i--) {
            int maxEle = (int)-1e9, maxSum = (int)-1e9, len = 0;

            for(int j = i; j < Math.min(n, i + k); j++) {
                len++;
                maxEle = Math.max(maxEle, arr[j]);
                int sum = (maxEle * len) + dp[j + 1];

                maxSum = Math.max(sum, maxSum);
            }

            dp[i] = maxSum;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int[] arr = {1,15,7,9,2,5,10};
        int k = 3;

        System.out.println(solve(arr, k));

        
    }
}
