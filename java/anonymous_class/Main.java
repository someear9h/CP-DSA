package anonymous_class;

public class Main {
    public static void main(String[] args) {
        /*
        * Anonymous class -> A class that doesnt have a name, cant be reused
        * Add custom behavior without having to create a class.
        * often used for one time uses (Timer task, Runnable, callbacks)
        * */

        Dog dog1 = new Dog();
        dog1.speak();

        // now if there is a talking dog, we will have to make
        // a class TalkingDog and then inherit the Dog class
        // and override the method speak()
        // but we anonymous class instead
        Dog dog2 = new Dog() {
            @Override
            void speak() {
                System.out.println("This dogs talks");
            }
        };

        dog2.speak();
    }
}
