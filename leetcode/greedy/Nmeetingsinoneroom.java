import java.util.Arrays;

public class Nmeetingsinoneroom {

    public static void solve(int[] start, int[] end) {
        int n = start.length;
        int[][] meetings = new int[n][3];  // [start, end, index]

        for(int i = 0; i < n; i++) {
            meetings[i][0] = start[i];
            meetings[i][1] = end[i];
            meetings[i][2] = i + 1;  // storing meeting number (1-based index)
        }

        // Sort by end time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1]));

        System.out.print("Order of meetings: ");

        // First meeting always selected
        int count = 1;
        int lastEndTime = meetings[0][1];
        System.out.print(meetings[0][2] + " ");

        for(int i = 1; i < n; i++) {
            if(meetings[i][0] >= lastEndTime) {
                count++;
                lastEndTime = meetings[i][1];
                System.out.print(meetings[i][2] + " ");
            }
        }

        System.out.println("\nTotal meetings: " + count);
    }

    public static void main(String[] args) {
        int[] start = {1,3,0,5,8,5};
        int[] end   = {2,4,5,7,9,9};

        solve(start, end);
    }
}