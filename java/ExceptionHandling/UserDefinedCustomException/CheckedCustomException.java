package ExceptionHandling.UserDefinedCustomException;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String m) {
        super(m); // message
    }
}

public class CheckedCustomException {
    public static void validate(int age) throws InvalidAgeException {
        if(age < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }
        System.out.println("Valid Age " + age);
    }

    public static void main(String[] args) {
        try {
            validate(12);
        } catch(InvalidAgeException ex) {
            System.out.println("Exception Caught: " + ex.getMessage());
        }
    }
}
