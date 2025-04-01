package ExceptionHandling.Final_and_Finally;

public class FinallyExample {
    public static void main(String[] args) {
        try {

            System.out.println("Inside try block");
            int res = 10 / 0; // cause an exception
        } catch(ArithmeticException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }finally {
            System.out.println("Finally block always executes");
        }
    }
}
