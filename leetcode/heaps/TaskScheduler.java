import java.util.*;

public class TaskScheduler {
    public static int leastInterval(char[] tasks, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int[] freq = new int[26];
        
        for(char c : tasks) {
            freq[c - 'A']++;
        }

        for(int f : freq) {
            if(f > 0) pq.add(f);
        }

        int time = 0;

        // while all tasks are not completed
        while(!pq.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int slots = n + 1;

            while(slots > 0) {

                if(!pq.isEmpty()) {
                    int task = pq.remove();
                    task--;

                    if(task > 0) temp.add(task);
                }
                time++;
                slots--;

                if(pq.isEmpty() && temp.isEmpty()) break;
            }

            for(int i : temp) {
                pq.add(i);
            }
        }

        return time;
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        System.out.println(leastInterval(tasks, n));
    }
}
