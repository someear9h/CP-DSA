package OOP.Inheritance;

// This program, demonstrates multilevel inheritance in Java, where class Three inherits from class Two,
// and class Two inherits from class One.

class One {
    public void printSamarth() {
        System.out.print("Samarth");
    }
}

class Two extends One {
    public void printIS() {
        super.printSamarth();
        System.out.print(" is");
    }
}

class Three extends Two {
    public void printGood() {
        super.printIS();
        System.out.println(" Good.");
    }
}

public class Example3 {
    public static void main(String[] args) {
        Three t = new Three();
        t.printGood();
    }
}