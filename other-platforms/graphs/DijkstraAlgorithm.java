import java.io.*;
import java.util.*;



public class DijkstraAlgorithm {
    static class Pair {
        int node;
        int wt;
        
        Pair(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }


    private static int[] dijkstra(int V, int[][] edges, int src) {
        List<List<Pair>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            int u = e[0], v = e[1], wt = e[2];
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.wt, b.wt));
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e9);
        dist[src] = 0;

        pq.add(new Pair(src, 0));

        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            int currNode = curr.node;
            int currWt = curr.wt;

            if(currWt > dist[currNode]) continue;

            for(Pair p : adj.get(currNode)) {
                int adjNode = p.node, adjWt = p.wt;

                if(currWt + adjWt < dist[adjNode]) {
                    dist[adjNode] = currWt + adjWt;
                    pq.add(new Pair(adjNode, dist[adjNode]));
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