// package java_interview.interview_questions;

class A {
    public A() {
        System.out.println("Object created");
    }

    @Override
    public void finalize() {
        // finalize has been depreaceted not recommnded to use
        // just good to know for interviews
        System.out.println("in finalise");
    }
}

public class FinalizeMethod {
    public static void main(String[] args) {
        A obj = new A();
        // new A() creates the java object
        // obj is a reference
        obj = null;
        System.gc();
        
    }
}
