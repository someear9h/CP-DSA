public class MinimumTimetoCompleteAllDeliveries {
    public static long minimumTime(int[] d, int[] r) {
        long low = 0;
        long temp = ((long)d[0] + (long)d[1]) * (long)Math.max(r[0], r[1]);
        long high = Math.max(100L, temp); // safe high position

        while(low <= high) {
            // mid = guessing of minimum time to deliver 
            long mid = low + (high - low) /2;
            
            // x1 -> number of slots dron1 wont work
            // x2 -> same for drone 2
            long x1 = mid - mid / r[0];
            long x2 = mid - mid / r[1];
            long gcdd = gcd(r[0], r[1]);
            // common multiple 
            long x = ((long)r[0] * (long)r[1]) / gcdd;
            long x3 = mid - (mid/r[0] + mid/r[1] - mid/x);

            if(x1 >= d[0] && x2 >= d[1] && x1 + x2 - x3 >= (long)d[0] + d[1]) {
                high = mid - 1; // go for lower time
            } else {
                low = mid + 1; // go for higher time
            }
        }

        return low;
    }

    private static long gcd(long a, long b) {
        while(b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    public static void main(String[] args) {
        int[] d ={3, 1}, r ={2, 3};

        System.out.println(minimumTime(d, r));
    }
}
