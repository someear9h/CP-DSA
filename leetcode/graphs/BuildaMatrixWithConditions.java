import java.util.*;

public class BuildaMatrixWithConditions {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        // get topo sort of row and column conditions
        List<Integer> rowTopo = topoSort(rowConditions, k);
        List<Integer> colTopo = topoSort(colConditions, k);

        if(rowTopo.isEmpty() || colTopo.isEmpty()) return new int[0][0];

        int[][] ans = new int[k][k];

        Map<Integer, Integer> rowMap = new HashMap<>();
        Map<Integer, Integer> colMap = new HashMap<>();

        for(int i = 0; i < k; i++) rowMap.put(rowTopo.get(i), i); // store index for rows
        for(int i = 0; i < k; i++) colMap.put(colTopo.get(i), i); // index for cols

        for(int num = 1; num <= k; num++) {
            int i = rowMap.get(num);
            int j = colMap.get(num);
            ans[i][j] = num;
        }

        return ans;
    }

    private List<Integer> topoSort(int[][] edges, int k) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= k; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
        }

        List<Integer> order = new ArrayList<>();
        boolean[] vis = new boolean[k + 1];
        boolean[] visPath = new boolean[k + 1];

        for(int i = 1; i <= k; i++) {
            if(!vis[i]) {
                if(dfs(i, vis, visPath, adj, order)) {
                    return new ArrayList<>(); // cycle detected
                }
            }
        }

        Collections.reverse(order);
        return order;
    }

    private boolean dfs(int node, boolean[] vis, boolean[] visPath, 
                        List<List<Integer>> adj, List<Integer> order) {
        vis[node] = true;
        visPath[node] = true;

        for(int nei : adj.get(node)) {
            if(!vis[nei]) {
                if(dfs(nei, vis, visPath, adj, order)) return true;
            } else if(visPath[nei]) {
                return true; // cycle detected
            }
        }

        visPath[node] = false;
        order.add(node);
        return false;
    }

    
}
