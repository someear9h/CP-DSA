import java.util.*;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> deq = new ArrayDeque<>();
        int[][] cost = new int[m][n];

        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        deq.offerFirst(new int[]{0, 0});
        cost[0][0] = 0;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // right, left, down, up

        while (!deq.isEmpty()) {
            int[] curr = deq.pollFirst();
            int r = curr[0], c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i][0];
                int nc = c + dirs[i][1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newCost = cost[r][c] + (grid[r][c] == i + 1 ? 0 : 1);

                    if (newCost < cost[nr][nc]) {
                        cost[nr][nc] = newCost;

                        if (grid[r][c] == i + 1)
                            deq.offerFirst(new int[]{nr, nc});
                        else
                            deq.offerLast(new int[]{nr, nc});
                    }
                }
            }
        }

        return cost[m - 1][n - 1];
    }
    
    public static void main(String[] args) {
        int[][] grid = {{1, 2}, {4, 3}};
        
        Solution sol = new Solution();
        
        System.out.println(sol.minCost(grid));
    }
}
