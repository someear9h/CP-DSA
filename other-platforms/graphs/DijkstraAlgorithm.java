import java.io.*;
import java.util.*;

class Pair {
    int node;
    int wt;
    
    Pair(int node, int wt) {
        this.node = node;
        this.wt = wt;
    }
}

public class DijkstraAlgorithm {
    private static int[] dijkstra(int V, int[][] edges, int src) {
        TreeSet<int[]> set = new TreeSet<>((a, b) -> {
            if(a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        
        List<List<Pair>> adj = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] e : edges) {
            int u = e[0], v = e[1], wt = e[2];    
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt)); 
        }
        
        
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e9);
        dist[src] = 0;
        
        set.add(new int[] {0, src}); // distance and node
        
        while(!set.isEmpty()) {
            int[] top = set.pollFirst();
            int dis = top[0];
            int node = top[1];
            
            for(Pair p : adj.get(node)) {
                int v = p.node;
                int weight = p.wt;
                
                if(dis + weight < dist[v]) {
                    if(dist[v] != (int)1e9) {
                        set.remove(new int[] {dist[v], v});
                    }
                    
                    dist[v] = dis + weight;
                    set.add(new int[] {dist[v], v});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        int V = 3;
        int[][] edges = {{0, 1, 1}, {1, 2, 3}, {0, 2, 6}};
        int src = 2;

        int[] ans = dijkstra(V, edges, src);

        for(int a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
