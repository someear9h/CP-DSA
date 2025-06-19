import java.util.*;

public class InsertInterval {
    public static int[][] solve(int[][] intervals, int[] newInterval) {
        List<int[]> intervalList = new ArrayList<>(Arrays.asList(intervals));
        intervalList.add(newInterval);
        Collections.sort(intervalList, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>();
        int[] prev = intervalList.get(0);
       
        for(int i = 1; i < intervalList.size(); i++) {
            int[] curr = intervalList.get(i);

            if(prev[1] >= curr[0]) {
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
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] res = solve(intervals, newInterval);

        for(int i = 0; i < intervals.length; i++) {
            for(int j = 0; j < 2; j++) {
                System.out.print(res[i][j]+ " ");
            }
        }
        System.out.println();
    }
}
