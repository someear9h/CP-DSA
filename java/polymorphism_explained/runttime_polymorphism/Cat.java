package polymorphism_explained.runttime_polymorphism;

public class Cat extends Animal {
    @Override
    void speak() {
        System.out.println("Cat meows");
    }
}
