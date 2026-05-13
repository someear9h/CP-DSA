package methods.overloaded_methods;

public class Eg2 {
    public static void main(String[] args) {
        String pizza = bakePizza("flat bread");
        System.out.println(pizza);
        System.out.println(bakePizza("flat bread", "extra cheese"));
        System.out.println(bakePizza("brown bread", "extra cheese", "sanket"));
    }

    static String bakePizza(String bread) {
        return bread + " " + "pizza";
    }

    static String bakePizza(String bread, String cheese) {
        return bread + " " + cheese + " " + "pizza";
    }

    static String bakePizza(String bread, String cheese, String deliveryBoy) {
        return bread + " " + cheese + " delivered by " + deliveryBoy + " " + "pizza";
    }
}
