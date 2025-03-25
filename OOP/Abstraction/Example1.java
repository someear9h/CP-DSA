package Abstraction;
// Demonstrating Abstraction in Java

abstract class TV {
    abstract void turnOn();
    abstract void turnOff();
}

// concrete class implementing the abstract methods
class Remote extends TV {
    @Override
    void turnOn() {
        System.out.println("TV is turned On");
    }

    @Override
    void turnOff() {
        System.out.println("TV is turned Off");
    }
}

public class Example1 {
    public static void main(String[] args) {
        TV remote = new Remote();
        remote.turnOn();
        remote.turnOff();
    }
}
