import java.util.*;

public class MinimumPrefixRemovaltoMakeArrayStrictlyIncreasing {
    public static int minimumPrefixLength(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        st.push(n-1);
        
        for(int i = n -2; i >= 0; i--) {
            if(nums[st.peek()] > nums[i]) {
                st.push(i); 
            } else {
                return i+1;
            }
        }

        return 0;            
    }   
    
    public static void main(String[] args) {
        int[] nums = {1,-1,2,3,3,4,5};

        System.out.println(minimumPrefixLength(nums));
    }
}