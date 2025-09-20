public class EarliestTimetoFinishOneTask {
    public static int earliestTime(int[][] tasks) {
        int n = tasks.length;
        int m = tasks[0].length;
        int mini = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = 0; j < m; j++) {
                sum += tasks[i][j];

            }
            mini = Math.min(sum, mini);
        }

        return mini;
    }

    public static void main(String[] args) {
        int[][] tasks = {{1, 6}, {2, 3}};

        System.out.println(earliestTime(tasks));
    }
}
