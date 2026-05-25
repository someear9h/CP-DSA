/**
 * 
 * 1. Grid Game (LeetCode 2017)
 * 2. Maximum Number of Points with Cost (LeetCode 1937)
 * 3. Maximal Rectangle (LeetCode 85)
 */

public class C_MaximumPathIntersectionSuminaGrid {
    public static int maxScore(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int maxSum = -105;

        // intersection can happen in 3 ways
        // 1. horizontal, 2. vertical and 3. single interior cell (non boundary cells)

        // we cant have a single cell on edges (left, right, bottom, top)
        // where intersection happens. There has to be atleast 2 cells on edges
        // where intersection can happen

        // and for the same reason we dont do standard kadane's algo
        // because if we only take curr cell then at edges we cant do
        // single cell and in interior rows and cols 
        // we already write code for that below

        // so for finding horization and vertical intersections we cant have a 
        // single cell intersecting, we need to take the previous cell's value
        // as well for this specific kadane's

        // go for horizontal lines
        for(int i = 0; i < rows; i++) {
            int currSum = grid[i][0] + grid[i][1];
            maxSum = Math.max(maxSum, currSum);

            for(int j = 2; j < cols; j++) {
                int extSum = currSum + grid[i][j];
                int newSum = grid[i][j-1] + grid[i][j];

                currSum =  Math.max(extSum, newSum);
                maxSum = Math.max(maxSum, currSum);
            }

        }

        // for vertical intersections
        for(int j = 0; j < cols; j++) {
            int currSum = grid[0][j] + grid[1][j];
            maxSum = Math.max(maxSum, currSum);


            for(int i = 2; i < rows; i++) {
                int extSum = currSum + grid[i][j];
                int newSum = grid[i-1][j] + grid[i][j];

                currSum = Math.max(extSum, newSum);
                maxSum = Math.max(maxSum, currSum);
            }

        }

        // no go for single cell (interor rows and cols)
        // where intersection can happen
        for(int i = 1; i < rows-1; i++) {
            for(int j = 1; j < cols-1; j++) {
                maxSum = Math.max(maxSum, grid[i][j]);
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1,2,0,-3},{1,-2,1,0},{-4,2,-1,3},{3,-3,3,-2},{-1,-5,0,1}
        };

        System.out.println(maxScore(grid));
    }
}
