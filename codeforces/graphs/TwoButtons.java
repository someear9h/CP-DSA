import java.io.*;
import java.util.*;

public class TwoButtons {
    private static int solve(int n, int m) {
        Queue<Pair> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<>();
        
        q.add(new Pair(n, 0));
        vis.add(n);
        
        while(!q.isEmpty()) {
            Pair p = q.remove();
            int num = p.first;
            int steps = p.second;
            
            if(num == m) return steps;
            
            // red light
            if(num * 2 <= m * 2 && !vis.contains(num * 2)) {
                q.add(new Pair(num * 2, steps + 1));
                vis.add(num * 2);
            }
            
            // blue light
            if(num - 1 >= 0 && !vis.contains(num - 1)) {
                vis.add(num - 1);
                q.add(new Pair(num - 1, steps + 1));
            }
        }
        return -1;
    }
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        
        out.println(solve(n, m));
        
        out.flush();
    }
}

class Pair {
    int first;
    int second;

    Pair(int f, int s) {
        this.first = f;
        this.second = s;
    }
}