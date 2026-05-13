package TypeInferenceandMethodReferences;

public class Guest {
    private String name;
    private int age;

    Guest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }
}
