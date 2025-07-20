public class DisconnectPathinaBinaryMatrixbyatMostOneFlip {
    private boolean solve(int[][] grid) {
        // idea: if we can reach the last cell by more than 1 time then we cant disconnect by changing 1 cell
        if(!dfs(grid, 0, 0)) return true;
        grid[0][0] = 1;
        
        if(!dfs(grid, 0, 0)) return true;
        
        return false;
    }
    
    private boolean dfs(int[][] grid, int r, int c) {
        int m = grid.length, n = grid[0].length;
        
        if(r >= m || c >= n || grid[r][c] == 0) return false;
        if(r == m - 1 && c == n - 1) return true; // we can reach the last cell
        
        grid[r][c] = 0; // mark as visited
        
        if(dfs(grid, r + 1, c)) return true;
        if(dfs(grid, r, c + 1)) return true;
        
        return false;
    }
    
    public static void main(String[] args) {
        DisconnectPathinaBinaryMatrixbyatMostOneFlip sol = new DisconnectPathinaBinaryMatrixbyatMostOneFlip();
        
        int[][] grid = {{1,1,1},{1,0,0},{1,1,1}};
        
        System.out.println(sol.solve(grid));
    }
}