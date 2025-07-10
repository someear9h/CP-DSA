import java.io.*;
import java.util.*;

public class PathWithMinimumEffort {
    
    private static int solve(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] {0, 0, 0}); // effort, row, column

        int[][] dist = new int[rows][cols]; // efforts array
        for(int[] r : dist) {
            Arrays.fill(r, (int)1e9);
        }

        while(!pq.isEmpty()) {
            int[] currPos = pq.poll();
            int eff = currPos[0], x = currPos[1], y = currPos[2];

            if(x == rows - 1 && y == cols - 1) return eff;
            
            // explore directions
            for(int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols) {

                    int newEff = Math.max(eff, Math.abs(heights[nx][ny] - heights[x][y]));
    
                    if(newEff < dist[nx][ny]) {
                        dist[nx][ny] = newEff;
    
                        pq.add(new int[] {newEff, nx, ny});
                    }
                }

            }
        }
        return 0;
    }  
    
    public static void main(String[] args)throws IOException {
        int[][] heights1 = {
            {1, 2, 2},
            {3, 8, 2},
            {5, 3, 5}
        };

        int[][] heights2 = {
            {1, 2, 3},
            {3, 8, 4},
            {5, 3, 5}
        };

        int[][] heights3 = {
            {1, 2, 1, 1, 1},
            {1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1},
            {1, 1, 1, 2, 1}
        };

        System.out.println("Minimum effort (Test 1): " + solve(heights1)); // Expected: 2
        System.out.println("Minimum effort (Test 2): " + solve(heights2)); // Expected: 1
        System.out.println("Minimum effort (Test 3): " + solve(heights3)); // Expected: 0
    }
}
