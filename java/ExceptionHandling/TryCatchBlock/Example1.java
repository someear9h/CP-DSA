package ExceptionHandling.TryCatchBlock;

// Example to handle an ArithmeticException using try-catch, and finally blocks
public class Example1 {
    public static void main(String[] args) {
        int n = 10, m = 0;

        try {
            // Code that may throw an exception
            int ans = n / m;
            System.out.println("Answer: " + ans);
        } catch(ArithmeticException e) {

            System.out.println("Error: Dont divide by zero");
        } finally {
            System.out.println("Program continues after handling the exception");
        }
    }
}