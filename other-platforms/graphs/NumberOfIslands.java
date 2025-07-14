import java.util.*;

class DisjointSet {
    int[] parent;
    int[] size;

    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int findUPar(int node) {
        if(parent[node] == node) return node;
        return parent[node] = findUPar(parent[node]);
    }

    void unionBySize(int u, int v) {
        int up = findUPar(u), vp = findUPar(v);
        if(up == vp) return;

        if(size[up] < size[vp]) {
            parent[up] = vp;
            size[vp] += size[up];
        } else {
            parent[vp] = up;
            size[up] += size[vp];
        }
    }
}

public class NumberOfIslands {

    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        
        DisjointSet dsu = new DisjointSet(rows * cols);
        List<Integer> ans = new ArrayList<>();
        int[][] vis = new int[rows][cols];
        int cnt = 0;
        
        for (int[] op : operators) {
            int r = op[0];
            int c = op[1];

            if(vis[r][c] == 1) {
                // already visited
                ans.add(cnt);
                continue;
            }

            vis[r][c] = 1; // mark visited
            cnt++; // separate island
            
            // explore 4 directions
            int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
            for(int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                boolean inBounds = nr < rows && nr >= 0 && nc < cols && nc >= 0;
                if(inBounds && vis[nr][nc] == 1) {
                    
                    int nodeNo = r * cols + c;
                    int adjNo = nr * cols + nc;
                    
                    if(dsu.findUPar(nodeNo) != dsu.findUPar(adjNo)) {
                        cnt--;
                        dsu.unionBySize(nodeNo, adjNo);
                    }
                    
                }
            }
            ans.add(cnt);
        }
        
        return ans;
    }

    public static void main(String[] args) {
        NumberOfIslands sol = new NumberOfIslands();
        
        int rows = 4, cols = 5;
        int[][] operators = {
            {1, 1}, {0, 1}, {3, 3}, {3, 4}
        };

        List<Integer> result = sol.numOfIslands(rows, cols, operators);
        System.out.println("Number of islands after each operation: " + result);
    }
}