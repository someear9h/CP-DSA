public class MinimizeMaxDistancetoGasStation {
    private static double bruteForce(int[] stations, int k) {
        int n = stations.length;
        int[] howManySections = new int[n - 1];
        
        for(int gs = 1; gs <= k; gs++) {
            double maxSection = -1;
            int maxInd = -1;
            
            for(int i = 0; i < n - 1; i++) {
                double diff = stations[i + 1] - stations[i];
                double sectionLength = diff / (double) (howManySections[i] + 1); 

                if(sectionLength > maxSection) {
                    maxSection = sectionLength;
                    maxInd = i;
                }
            }

            // insert the current gas station
            howManySections[maxInd]++;
        }

        double maxAns = -1;
        for(int i = 0; i < n - 1; i++) {
            double diff = stations[i + 1] - stations[i];
            double sectionLength = diff / (double) (howManySections[i] + 1);
            maxAns = Math.max(maxAns, sectionLength);
        }

        return maxAns;
    }    

    private static int countStations(double dist, int[] stations) {
        int n = stations.length;
        int cnt = 0;
        
        for (int i = 1; i < n; i++) {
            double gap = stations[i] - stations[i - 1];
            cnt += (int)(gap / dist); // number of new stations required
        }

        return cnt;
    }

    private static double binarySearch(int[] stations, int k) {
        int n = stations.length;
        double low = 0;
        double high = 0;
        for(int i = 1; i < n; i++) {
            int diff = stations[i] - stations[i - 1];
            high = Math.max(high, diff);
        }

        while((high - low) > 1e-2) {
            double mid = low + (high - low) / 2.0;
            int cntStations = countStations(mid, stations);

            if(cntStations > k) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }

    public static void main(String[] args) {
        int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 9;

        System.out.println("Brute Force: " + bruteForce(stations, k));
        System.out.println("BinarySearch: " + binarySearch(stations, k));
    }
}
