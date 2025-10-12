public class FindtheMaximumLengthofValidSubsequenceI {
    public static int maximumLength(int[] nums) {
        int cntEve = 0;
        int cntOdd = 0;

        for(int num : nums) {
            if(num % 2 == 0)
                cntEve++;
            else
                cntOdd++;
        }

        int odd = 0;
        int even = 0;

        for(int num : nums) {
            if(num % 2 == 0) {
                even = Math.max(even, odd + 1);
            } else {
                odd = Math.max(odd, even + 1);
            }
        }

        return Math.max(Math.max(cntEve, cntOdd), Math.max(even, odd));
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(maximumLength(nums));
    }
}
