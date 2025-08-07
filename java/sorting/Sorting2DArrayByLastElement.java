import java.util.*;

public class Sorting2DArrayByLastElement {
    public static void main(String[] args) {
        int[][] array = {{3, 8}, {5, 1}, {9, 4}};

        System.out.println("original array: ");
        System.out.println(Arrays.deepToString(array));

        // Arrays.sort(array, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(array, (a, b) -> a[1] - b[1]);

        System.out.println("Sorting by 2nd element: ");
        System.out.println(Arrays.deepToString(array));
    }
}
