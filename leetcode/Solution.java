class DisjointSetUnion {
    int[] parent;
    int[] rank;
    int[] size;
    
    DisjointSetUnion(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        size = new int[n + 1];
        
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }
    
    // function to get the ultimate Parent of a node
    public int findUPar(int node) {
        if(node == parent[node]) return node;
        
        int ulp = findUPar(parent[node]);
        parent[node] = ulp;
        return parent[node];
    }

    // function to unionise nodes using rank
    public void unionByRank(int u, int v) {
        int up = findUPar(u);
        int vp = findUPar(v);

        if(up == vp) return; // same parent, already unionised

        if(rank[up] < rank[vp]) {
            parent[up] = vp;
        } else if(rank[vp] < rank[up]) {
            parent[vp] = up;
        } else {
            // if they same same rank then, any node can be a parent of any node
            parent[up] = vp;
            rank[vp] ++;
        }
    }

    public void unionBySize(int u, int v) {
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
}

public class Solution {
    public static void main(String[] args) {
        DisjointSetUnion ds = new DisjointSetUnion(7);

        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);

        if(ds.findUPar(3) == ds.findUPar(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not same");
        }


        ds.unionBySize(3, 7);
        System.out.println("After Union By Rank");

        if(ds.findUPar(3) == ds.findUPar(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not same");
        }
    }
}