package methods.override_tostring;

public class Main {
    public static void main(String[] args) {

        /*
        * .toString() -> Method inherited from the object class
        * Used to return a string representation of an object
        * By default, it returns a hashcode as unique identifier
        * it can be overridden to provide meaningful details
        * */

        Car car = new Car("Ford", "Mustang", 2026, "Red");

        System.out.println(car);
    }
}
