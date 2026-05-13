package polymorphism_explained;

public class Car extends Vehicle {
    @Override
    void go() {
        System.out.println("You drive the car");
    }
}
