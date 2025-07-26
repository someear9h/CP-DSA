public class MatrixChainMultiplication {
	private static int matrixMultiplication(int[] arr , int N) {
		int[][] dp = new int[N][N];

		// base case when i == j
		for(int i = 1; i < N; i++) {
			dp[i][i] = 0;
		}

		for(int i = N - 1; i >= 1; i--) {
			for(int j = i + 1; j < N; j++) {
				int mn = (int)1e9;
				// i and j are start and end index of the partitions in the array
				for(int k = i; k < j; k++) {
					int steps = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];
					mn = Math.min(mn, steps);
				} 

				dp[i][j] = mn;
			}
		}

		return dp[1][N - 1];
	}

    public static void main(String[] args) {
        int[] arr = {10, 15, 20, 25};
        int N = 4;

        System.out.println(matrixMultiplication(arr, N));
    }
}
