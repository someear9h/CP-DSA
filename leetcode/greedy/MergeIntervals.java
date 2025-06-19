import java.util.*;

public class MergeIntervals {
    public static int[][] solve(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] prev = intervals[0];
        
        for(int i = 1; i < n; i++) {
            int[] curr = intervals[i];
            if(curr[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], curr[1]);
                
            } else {
                res.add(prev);
                prev = curr;
            }
        }
        res.add(prev);

        return res.toArray(new int[res.size()][]);
    }
    
    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {5, 6}};

        int[][] res = solve(intervals);

        for(int i = 0; i < res.length; i++) {
            for(int j = 0; j < 2; j++) {
                System.out.print(res[i][j] + " ");
            }
        }
        System.out.println();
    }
}