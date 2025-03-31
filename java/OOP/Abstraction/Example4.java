package OOP.Abstraction;

interface Shape1 {
    double calculateArea(); // abstract method
}

class Circle1 implements Shape1 {
    private double r;

    public Circle1(double r) {
        this.r = r;
    }

    // implementing the abstract method from Shape1
    public double calculateArea() {
        return Math.PI * Math.pow(r, 2);
    }
}

class Rectangle1 implements Shape1 {
    private double length;
    private double width;

    public Rectangle1(double length, double width) {
        this.length = length;
        this.width = width;
    }

    // implement abstract method
    public double calculateArea() {
        return length * width;
    }
}

public class Example4 {
    public static void main(String[] args) {
        Shape1 circle = new Circle1(5.0);
        Shape1 rect = new Rectangle1(10.0, 5.0);

        System.out.println("Area of Circle: " + circle.calculateArea());
        System.out.println("Area of Rectangle: " + rect.calculateArea());
    }
}
