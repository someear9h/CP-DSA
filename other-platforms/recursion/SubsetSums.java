import java.util.*;

public class SubsetSums {
    public static ArrayList<Integer> subsetSums(int[] arr) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        
        helper(0, arr, 0, res);
        
        return res;
    }
    
    private static void helper(int idx, int[] arr, int sum, ArrayList<Integer> res) {
        // base case
        if(idx == arr.length) {
            res.add(sum);
            return;
        }
        
        // pick
        helper(idx + 1, arr, sum + arr[idx], res);
        
        // dont pick
        helper(idx + 1, arr, sum, res);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3};

        ArrayList<Integer> res = subsetSums(arr);
        
        for(int r : res) {
            System.out.print(r + " ");
        }

        System.out.println();
    }
}
