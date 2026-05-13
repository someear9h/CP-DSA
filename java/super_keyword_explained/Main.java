package super_keyword_explained;

public class Main {
    public static void main(String[] args) {

        /*
        * super = refers to parent class (child class <-- parent class)
        * Used in constructors and method overloading
        * Calls the parent constructors to initialize attributes
        * */

        Person p1 = new Person("Samarth", "Tikotkar");
        Person p2 = new Person("Sam", "Tiks");

        p1.showName();
        p2.showName();

        Student student = new Student("Harry", "Potter", 2.45);
        System.out.println(student.gpa);

        student.showName();
        student.showGPA();

        Employee emp = new Employee("Vijay", "Shankar", 35000);
        emp.showName();
        emp.showSalary();
    }
}
