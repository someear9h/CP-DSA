package polymorphism_explained;

public class Main {
    public static void main(String[] args) {

        /*
        * Polymorphism -> "Poly" = "Many"
        *                  "Morphs" = "Forms/shapes"
        * Objects can identify as other objects
        * Objects can be treated as objects of the superclass
        * eg -> Car is a car and also Vehicle
        * */

        Car car = new Car();
        Bike bike = new Bike();
        Boat boat = new Boat();

        Vehicle[] vehicles = {car, bike, boat};

        for(Vehicle vehicle : vehicles) {
            vehicle.go();
        }
    }
}
