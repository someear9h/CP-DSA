public class FloydWarshall {
    public void floydWarshall(int[][] dist) {
        // Code here
        int n = dist.length;
        int m = dist[0].length;
        
        
        // k -> path nodes through which we reach from i to j
        for(int k = 0; k < n; k++) {
            for(int i = 0;i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(dist[i][k] == (int)1e8 || dist[k][j] == (int)1e8) {
                        continue;
                    }
                    // formula
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        // check for negative cycles
        // if i == j cell becomes negative then cycle
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i == j) {
                    if(dist[i][j] < 0) {
                        // negative cycle
                        return;
                    }
                }
            }
        }
    }
}
