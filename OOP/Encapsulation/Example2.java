package Encapsulation;

class Student {
    private String name;
    private int rollNo;
    private int age;

    // getters
    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public int getAge() {
        return age;
    }

    // setters
    public void setName(String newName) {
        name = newName;
    }

    public void setRollNo(int rn) {
        rollNo = rn;
    }

    public void setAge(int newAge) {
        age = newAge;
    }
}

public class Example2 {
    public static void main(String[] args) {
        Student s = new Student();
        s.setName("Samarth");
        s.setRollNo(600025);
        s.setAge(19);

        System.out.println("Name: " + s.getName());
        System.out.println("Roll NO: " + s.getRollNo());
        System.out.println("Age: " + s.getAge());
    }
}