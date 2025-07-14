import java.util.*;

public class MakingALargeIsland {
    HashMap<Integer, Integer> map;
    int maxSize;
    int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // left, down, right, up

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        map = new HashMap<>();
        maxSize = 0;
        int id = 2;

        // Step 1: Label all islands with unique IDs and record their sizes
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(i, j, id, grid);
                    map.put(id, size);
                    maxSize = Math.max(maxSize, size);
                    id++;
                }
            }
        }

        // Step 2: Try flipping each 0 to 1 and compute resulting island size
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    int newSize = 1;

                    for (int[] dir : dirs) {
                        int ni = i + dir[0], nj = j + dir[1];
                        if (inBounds(ni, nj, n) && grid[ni][nj] > 1) {
                            int islandId = grid[ni][nj];
                            if (seen.add(islandId)) {
                                newSize += map.get(islandId);
                            }
                        }
                    }

                    maxSize = Math.max(maxSize, newSize);
                }
            }
        }

        return maxSize;
    }

    // DFS to label island and return its size
    private int dfs(int i, int j, int id, int[][] grid) {
        int n = grid.length;
        if (!inBounds(i, j, n) || grid[i][j] != 1) return 0;

        grid[i][j] = id;
        int size = 1;

        for (int[] dir : dirs) {
            size += dfs(i + dir[0], j + dir[1], id, grid);
        }

        return size;
    }

    // Bounds check
    private boolean inBounds(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    public static void main (String[] args) {
        int[][] grid = {
            {1, 1, 0, 1, 1, 0}, {1, 1, 0, 1, 1, 0},
            {1, 1, 0, 1, 1, 0}, {0, 0, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 0}
        };

        MakingALargeIsland obj = new MakingALargeIsland();
        int ans = obj.largestIsland(grid);
        System.out.println("The largest group of connected 1s is of size: " + ans);
    }
}
