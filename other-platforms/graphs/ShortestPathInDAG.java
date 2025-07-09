import java.io.*;
import java.util.*;

class Pair {
    int first;
    int second;
    
    Pair(int f, int s) {
        this.first = f;
        this.second = s;
    }
}

public class ShortestPathInDAG {
    private static int[] shortestPath(int V, int E, int[][] edges) {
        List<List<Pair>> adj = new ArrayList<>();
        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] e : edges) {
            int u = e[0], v = e[1], wt = e[2];
            adj.get(u).add(new Pair(v, wt));
        }
        
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                topoSort(i, adj, st, vis);
            }
        }
        
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e9);
        dist[0] = 0;
        
        while(!st.isEmpty()) {
            int node = st.peek();
            st.pop();
            
            for(Pair it : adj.get(node)) {
                int v = it.first;
                int wt = it.second;
                
                if(dist[node] + wt < dist[v]) {
                    dist[v] = dist[node] + wt;
                }
            }
        }
        
        for(int i = 0; i < V; i++) {
            if(dist[i] == (int)1e9) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    private static void topoSort(int node, List<List<Pair>> adj, Stack<Integer> st, boolean[] vis) {
        vis[node] = true;
        
        for(Pair it : adj.get(node)) {
            int v = it.first;
            if(!vis[v]) {
                topoSort(v, adj, st, vis);
            }
        }
        st.push(node);
    }

    public static void main(String[] args) throws IOException {
        
        int n = 6, m = 7;
        int[][] edge = {{0,1,2},{0,4,1},{4,5,4},{4,2,2},{1,2,3},{2,3,6},{5,3,1}};
        int res[] = shortestPath(n, m, edge);
        
        for (int i = 0; i < n; i++) {
        System.out.print(res[i] + " ");
        }
        
        System.out.println();
    }
}
