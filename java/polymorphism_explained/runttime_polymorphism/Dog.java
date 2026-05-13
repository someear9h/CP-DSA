package polymorphism_explained.runttime_polymorphism;

public class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("dog barks");
    }
}
