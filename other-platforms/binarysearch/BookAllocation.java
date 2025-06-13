// https://takeuforward.org/plus/dsa/problems/book-allocation-problem

public class BookAllocation {
    public static int countStudents(int[] arr, int pages) {
        int students = 1;
        int pagesPerStudent = 0;
        
        for(int i = 0; i < arr.length; i++) {
            if(pagesPerStudent + arr[i] <= pages) {
                pagesPerStudent += arr[i];
            } else {
                students++;
                pagesPerStudent = arr[i];
            }
        }
        return students;
    }

    public static int findPages(int[] nums, int m) {
        int left = Integer.MIN_VALUE, right = 0;
        
        for(int i = 0; i < nums.length; i++) {
            left = Math.max(left, nums[i]);
            right += nums[i];
        }

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(countStudents(nums, mid) > m) {
                // means we have allocated little pages to students go right for more pages
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {12, 34, 67, 90};
        int m = 2;

        System.out.println(findPages(arr, m));
    }
}
