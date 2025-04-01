package ExceptionHandling.Final_and_Finally;

public class FinalExample {
    public static void main(String[] args) {
        int a = 5;
        final int b = 10;

        a++;
        // b++;  cannot assign a value to final variable b
        System.out.println(a);
    }
}
