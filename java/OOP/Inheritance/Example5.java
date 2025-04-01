package OOP.Inheritance;

// This example, demonstrates Multiple inheritance through interfaces, where a class can
// implement multiple interfaces to inherit methods from them.

interface InterfaceA {
    public void printSamarth();
}

interface InterfaceB {
    public void printIs();
}

interface InterfaceC extends InterfaceA, InterfaceB {
    public void printSamarth();
}

class Child implements InterfaceC {
    @Override
    public void printSamarth() {
        System.out.println("Samarth");
    }

    public void printIs() {
        System.out.println("is");
    }
}

public class Example5 {
    public static void main(String[] args) {
        Child c = new Child();

        c.printSamarth();
        c.printIs();
        c.printSamarth();
    }
}