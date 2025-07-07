public class UniquePathsIII {
    public int uniquePathsIII(int[][] grid) {
        int x = 0, y = 0, zeros = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) zeros++;
                if(grid[i][j] == 1) {
                    x = i;
                    y = j;
                }
            }
        }

        return findPath(grid, x, y, zeros);
    }

    private int findPath(int[][] grid, int i, int j, int zeros) {
        
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == - 1) {
            return 0; // no way from here
        }

        if(grid[i][j] == 2) {
            return zeros == -1 ? 1 : 0;
        }


        grid[i][j] = -1; // mark visisted
        zeros--;

        // explore all four ways
        int allWays = findPath(grid, i + 1, j, zeros) + findPath(grid, i - 1, j, zeros) + 
                        findPath(grid, i, j + 1, zeros) + findPath(grid, i, j - 1, zeros);

        grid[i][j] = 0;
        zeros++;

        return allWays;
    }

    public static void main(String[] args) {
        UniquePathsIII sol = new UniquePathsIII();

        int[][] grid = {{1,0,0,0}, {0,0,0,0}, {0,0,2,-1}};

        System.out.println(sol.uniquePathsIII(grid));
    }
}
