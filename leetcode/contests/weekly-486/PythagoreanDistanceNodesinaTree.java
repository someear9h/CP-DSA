import java.util.*;

public class PythagoreanDistanceNodesinaTree {
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // get the distances of all nodes from x, y, z 
        int[] distX = bfs(adj, x, n);
        int[] distY = bfs(adj, y, n);
        int[] distZ = bfs(adj, z, n);

        int res = 0;
        for(int i = 0; i < n; i++) {
            // i is the node
            // p[i] = distance of ith node from p
            int a = distX[i], b = distY[i], c = distZ[i];

            int[] arr = new int[3];
            arr[0] = a; arr[1] = b; arr[2] = c;

            Arrays.sort(arr);
            a = arr[0];
            b = arr[1];
            c = arr[2];

            if((long) (a * a) + (long) (b * b) == (long) (c * c)) {
                res++;
            }
        }
        
        return res;
    }

    private int[] bfs(List<List<Integer>> adj, int start, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[start] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()) {
            int u = q.remove();

            for(int v : adj.get(u)) {
                if(dist[v] == -1) {
                    dist[v] = dist[u]+1;
                    q.add(v);
                }
            }
        }

        return dist;
    }
}

