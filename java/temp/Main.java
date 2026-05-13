package  temp;

import java.util.*;

public class Main {

    public static int[] dijkstra(int V, int[][] edges, int src) {
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            adj.get(u).add(new int[] {v, wt});
            adj.get(v).add(new int[] {u, wt});
        }

        int[] dist = new int[V];
        Arrays.fill(dist, 1000005);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] {0, src});

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int dis = curr[0];
            int node = curr[1];

            if(dist[node] < dis) continue;

            for(int[] nei : adj.get(node)) {
                int v = nei[0];
                int w = nei[1];

                if(dist[node] + w < dist[v]) {
                    dist[v] = dist[node] + w;
                    pq.offer(new int[] {dist[v], v});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 3;
        int[][] edges = {{0, 1, 1}, {1, 2, 3}, {0, 2, 6}};
        int src = 2;

        int[] res = dijkstra(V, edges, src);

        for(int r : res)
            System.out.print(r + " ");
    }
}