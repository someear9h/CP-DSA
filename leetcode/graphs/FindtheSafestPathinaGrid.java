import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindtheSafestPathinaGrid {
    public static int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        if(grid.get(0).get(0) == 1 || grid.get(n-1).get(n-1) == 1) {
            return 0;
        }

        // 1. BFS to get distance to nearest thief for every cell
        int[][] distToThief = new int[n][n];
        for(int[] r : distToThief) Arrays.fill(r, -1);
        bfs(grid, distToThief, n);

        // 2. Dijkstra to find Maximum Safeness Path
        // Max-Heap: We want to explore paths with HIGHER safeness first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Instead of 'vis', we use this array to store the best safeness found so far
        int[][] maxSafeness = new int[n][n];
        for(int[] r : maxSafeness) Arrays.fill(r, -1);

        // Initialize start
        maxSafeness[0][0] = distToThief[0][0];
        pq.add(new int[] {distToThief[0][0], 0, 0}); // safe_score, x, y

        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        while(!pq.isEmpty()) {
            int[] curr = pq.remove();
            int currSafe = curr[0];
            int x = curr[1], y = curr[2];

            // If we found a path to end, this is guaranteed to be the best 
            // because of Max-Heap (similar to standard Dijkstra)
            if(x == n - 1 && y == n - 1) return currSafe;

            // Optimization: If we already found a safer way to this cell, skip
            if(currSafe < maxSafeness[x][y]) continue;

            for(int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];

                if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    // The safeness of the new path is the MIN of current path and the new cell
                    int newSafe = Math.min(currSafe, distToThief[nx][ny]);

                    // RELAXATION STEP:
                    // If this new path is SAFER than what we previously recorded for (nx, ny)
                    if(newSafe > maxSafeness[nx][ny]) {
                        maxSafeness[nx][ny] = newSafe;
                        pq.add(new int[] {newSafe, nx, ny});
                    }
                }
            }
        }

        return -1;
    }

    // Standard BFS (Same as before)
    private static void bfs(List<List<Integer>> grid, int[][] dist, int n) {
        Queue<int[]> q = new LinkedList<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.add(new int[] {i, j});
                }
            }
        } 

        while(!q.isEmpty()) {
            int[] curr = q.remove();
            int x = curr[0], y = curr[1];
            int d = dist[x][y];

            for(int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];

                if(nx >= 0 && nx < n && ny >= 0 && ny < n && dist[nx][ny] == -1) {
                    dist[nx][ny] = d + 1;
                    q.add(new int[] {nx, ny});
                }
            }
        }
    }
}
