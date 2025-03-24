package class_and_objects;

// Java Program to Demonstrate the use of a class with instance variable
class Dog {
    String name;
    String breed;
    int age;
    String color;

    // Constructor
    public Dog(String name, String breed, int age, String color) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
    }

    // methods
    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

   public String getColor() {
        return color;
   }

    @Override public String toString() {
        return ("Name is: " + this.getName()
                + "\nBreed, age, and color are: "
                + this.getBreed() + ", " + this.getAge()
                + ", " + this.getColor());
    }

    public static void main(String[] args) {
        Dog tuffy = new Dog("Tuffy", "Husky", 5, "blue");
        System.out.println(tuffy.toString());
    }
}
