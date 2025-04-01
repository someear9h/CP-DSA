package OOP.Inheritance;

class Bicycle {
    public int gear;
    public int speed;

    // constructor
    public Bicycle(int gear, int speed) {
        this.gear = gear;
        this.speed = speed;
    }

    public void applyBreak(int decrement) {
        speed -= decrement;
    }

    public void speedUp(int increment) {
        speed += increment;
    }

    public String toString() {
        return ("No of Gears are " + gear + "\n" + "Speed is: " + speed);
    }
}

class MountainBike extends Bicycle {
    public int seatHeight;

    // the MountainBike subclass has one constructor
    public MountainBike(int gear, int speed, int startHeight) {
        super(gear, speed);
        seatHeight = startHeight;
    }

    public void setHeight(int newHeight) {
        seatHeight = newHeight;
    }

    // overriding toString() method of Bicycle to print more info
    @Override
    public String toString() {
        return (super.toString() + "\nSeat Height is: " + seatHeight);
    }
}

// driver class
public class Example1 {
    public static void main(String[] args) {
        MountainBike mb = new MountainBike(3, 100, 25);
        System.out.println(mb.toString());

        mb.applyBreak(50);
        System.out.println("After Decreasing Speed: \n" + mb.toString());
    }
}
