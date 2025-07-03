import java.util.Arrays;

public class GraphBipartite {
    private boolean isBipartite(int[][] graph) {
        int[] vis = new int[graph.length];
        Arrays.fill(vis, -1);

        for(int i = 0; i < graph.length; i++) {
            if(vis[i] == -1) {
                if(dfs(i, 0, vis, graph) == false) return false;
            }
        }
        return true;
    }

    private boolean dfs(int node, int color, int[] vis, int[][] adj) {
        vis[node] = color; // color the node

        // go through adj node
        for(int it : adj[node]) {
            if(vis[it] == -1) {
                // if node not visited then color it with opposite number
                if(dfs(it, 1 - color, vis, adj) == false) return false;
            } else if(vis[it] == color) {
                // adjacent nodes have the same color
                return false;
            } 
        }
        return true;
    }

    public static void main(String[] args) {
        GraphBipartite obj = new GraphBipartite();
        int[][] graph = {{1,2,3}, {0, 2}, {0, 1, 3}, {0, 2}};

        System.out.println(obj.isBipartite(graph)? "True" : "False");
    }
}
