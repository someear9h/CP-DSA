package ExceptionHandling.UserDefinedCustomException;

class DivisionByZeroException extends RuntimeException{
    public DivisionByZeroException(String m) {
        super(m); // message for exception
    }
}

public class UncheckedCustomException {
    public static void divide(int a, int b) throws DivisionByZeroException {
        if(b == 0) {
            throw new DivisionByZeroException("Division By Zero is not allowed");
        }
        System.out.println(a / b);
    }

    public static void main(String[] args) {
        try {
            divide(5, 0);
        } catch(DivisionByZeroException ex) {
            System.out.println("Exception Caught: " + ex.getMessage());
        }
    }
}
