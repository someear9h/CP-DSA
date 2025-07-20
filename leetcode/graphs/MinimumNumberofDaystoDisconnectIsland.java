class Solution {
    public int minDays(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        if(countIslands(grid) != 1) return 0; // already disconnected

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    // change this cell to water temporarily
                    grid[i][j] = 0;
                    if(countIslands(grid) != 1) return 1;
                    grid[i][j] = 1;
                }
            }
        }

        return 2;
    }

    private int countIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        boolean[][] vis = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && !vis[i][j]) {
                    cnt++;
                    dfs(grid, i, j, vis);
                }
            }
        }
        return cnt;
    }

    private void dfs(int[][] grid, int r, int c, boolean[][] vis) {
        int m = grid.length, n = grid[0].length;
        vis[r][c] = true;

        // go through 4 directions
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for(int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];

            if(nr >= 0 && nr < m && nc >= 0 && nc < n && !vis[nr][nc] && grid[nr][nc] == 1) {
                dfs(grid, nr, nc, vis);
            }
        }
    }
}