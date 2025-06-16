public class Searcha2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int top = 0;
        int bot = matrix.length - 1;

        while(top <= bot) {
            int mid = (top + bot) / 2;
            if(matrix[mid][0] < target && matrix[mid][matrix[mid].length - 1] > target) {
                break; // exit the loop
            } else if(matrix[mid][0] > target) {
                bot = mid - 1;
            } else {
                top = mid + 1;
            }
        }

        int row = (top + bot) / 2;
        int left = 0, right = matrix[row].length -1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(matrix[row][mid] == target) {
                return true;
            } else if(target > matrix[row][mid]) {
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