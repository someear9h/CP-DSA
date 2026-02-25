import java.util.*;

record Pair(int x, int y) {}

public class Solution {
    public static void main(String[] args) {
        
        // streams
        int[] arr1 = {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.stream(arr1).boxed().toList();
        System.out.println(list);

        // Suppose you have an array of numbers, 
        // and you want to find the square of all the even numbers, then sum them up.
        int sum = Arrays.stream(arr1)
                    .filter(n -> n % 2 == 0)
                    .map(n -> n * n)
                    .sum();

        System.out.println("Sum: " + sum);

        
        Pair curr = new Pair(5, 10);
        System.out.println("X: " + curr.x());
        System.out.println(curr);
    }
}