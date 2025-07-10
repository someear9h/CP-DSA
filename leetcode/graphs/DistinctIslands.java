import java.util.*;
public class DistinctIslands {
    int[] row = {-1, 1, 0, 0};
    int[] col = {0, 0, -1, 0};
    char[] dir = {'U', 'D', 'L', 'R'};
    
    private int countDistinctIslands(int[][] grid) {
        Set<String> shapes = new HashSet<>();
        for(int i = 0;i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(i, j, grid, path, 'O');
                    shapes.add(path.toString());
                }
            }
        }
        return shapes.size();
    }

    void dfs(int i, int j, int[][] grid, StringBuilder path, char move) {
        grid[i][j] = 0; // mark visited
        path.append(move);

        // explore all directions
        for(int d = 0; d < 4; d++) {
            int ni = i + row[d];
            int nj = j + col[d];

            if(ni < grid.length && ni >= 0 && nj < grid[0].length && nj >= 0 && grid[ni][nj] == 1) {
                dfs(ni, nj, grid, path, dir[d]);
            }
        }
        path.append('B'); // 'B' = backtrack
    }

    public static void main(String[] args) {
        DistinctIslands obj = new DistinctIslands();
        int[][] grid = {{1, 1, 0, 0, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 0, 1, 1},
                        {0, 0, 0, 1, 1}};

        System.out.println(obj.countDistinctIslands(grid));

        int[][] grid1 = {{1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}};

        System.out.println(obj.countDistinctIslands(grid1));
    }
}
