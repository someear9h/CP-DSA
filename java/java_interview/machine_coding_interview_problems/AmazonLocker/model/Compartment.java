package java_interview.machine_coding_interview_problems.AmazonLocker.model;

public class Compartment {
    private final String id;
    private final Size size;
    private boolean occupied; // not marked final because occupation changes

    public Compartment(String id, Size size) {
        this.id = id;
        this.size = size;
        this.occupied = false;
    }

    public String getId() {
        return id;
    }

    public Size getSize() {
        return size;
    }

    public void markOccupied() {
        this.occupied = true;
    }

    public void markFree() {
        this.occupied = false;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public void open() {
        System.out.println("compartment id " + id + " open");
    }
}
