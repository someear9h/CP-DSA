import java.util.*;

class Solution {
    private static void dfs(int node, boolean[] vis, Stack<Integer> st, List<List<Integer>> adj) {
        vis[node] = true;

        for(int adjNode : adj.get(node)) {
            if(!vis[adjNode]) {
                dfs(adjNode, vis, st, adj);
            }
        }

        st.push(node);
    }


    public static int[] topoSort(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
        }

        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                dfs(i, vis, st, adj);
            }
        }

        int[] ans = new int[V];
        for(int i = 0; i < V; i++) {
            ans[i] = st.pop();
        }

        return ans;
    }

    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {{3, 0}, {1, 0}, {2, 0}};

        int[] ans = topoSort(V, edges);
        for(int a : ans) {
            System.out.print(a + " ");
        }
    }
}