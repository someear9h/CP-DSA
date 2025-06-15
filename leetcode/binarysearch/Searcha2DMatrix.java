public class Searcha2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int row = mid / cols;
            int col = mid % cols;
            int guess = matrix[row][col];

            if (guess == target) {
                return true;
            } else if (guess < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;        
    }
    public static void main(String[] args) {
        int[][] matrix = {
            {1,3,5,7}, {10,11,16,20}, {23,30,34,60}
        };

        int target = 3;

        System.out.println((searchMatrix(matrix, target) ? "True" : "false"));
    }
}