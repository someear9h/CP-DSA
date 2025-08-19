// swap to numbers without using 3rd variable

public class SwapTwoNumbers {
    public static void main(String[] args) {
        int a = 5;
        int b = 9;

        System.out.println("Before Swapping: ");
        System.out.println("a: " + a);
        System.out.println("b: " + b);

        a = a ^ b;
        b = a ^ b; // here a = a ^ b -> b = (a ^ b) ^ b, where b ^ b becomes 0 and b = a
        a = a ^ b; // a = a ^ b -> (a ^ b) ^ b now b = a from above step so, a ^ a = 0 and a becomes b

        System.out.println("After Swapping: ");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }    
}
