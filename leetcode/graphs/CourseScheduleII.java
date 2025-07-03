import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CourseScheduleII {
    private static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> topo = new ArrayList<>();
        int[] vis = new int[numCourses];
        int[] visPath = new int[numCourses];

        // build the adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] pre : prerequisites) {
            int u = pre[1], v = pre[0];
            adj.get(u).add(v);
        }

        for(int i = 0; i < numCourses; i++) {
            if(vis[i] == 0) {
                if(dfsCheck(i, vis, visPath, adj, topo)) {
                    return new int[0];
                }
            }
        }

        Collections.reverse(topo);
        return topo.stream().mapToInt(i -> i).toArray();
    }

    private static boolean dfsCheck(int node, int[] vis, int[] visPath, ArrayList<ArrayList<Integer>> adj, List<Integer> topo) {
        vis[node] = 1; // mark visited
        visPath[node] = 1;

        for(int it : adj.get(node)) {
            if(vis[it] == 0) {
                if(dfsCheck(it, vis, visPath, adj, topo)) {
                    return true;
                }
            } else if(visPath[it] == 1) {
                return true;
            }
        }

        visPath[node] = 0; // reset for next path
        topo.add(node);
        return false;
    }

    public static void main(String[] args) {

        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};

        int[] order = findOrder(numCourses, prerequisites);

        System.out.println(Arrays.toString(order));
    }
}
