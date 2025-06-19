public class TeemoAttacking {
    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        int total = 0;

        for(int i = 0; i < timeSeries.length - 1; i++) {
            int diff = timeSeries[i + 1] - timeSeries[i];
            total += Math.min(diff, duration);
        }
        total += duration;
        return total;
    }

    public static void main(String[] args) {
        int[] timeSeries = {1, 4};
        int duration = 2;

        System.out.println(findPoisonedDuration(timeSeries, duration));
    }
}
