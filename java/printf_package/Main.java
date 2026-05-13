package printf_package;

public class Main {
    public static void main(String[] args) {
        String name = "Samarth";
        char firstLetter = 'S';
        int age = 20;
        double height = 182.5;
        boolean isGoat = true;

        /*
          for string -> %s
          for char -> %c
          for int -> %d
          for double/float -> %f
          for boolean -> %b
          */

//        System.out.printf("Hello %s\n", name);
//        System.out.printf("Your name starts with %c\n", firstLetter);
//        System.out.printf("Your are %d Years old\n", age);
//        System.out.printf("You are %.2f cm tall, good height!\n", height);
//        System.out.printf("Goat: %b\n\n\n", isGoat);

        System.out.printf("%s is %d old and he is %.1f cm tall. Its %b that he is a goat\n\n\n",
                name, age, height, isGoat);

        // padding
        // zero padding -> %04
        // +ve number -> right justified padding
        // -ve number -> left justified padding
        int id1 = 1;
        int id2 = 12;
        int id3 = 123;
        int id4 = 1234;

        System.out.printf("%-4d\n", id1);
        System.out.printf("%-4d\n", id2);
        System.out.printf("%-4d\n", id3);
        System.out.printf("%-4d\n", id4);

    }
}