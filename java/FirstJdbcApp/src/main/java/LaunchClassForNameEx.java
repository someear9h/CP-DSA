package FirstJdbcApp.src.main.java;

public class LaunchClassForNameEx {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("Demo");

        Demo demo = new Demo();
    }
}

class Demo {
    static {
        System.out.println("Static Block");
    }

    {
        System.out.println("Instance Block --> not a static block");
    }
}
