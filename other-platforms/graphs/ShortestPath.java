import java.util.*;

public class ShortestPath {
    private static int[] findShortestDistance(int[][] edges, int n, int m, int src) {
        // build an adj list
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[n];
        for(int i = 0; i < n; i++) {
            dist[i] = (int)1e9;
        }

        dist[src] = 0; // distance from src node to src node is 0 ofc
        q.add(src);

        while(!q.isEmpty()) {
            int node = q.peek();
            q.remove();

            for(int it : adj.get(node)) {
                if(dist[node] + 1 < dist[it]) {
                    dist[it] = dist[node] + 1;
                    q.add(it);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            if(dist[i] == (int)1e9) {
                dist[i] = -1;
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int n = 9, m = 10;
        int src = 0;
        int[][] edges = {{0,1},{0,3},{3,4},{4,5},{5,6},{1,2},{2,6},{6,7},{7,8},{6,8}};

        int[] ans = findShortestDistance(edges, n, m, src);

        for(int a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
