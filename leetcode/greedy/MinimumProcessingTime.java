import java.util.*;

public class MinimumProcessingTime {
    private int solve(List<Integer> processorTime, List<Integer> tasks) {
        int n = tasks.size();

        Collections.sort(processorTime);
        Collections.sort(tasks);

        int ans = 0;
        int j = n - 1;

        for(int pt : processorTime) {
            for(int c = 0; c < 4; c++) {
                int totalTime = pt + tasks.get(j);
                ans = Math.max(ans, totalTime);
                j--;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        List<Integer> processorTime = new ArrayList<>(Arrays.asList(8, 10));
        List<Integer> tasks = new ArrayList<>(Arrays.asList(2,2,3,1,8,7,4,5));

        MinimumProcessingTime sol = new MinimumProcessingTime();

        System.out.println(sol.solve(processorTime, tasks));
    }
}
