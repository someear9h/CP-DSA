package ExceptionHandling.TryCatchBlock;

// Example that illustrates how a run-time system searches for appropriate exception handling code on the call stack.
public class Example2 {

    static int divideByZEro(int a, int b) {
        int i = a / b;
        return i;
    }

    static int computeDivision(int a, int b) {
        int res = 0;
        try {
            res = divideByZEro(a, b);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException has Occurred");
        }
        return res;
    }

    public static void main(String[] args) {
        int a = 1, b = 0;

        try{
            int res = computeDivision(a, b);
        } catch(ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
