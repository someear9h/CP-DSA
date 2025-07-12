import java.io.*;
import java.util.*;

class DisjointSet {
    int[] parent;
    int[] size;

    DisjointSet(int n) {
        parent = new int[n + 1];
        size = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int findUPar(int node) {
        if(parent[node] == node) return node;
        return parent[node] = findUPar(parent[node]); // path compression
    }

    void unionBySize(int u, int v) {
        int up = findUPar(u);
        int vp = findUPar(v);

        if(up == vp) return;

        if(size[up] < size[vp]) {
            parent[up] = vp;
            size[vp] += size[up];
        } else {
            parent[vp] = up;
            size[up] += size[vp];
        }
    }
}

public class MostStonesRemovedwithSameRoworColumn {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        int maxRow = 0;
        int maxCol = 0;

        for(int[] stone : stones) {
            maxRow = Math.max(maxRow, stone[0]);
            maxCol = Math.max(maxCol, stone[1]);
        }

        DisjointSet dsu = new DisjointSet(maxRow + maxCol + 1);
        HashSet<Integer> nodesUsed = new HashSet<>();

        for(int[] stone : stones) {
            int rowNode = stone[0];
            int colNode = stone[1] + maxRow + 1;

            dsu.unionBySize(rowNode, colNode);

            nodesUsed.add(rowNode);
            nodesUsed.add(colNode);
        }

        int components = 0;
        for(int node : nodesUsed) {
            if(dsu.findUPar(node) == node) {
                components++;
            }
        }

        return n - components;
    }

    public static void main(String[] args) throws IOException {
        MostStonesRemovedwithSameRoworColumn sol = new MostStonesRemovedwithSameRoworColumn();

        int[][] stones = {{0,0}, {0,1}, {1,0}, {1,2}, {2,1}, {2,2}};

        System.out.println(sol.removeStones(stones));
    }
}
