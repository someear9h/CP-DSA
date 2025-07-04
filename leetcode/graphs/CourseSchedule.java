import java.util.ArrayList;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // build an adj list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] p : prerequisites) {
            int u = p[0], v = p[1];
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[numCourses];
        boolean[] visPath = new boolean[numCourses];

        for(int i = 0; i < numCourses; i++) {
            if(!vis[i]) {
                if(dfsCheck(i, vis, visPath, adj)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean dfsCheck(int node, boolean[] vis, boolean[] visPath, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        visPath[node] = true;

        for(int it : adj.get(node)) {
            if(!vis[it]) { // if the node is not visited
                if(dfsCheck(it, vis, visPath, adj)) {
                    return true;
                }
            } else if(visPath[it]) { // if node is visited and also visited during the same path
                return true; // a cycle exists here
            }
        }
        visPath[node] = false; // go through next path now
        return false;
    }

    public static void main(String[] args) {

        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};

        System.out.println((canFinish(numCourses, prerequisites)) ? "True" : "false");
    }
}
