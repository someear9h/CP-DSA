import java.util.PriorityQueue;

public class SwiminRisingWater {
    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        boolean[][] vis = new boolean[n][n];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        pq.add(new int[] {grid[0][0], 0, 0});

        while(!pq.isEmpty()) {
            int[] curr = pq.remove();
            int time = curr[0], r = curr[1], c = curr[2];

            // return time if we have reached the destination
            if(r == n - 1 && c == n - 1) return time;

            if(vis[r][c]) continue;

            vis[r][c] = true;

            // explore 4 directions
            for(int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                boolean inBounds = nr < n && nr >= 0 && nc < n && nc >= 0;
                
                // if nr and nc is in bounds and not visited
                if(inBounds && !vis[nr][nc]) {
                    pq.add(new int[] {Math.max(time, grid[nr][nc]), nr, nc});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0,1,2,3,4},
            {24,23,22,21,5},
            {12,13,14,15,16},
            {11,17,18,19,20},
            {10,9,8,7,6}
        };

        System.out.println(swimInWater(grid));
    }
}
