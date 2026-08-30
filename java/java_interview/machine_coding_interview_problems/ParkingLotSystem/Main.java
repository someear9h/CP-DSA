package java_interview.machine_coding_interview_problems.ParkingLotSystem;

import java.util.*;

enum VehicleType {
    CAR, BIKE, TRUCK
}

class Vehicle {
    String regNo;
    VehicleType type;
    String color;

    public Vehicle(String regNo, String color, VehicleType type) {
        this.regNo = regNo;
        this.type = type;
        this.color = color;
    }
}

class Slot implements Comparable<Slot> {
    int slotNo;
    boolean isTaken;
    VehicleType type;

    public Slot(int slotNo, VehicleType type) {
        this.slotNo = slotNo;
        this.isTaken = false;
        this.type = type;
    }

    @Override
    public int compareTo(Slot other) {
        return Integer.compare(this.slotNo, other.slotNo);
    }
}

class Ticket {
    String id;
    int floorNo;
    int slotNo;
    Vehicle vehicle;

    public Ticket(int floorNo, int slotNo, Vehicle veh) {
        this.id = UUID.randomUUID().toString();
        this.floorNo = floorNo;
        this.slotNo = slotNo;
        this.vehicle = veh;
    }
}

class Floor {
    int floorNo;
    private final Map<VehicleType, TreeSet<Slot>> freeSlotsByType = 
    new EnumMap<>(VehicleType.class);

    private final Map<Integer, Slot> allSlots = new HashMap<>();

    // fill the data structures for this floor
    public Floor(int floorNo, int numSlots) {
        this.floorNo = floorNo;
        
        for(int i = 1; i <= numSlots; i++) {
            VehicleType type;
            if(i == 1) {
                type = VehicleType.TRUCK;
            }
            else if(i == 2 || i == 3) {
                type = VehicleType.BIKE;
            }
            else {
                type = VehicleType.CAR;
            }

            Slot slot = new Slot(i, type);
            freeSlotsByType.computeIfAbsent(type, t -> new TreeSet<>()).add(slot);
            allSlots.put(i, slot);
        }
    }

    public Optional<Slot> allocateSlot(VehicleType type) {
        TreeSet<Slot> freeSlots = freeSlotsByType.get(type);
        if(freeSlots == null || freeSlots.isEmpty()) {
            return Optional.empty();
        }

        Slot allocatedSlot = freeSlots.pollFirst();
        allocatedSlot.isTaken = true;
        return Optional.of(allocatedSlot);
    }

    public void releaseSlot(int slotNo) {
        Slot slot = allSlots.get(slotNo);
        slot.isTaken = false;
        freeSlotsByType.get(slot.type).add(slot);
    } 

    public int getFreeCount(VehicleType type) {
        TreeSet<Slot> free = freeSlotsByType.get(type);
        return free == null ? 0 : free.size();
    }
}

class ParkingLotService {
    private final List<Floor> floors = new ArrayList<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();

    public ParkingLotService(int numFloors, int numSlots) {
        for(int i = 1; i <= numFloors; i++) {
            floors.add(new Floor(i, numSlots));
        }
    }

    public Optional<Ticket> parkVehicle(Vehicle vehicle) {
        VehicleType type = vehicle.type;
        for(Floor floor : floors) {
            Optional<Slot> slot = floor.allocateSlot(type);
            if(slot.isPresent()) {
                Ticket ticket = new Ticket(floor.floorNo, slot.get().slotNo, vehicle);
                activeTickets.put(ticket.id, ticket);
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }

    public boolean unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        activeTickets.remove(ticketId);

        if(ticket == null) {
            return false;
        }

        int slotNo = ticket.slotNo;
        int floorNo = ticket.floorNo;
        Floor floor = floors.get(floorNo - 1);
        floor.releaseSlot(slotNo);
        return true;
    }
    
    public Map<Integer, Integer> getFreeCountPerFloor(VehicleType type) {
        Map<Integer, Integer> result = new LinkedHashMap<>(); // preserves floor order for display
        for (Floor floor : floors) {
            result.put(floor.floorNo, floor.getFreeCount(type));
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLotService service = new ParkingLotService(5, 5);

        printFreeCount(service, VehicleType.CAR);

        Optional<Ticket> ticket1 = service.parkVehicle(new Vehicle("KA01AB1234", "navyblue", VehicleType.CAR));
        ticket1.ifPresentOrElse(
            t -> System.out.println("Parked. Ticket: " + t.id),
            () -> System.out.println("Parking full!")
        );
        printFreeCount(service, VehicleType.CAR);

        Optional<Ticket> ticket2 = service.parkVehicle(new Vehicle("KA01CD5678", "red", VehicleType.CAR));
        ticket2.ifPresentOrElse(
            t -> System.out.println("Parked. Ticket: " + t.id),
            () -> System.out.println("Parking full!")
        );
        printFreeCount(service, VehicleType.CAR);

        // Unpark using ticket1 -- safe even if ticket1 were empty, since we check isPresent
        ticket1.ifPresent(t -> {
            boolean released = service.unparkVehicle(t.id);
            System.out.println("Unparked ticket1: " + released);
        });
        printFreeCount(service, VehicleType.CAR);

        // Demonstrate the fixed bug from v1: unparking a garbage ticket no longer crashes
        boolean releasedGarbage = service.unparkVehicle("not-a-real-ticket");
        System.out.println("Unparked garbage ticket (should be false): " + releasedGarbage);
    }

    private static void printFreeCount(ParkingLotService service, VehicleType type) {
        System.out.println("Free slots for " + type + ":");
        service.getFreeCountPerFloor(type).forEach((floorNo, count) ->
            System.out.printf("  Floor %d: %d%n", floorNo, count));
    }
}