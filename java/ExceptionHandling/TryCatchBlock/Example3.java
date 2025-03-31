package ExceptionHandling.TryCatchBlock;

// Java program to demonstrate ArrayIndexOutOfBoundsException
public class Example3 {
    public static void main(String[] args) {
        int[] arr = new int[4];

        try {
            int i = arr[4];
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }

        // This statement will now execute
        System.out.println("Hi I want to Execute");
    }
}
