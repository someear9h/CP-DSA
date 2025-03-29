package Encapsulation;

// Java program demonstrating Encapsulation
class Programmer {
    private String name;

    // getter
    public String getName() {
        return name;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }
}

public class Example1 {
    public static void main(String[] args) {
        Programmer p = new Programmer();
        p.setName("Samarth");
        System.out.println("Name =>" + p.getName());
    }
}
