/**
* someear1h
*/

#include <bits/stdc++.h>
using namespace std;

class DisjointSet {
    vector<int> rank, parent, size;

public:
    DisjointSet(int n) {
        rank.resize(n+1, 0);
        parent.resize(n+1);
        size.resize(n+1);

        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i]=1;
        }
    }

    int findUPar(int node) {
        if(parent[node] == node) {
            return parent[node];
        }

        return parent[node] = findUPar(parent[node]);
    }

    void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);

        if(ulp_v == ulp_u) {
            return;
        }

        if(rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        } else if(rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u;
        } else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }

    void unionSize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);

        if(ulp_v == ulp_u) {
            return;
        }

        if(size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        } else if(size[ulp_v] < size[ulp_u]) {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
};

int main() {
    
    DisjointSet ds(3);
    ds.unionSize(1, 2);
    
    if(ds.findUPar(1) == ds.findUPar(3)) {
        cout << "SAME\n";
    } else {
        cout << "Not same\n";
    }
    
    ds.unionSize(2, 3);
    ds.unionSize(1, 3);

    if(ds.findUPar(1) == ds.findUPar(3)) {
        cout << "SAME\n";
    } else {
        cout << "Not same\n";
    }

    cout << "DisjointSet done successfully\n";

    return 0;
}