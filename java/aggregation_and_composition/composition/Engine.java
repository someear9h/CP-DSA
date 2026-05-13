package aggregation_and_composition.composition;

public class Engine {
    private final String type;

    Engine(String type) {
        this.type = type;
    }

    // getter
    public String getEngineType() {
        return this.type;
    }

    void start() {
        System.out.printf("You start the %s Engine\n", this.type);
    }
}
