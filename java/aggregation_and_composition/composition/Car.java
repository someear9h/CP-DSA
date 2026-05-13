package aggregation_and_composition.composition;

public class Car {
    private final String model;
    private final int year;
    private final Engine engine;

    Car(String model, int year, String engineType) {
        this.model = model;
        this.year = year;
        this.engine = new Engine(engineType);
    }

    void start() {
        // when we start the car we also start the engine
        this.engine.start();
        System.out.printf("%s is running\n", this.model);
    }

    // getters
    public String getModel() {
        return this.model;
    }

    public int getYear() {
        return this.year;
    }

    public Engine getEngine() {
        return this.engine;
    }
}
