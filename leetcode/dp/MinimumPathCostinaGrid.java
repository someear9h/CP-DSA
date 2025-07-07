public class MinimumPathCostinaGrid {
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static int minPathCost(int[][] grid, int[][] moveCost) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] prev = new int[cols];
        
        // base case: u can start from any column in 1st row
        for(int i = 0; i < cols; i++) {
            prev[i] = grid[0][i];
        }

        for(int i = 1; i < rows; i++) {
            int[] curr = new int[cols];
            
            for(int j = 0; j < cols; j++) { 
                int minCost = Integer.MAX_VALUE;
                for(int pr = 0; pr < cols; pr++) { //pr = looping through previous row
                    int prevVal = grid[i - 1][pr];
                    minCost = Math.min(minCost, prev[pr] + moveCost[prevVal][j] + grid[i][j]);
                }
                curr[j] = minCost;
            }
            prev = curr;
        }

        int res = Integer.MAX_VALUE;
        for(int a : prev) {
            res = Math.min(res, a);
        }
        return res;
    }

    public static void main(String[] args) {
        
        int[][] grid = {
            {5, 1},
            {4, 2},
            {3, 1}
        };

        int[][] moveCost = {
            {9, 8}, 
            {1, 5}, 
            {10, 12}, 
            {18, 6}, 
            {3, 5}, 
            {8, 2}
        };

        int result = minPathCost(grid, moveCost);
        System.out.println("Minimum Path Cost: " + result); 
    }
}
