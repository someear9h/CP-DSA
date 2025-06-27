## **general DFS template for graph problems** 

### **Generic DFS Graph Template**
```cpp
#include <vector>
#include <unordered_set> // If node labels aren't integers

class GraphSolution {
private:
    // ======== CORE DFS TEMPLATE ========
    void dfs(int node, 
            const vector<vector<int>>& graph, 
            vector<bool>& visited,
            /* Additional tracking parameters as needed */) {
        
        visited[node] = true;
        
        // === Pre-processing (e.g., track entry time, low link, etc.) ===
        
        for (int neighbor : graph[node]) {  // For adjacency list
        // for (int neighbor = 0; neighbor < graph.size(); neighbor++) {  // For adjacency matrix
            // if (graph[node][neighbor] == 1) {  // Use with adjacency matrix
            
            if (!visited[neighbor]) {
                // === Problem-specific checks before recursion ===
                dfs(neighbor, graph, visited, /*...*/);
            }
            
            // === Problem-specific checks after recursion ===
            // (e.g., cycle detection, low link updates)
        }
        
        // === Post-processing (e.g., topological sort ordering) ===
    }

public:
    int solveProblem(const vector<vector<int>>& graph) {
        int n = graph.size();
        vector<bool> visited(n, false);
        // vector<int> parent(n, -1);       // For cycle detection
        // vector<int> entryTime(n), low(n); // For articulation points
        // stack<int> order;                // For topological sort
        
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // === Problem-specific initialization ===
                result++;  // For connected components
                dfs(i, graph, visited, /*...*/);
            }
        }
        
        // return order;  // For topological sort
        return result;
    }
};
```

---

### **How to Adapt for Common Problems**
#### **1. Connected Components (Current Problem)**
```cpp
void dfs(int node, const vector<vector<int>>& graph, vector<bool>& visited) {
    visited[node] = true;
    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            dfs(neighbor, graph, visited);
        }
    }
}
```

#### **2. Cycle Detection in Undirected Graphs**
```cpp
bool dfs(int node, int parent, const vector<vector<int>>& graph, vector<bool>& visited) {
    visited[node] = true;
    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            if (dfs(neighbor, node, graph, visited)) return true;
        } else if (neighbor != parent) {
            return true; // Cycle detected
        }
    }
    return false;
}
```

#### **3. Topological Sort (DAGs)**
```cpp
void dfs(int node, const vector<vector<int>>& graph, vector<bool>& visited, stack<int>& order) {
    visited[node] = true;
    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            dfs(neighbor, graph, visited, order);
        }
    }
    order.push(node); // Post-order
}
```

#### **4. Bipartite Check**
```cpp
bool dfs(int node, int color, const vector<vector<int>>& graph, vector<int>& colors) {
    colors[node] = color;
    for (int neighbor : graph[node]) {
        if (colors[neighbor] == -1) {
            if (!dfs(neighbor, 1 - color, graph, colors)) return false;
        } else if (colors[neighbor] == color) {
            return false; // Not bipartite
        }
    }
    return true;
}
```

---

### **Key Features of the Template**
1. **Flexible Input Types**:
   - Works with both adjacency lists (`vector<vector<int>>`) and matrices.
2. **Modular Design**:
   - Add/remove tracking parameters (`parent`, `entryTime`, etc.) as needed.
3. **Handles Directed/Undirected Graphs**:
   - For undirected graphs, avoid revisiting parents (cycle detection).
4. **Time Complexity**:
   - **O(V + E)** for adjacency lists, **O(V²)** for matrices.

---

### **When to Use This Template**
- Counting connected components (provinces).
- Detecting cycles.
- Topological sorting.
- Finding articulation points/bridges.
- Bipartite graph checks.
- Solving maze/path problems.

**Tip**: Bookmark this template and adjust the `// === Problem-specific ===` sections for each new graph problem!