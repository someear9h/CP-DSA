package Abstraction;

// another example to understand abstraction in Java
abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void makeSound();

    public String getName() {
        return name;
    }
}

// abstracted class
class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    public void makeSound() {
        System.out.println(getName() + " barks");
    }
}

class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    public void makeSound() {
        System.out.println(getName() + " meows");
    }
}

// driver class
public class Example3 {
    public static void main(String[] args) {
        Animal dog = new Dog("Bruno");
        Animal cat = new Cat("Tom");

        dog.makeSound();
        cat.makeSound();
    }
}