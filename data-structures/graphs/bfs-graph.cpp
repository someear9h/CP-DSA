#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution {
public:
    vector<int> bfsOfGraph(int V, vector<vector<int>>& adj) {
        vector<int> vis(V, 0);
        vis[0] = 1;

        queue<int> q;
        q.push(0);
        vector<int> bfs;

        while(!q.empty()) {
            int node = q.front();
            q.pop();
            bfs.push_back(node);

            for(auto neighbour : adj[node]) {
                if(!vis[neighbour]) {
                    vis[neighbour] = 1;
                    q.push(neighbour);
                }
            }
        }

        return bfs;
    }
};

int main() {
    Solution sol;
    int V, E;  // V = number of vertices, E = number of edges
    cout << "Enter number of vertices and edges: ";
    cin >> V >> E;

    vector<vector<int>> adj(V);  // Adjacency list (vector of vectors)

    cout << "Enter edges (u v) for " << E << " edges:" << endl;
    for (int i = 0; i < E; ++i) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);  // For undirected graph (remove if directed)
    }

    vector<int> bfs = sol.bfsOfGraph(V, adj);

    cout << "BFS Traversal: ";
    for (int node : bfs) {
        cout << node << " ";
    }
    cout << endl;

    return 0;
}