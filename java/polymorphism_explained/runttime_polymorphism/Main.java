package polymorphism_explained.runttime_polymorphism;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        * Runtime polymorphism -> When the Method that gets executed is decided at runtime
        * base on actual type of object
        * */

        Animal animal;

        Scanner sc = new Scanner(System.in);
        System.out.print("Dog (1) or Cat (2) ? ");
        int choice = sc.nextInt();

        if(choice == 1) {
            animal = new Dog();
            animal.speak();
        } else if(choice == 2) {
            animal = new Cat();
            animal.speak();
        }

        sc.close();
    }
}
