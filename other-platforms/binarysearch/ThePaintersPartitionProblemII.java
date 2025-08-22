public class ThePaintersPartitionProblemII {
    private static int minTime(int[] arr, int k) {
        // code here
        int low = 0, high = 0;
        
        for(int a : arr) {
            low = Math.max(a, low);
            high += a;
        }
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            int painters = f(arr, mid);
            
            if(painters > k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return low;
    }
    
    private static int f(int[] arr, int boards) {
        int painterBoard = 0, painters = 1;
        
        for(int i = 0; i < arr.length; i++) {
            if(painterBoard + arr[i] <= boards) {
                painterBoard += arr[i];
            } else {
                painters++;
                painterBoard = arr[i];
            }
        }
        
        return painters;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 30, 20, 15};
        int k = 3;

        System.out.println(minTime(arr, k)); // output = 35
    }
}
