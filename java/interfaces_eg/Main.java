package interfaces_eg;

public class Main {
    public static void main(String[] args) {

        /*
        * Interfaces -> A blueprint of a class that specifies a set of abstract methods
        * - also we can say defines the "contracts" of what the implementing classes must do
        * - supports multiple inheritance (inheriting from more than 1 parent)
        * */

        Rabbit rabbit = new Rabbit();
        Hawk hawk = new Hawk();
        Fish fish = new Fish();

        rabbit.flee();
        hawk.hunt();

        fish.flee();
        fish.hunt();
    }
}
