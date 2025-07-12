class DisjointSet {
    int[] parent;
    int[] size;

    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findUPar(int node) {
        if (node == parent[node]) return node;
        return parent[node] = findUPar(parent[node]);
    }

    public void unionBySize(int u, int v) {
        int up = findUPar(u);
        int vp = findUPar(v);

        if (up == vp) return;

        if (size[up] < size[vp]) {
            parent[up] = vp;
            size[vp] += size[up];
        } else {
            parent[vp] = up;
            size[up] += size[vp];
        }
    }
}

public class NumberofOperationstoMakeNetworkConnected {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1; // Not enough cables to connect all

        DisjointSet dsu = new DisjointSet(n);
        int extraEdge = 0;

        for (int[] c : connections) {
            int u = c[0], v = c[1];
            if (dsu.findUPar(u) == dsu.findUPar(v)) {
                extraEdge++; // Redundant edge
            } else {
                dsu.unionBySize(u, v);
            }
        }

        int components = 0;
        for (int i = 0; i < n; i++) {
            if (dsu.parent[i] == i) components++; // Count root parents (unique components)
        }

        int operationsNeeded = components - 1;

        return (extraEdge >= operationsNeeded) ? operationsNeeded : -1;
    }

    public static void main(String[] args) {
        NumberofOperationstoMakeNetworkConnected sol = new NumberofOperationstoMakeNetworkConnected();

        int n1 = 4;
        int[][] con1 = {{0,1},{0,2},{1,2}};
        System.out.println("Output: " + sol.makeConnected(n1, con1)); // Output: 1

        int n2 = 6;
        int[][] con2 = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        System.out.println("Output: " + sol.makeConnected(n2, con2)); // Output: 2

        int n3 = 6;
        int[][] con3 = {{0,1},{0,2},{0,3},{1,2}};
        System.out.println("Output: " + sol.makeConnected(n3, con3)); // Output: -1
    }
}