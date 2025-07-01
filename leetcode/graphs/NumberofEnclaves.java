import java.util.*;

public class NumberofEnclaves {
    private static int solve(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < m; i++) {
            if(grid[i][0] == 1) {
                q.add(new int[] {i, 0});
                grid[i][0] = -1;
            }

            if(grid[i][n - 1] == 1) {
                q.add(new int[] {i, n - 1});
                grid[i][n - 1] = -1;
            }
        }

        for(int j = 0; j < n; j++) {
            if(grid[0][j] == 1) {
                q.add(new int[] {0, j});
                grid[0][j] = -1;
            }

            if(grid[m - 1][j] == 1) {
                q.add(new int[] {m - 1, j});
                grid[m - 1][j] = -1;
            }  
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0], col = cell[1];

            for(int[] dir : directions) {
                int nrow = row + dir[0];
                int ncol = col + dir[1];

                if(nrow < m && nrow >= 0 && ncol < n && ncol >= 0 && grid[nrow][ncol] == 1) {
                    q.add(new int[] {nrow, ncol});
                    grid[nrow][ncol] = -1;
                }
            }
        }

        int cnt = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0,0,0,0}, 
            {1,0,1,0}, 
            {0,1,1,0}, 
            {0,0,0,0}
        };

        int ans = solve(grid);
        System.out.println(ans);        
    }
}
