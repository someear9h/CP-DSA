package generics;

public class Main {
    public static void main(String[] args) {
        /*
        * Generics -> A concept where u can write class, methods, interface, etc
        * that is compatible with different data types
        * <T> type parameter (placeholder that gets replaced with an actual data type)
        * <String> Type argument, specifies the type
        * */

//        Box<String> box = new Box<>();
//        box.setItem("apple");
//
//        System.out.println(box.getItem());

//        Box<Integer> box = new Box<>();
//        box.setItem(272);
//
//        System.out.println(box.getItem());

        Product<String, Double> product1 = new Product<>("Watch", 23.99);
        Product<String, Integer> product2 = new Product<>("Computer", 50);

        System.out.println(product1.getItem());
        System.out.println(product1.getPrice());

        System.out.println(product2.getItem());
        System.out.println(product2.getPrice());
    }
}
