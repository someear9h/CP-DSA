#include <iostream>
#include <vector>
using namespace std;

class Solution {
private:
    void dfs(int node, vector<vector<int>>& adj, vector<int>& vis, vector<int>& res) {
        vis[node] = 1; // mark as node visited 
        res.push_back(node); // store the node 

        for(auto neighbour : adj[node]) { 
            if(!vis[neighbour]) {
                dfs(neighbour, adj, vis, res);
            }
        }   
    }

public:
    vector<int> dfsOfGraph(int V, vector<vector<int>>& adj) {
        vector<int> vis(V, 0);
        vector<int> res;
        int start = 0;

        dfs(start, adj, vis, res);
        return res;
    }
};

int main() {
    Solution sol;
    int V = 5;

    vector<vector<int>> adj = {
        {1, 2},    // Node 0
        {0, 3, 4}, // Node 1
        {0},       // Node 2
        {1},       // Node 3
        {1}        // Node 4
    };

    vector<int> traversal = sol.dfsOfGraph(V, adj);

    for(auto it : traversal) {
        cout << it << " "; // o/p: 0 1 3 4 2 
    }
    return 0;
}