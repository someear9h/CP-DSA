import java.util.Arrays;

public class NonoverlappingIntervals {
    private static int solve(int[][] intervals) {
        int n = intervals.length;
        int count = 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int prevEnd = intervals[0][1]; // move this after sort!

        for(int i = 1; i < n; i++) {
            int currStart = intervals[i][0];
            if(prevEnd > currStart) {
                count++; // overlap → remove one interval
            } else {
                prevEnd = intervals[i][1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("Answer is: " + solve(intervals));
    }
}