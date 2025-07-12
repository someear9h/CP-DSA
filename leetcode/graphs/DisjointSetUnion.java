public class DisjointSetUnion {
    
    int[] rank;
    int[] parent;
    int[] size;

    DisjointSetUnion(int n) {
        rank = new int[n];
        parent = new int[n];
        size = new int[n];

        for(int i = 0; i < n; i++) {
            rank[i] = 0;
            parent[i] = i; // own parent at start
            size[i] = 1;
        }
    }

    private int findUPar(int node) {
        if(node == parent[node]) return node;

        int ulp = findUPar(parent[node]);
        return parent[node] = ulp;
    }
    

    private void unionByRank(int u, int v) {
        int up = findUPar(u);
        int vp = findUPar(v);

        if(up == vp) return; // u and v already belong to the same set (same ultimate parent).

        if(rank[up] < rank[vp]) {
            parent[up] = vp;
        } else if(rank[vp] < rank[up]) {
            parent[vp] = up;
        } else { // same rank of parents
            parent[vp] = up;
            int rankU = rank[up];
            rank[up] = rankU + 1;
        }
    }

    private void unionBySize(int u, int v) {
        int up = findUPar(u);
        int vp = findUPar(v);

        if(up == vp) return;

        if(size[up] < size[vp]) {
            parent[up] = vp;
            size[vp] = size[vp] + size[up];
        } else {
            parent[vp] = up;
            size[up] = size[up] + size[vp];
        }
    }

    public static void main(String[] args) {
        int n = 7; // 0 to 6
        DisjointSetUnion dsu = new DisjointSetUnion(n);

        // union some sets
        dsu.unionByRank(0, 1);
        dsu.unionByRank(1, 2);
        dsu.unionByRank(3, 4);
        dsu.unionByRank(5, 6);
        dsu.unionByRank(4, 5);

        // check if 2 and 3 are in the same component
        if(dsu.findUPar(2) == dsu.findUPar(3)) {
            System.out.println("Same component");
        } else {
            System.out.println("Not in same component");
        }

        // union 2 and 3
        dsu.unionByRank(2, 3);

        // now check again
        if(dsu.findUPar(2) == dsu.findUPar(3)) {
            System.out.println("Same component");
        } else {
            System.out.println("Not in same component");
        }

        // Union 2 and 3 using unionBySize
        dsu.unionBySize(2, 3);

        // Now check again
        if(dsu.findUPar(2) == dsu.findUPar(3)) {
            System.out.println("Same component");
        } else {
            System.out.println("Not in same component");
        }

        // Print final parent and size arrays for visualization
        System.out.println("Final parents:");
        for (int i = 0; i < n; i++) {
            System.out.println(i + " -> " + dsu.findUPar(i));
        }

        System.out.println("Final sizes:");
        for (int i = 0; i < n; i++) {
            System.out.println("Component rooted at " + i + " has size " + dsu.size[i]);
        }
    }
}
