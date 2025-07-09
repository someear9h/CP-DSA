import java.io.*;
import java.util.*;

public class ShortestPathinBinaryMatrix {

    public static int findShortestPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[m][n];

        if(grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return -1;
        
        q.offer(new int[] {0, 0});
        vis[0][0] = true;

        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {1, -1}, {1, 1}, {1, 0}, {0, -1}, {0, 1}};

        while(!q.isEmpty()) {
            ans++;
            int size = q.size();

            for(int i = 0; i < size; i++) {
                int x = q.peek()[0];
                int y = q.peek()[1];
                q.remove();

                if(x == m - 1 && y == n - 1) {
                    return ans;
                }

                // explore directions
                for(int[] d : dirs) {
                    int newx = x + d[0];
                    int newy = y + d[1];

                    if(newx < 0 || newx >= m || newy < 0 || newy >= n || vis[newx][newy] || grid[newx][newy] == 1) {
                        continue;
                    }

                    q.add(new int[] {newx, newy});
                    vis[newx][newy] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        int[][] grid = {{0,0,0}, {1,1,0}, {1, 1, 0}};

        System.out.println(findShortestPath(grid));
    }
}
