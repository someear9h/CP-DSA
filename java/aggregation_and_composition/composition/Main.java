package aggregation_and_composition.composition;

public class Main {
    public static void main(String[] args) {
        /*
        * Composition: Represents a "part-of" relationships between objects
        * for eg -> an engine cannot exist without car, it is a part of the car
        * Allows complex objects to be constructed from smaller objects
        * */

        Car car = new Car("Mustang", 2005, "v8");

        System.out.println(car.getModel());
        System.out.println(car.getYear());
        System.out.println(car.getEngine().getEngineType());

        car.start();
    }
}