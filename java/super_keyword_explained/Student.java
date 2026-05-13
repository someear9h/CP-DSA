package super_keyword_explained;

public class Student extends Person {
    double gpa;

    Student(String first, String last, double gpa) {
//        this.first = first; -> wrong
//        this.last = last; -> wrong

        super(first, last);
        this.gpa = gpa;
    }

    void showGPA() {
        System.out.println(this.first + "'s GPA is: " + this.gpa);
    }
}