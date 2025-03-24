package class_and_objects;

// Program to initialize Java Object by using method/function
public class Example2 {
    static String name;
    static float price;

    static void set(String n, float p) {
        name = n;
        price = p;
    }

    static void display() {
        System.out.println("Software name is: " + name);
        System.out.println("Software Price is: " + price);
    }

    public static void main(String[] args) {
        Example2.set("Visual Studio Code", 0.0f);
        Example2.display();
    }
}
