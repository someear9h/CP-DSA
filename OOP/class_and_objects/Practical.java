package class_and_objects;

// Class representing a Programmer in a company
class Programmer {
    String name;
    String language;
    int experience;

    Programmer(String name, String language, int experience) {
        this.name = name;
        this.language = language;
        this.experience = experience;
    }

    // method do display info
    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Programming Language: " + language);
        System.out.println("Experience: " + experience +" years");
    }

    // memeber function to check if programmer is senior (experience >= 5)
    void isSenior() {
        if(experience >= 5) {
            System.out.println("Above Programmer is Senior");
        } else {
            System.out.println("Above Programmer Not a senior");
        }
    }

    public static void main(String[] args) {
        Programmer p1 = new Programmer("Samarth", "java", 7);
        Programmer p2 = new Programmer("Sanket", "c++", 1);

        // displaying details of both programmers
        System.out.println("Programmer 1: ");
        p1.displayInfo();
        p1.isSenior();

        System.out.println("Programmer 2: ");
        p2.displayInfo();
        p2.isSenior();
    }
}