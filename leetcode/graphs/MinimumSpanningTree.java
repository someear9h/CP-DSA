import java.util.*;

class Pair {
    int first;
    int second;
    
    Pair(int f, int s) {
        this.first = f;
        this.second = s;
    }
}

public class MinimumSpanningTree {
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.first, b.first));
        pq.add(new Pair(0, 0)); // wt, node
        
        int sum = 0;
        boolean[] vis = new boolean[V];
        Arrays.fill(vis, false); // not visited any node 
        
        while(!pq.isEmpty()) {
            int wt = pq.peek().first;
            int node = pq.peek().second;
            pq.remove();
            
            if(vis[node]) continue;
            
            vis[node] = true;
            sum += wt;
            
            // go through adjNodes of node
            for(int[] a : adj.get(node)) {
                int adjNode = a[0];
                int edW = a[1];
                
                if(!vis[adjNode]) { // if adjNode is not visited
                    pq.add(new Pair(edW, adjNode));
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int V = 5;
        int E = 6;
        
        // Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Undirected edges (u, v, wt)
        int[][] edges = {
            {0, 1, 2},
            {0, 3, 6},
            {1, 2, 3},
            {1, 3, 8},
            {1, 4, 5},
            {2, 4, 7}
        };

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt}); // Undirected
        }

        int mstWeight = spanningTree(V, E, adj);
        System.out.println("Minimum Spanning Tree Total Weight: " + mstWeight);
    }
}