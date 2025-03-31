package OOP.Abstraction;

//Java program to illustrate the concept of Abstraction
abstract class Shape {
    String color;

    // abstract methods
    abstract double area();
    public abstract String toString();

    // abstract class can have the constructor
    public Shape(String color) {
        this.color = color;
        System.out.println("Shape constructor called");
    }

    // concrete method
    public String getColor() {
        return color;
    }
}

class Circle extends Shape {
    double radius;

    public Circle(String color, double radius) {
        // calling Shape constructor
        super(color);
        this.radius = radius;
        System.out.println("Circle constructor called");
    }

    @Override
    double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Circle color is" + super.getColor() + " and area is " + area();
    }
}

class Rectangle extends Shape {
    double length;
    double width;

    public Rectangle(String color, double length, double width) {

        // calling Shape constructor
        super(color);
        this.length = length;
        this.width = width;
        System.out.println("Rectangle Constructor called");
    }

    @Override double area() {
        return length * width;
    }

    @Override public String toString() {
        return "Rectangle color is " + super.getColor() + " and area is " + area();
    }
}

public class Example2 {
    public static void main(String[] args) {
        Shape s1 = new Circle("Blue", 4.5);
        Shape s2 = new Rectangle("Yellow", 10, 5);

        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }
}
