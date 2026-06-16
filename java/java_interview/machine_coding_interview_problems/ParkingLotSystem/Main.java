package java_interview.machine_coding_interview_problems.ParkingLotSystem;

/**
 * 
 * Problem Statement:

Features:

The parking lot has multiple Floors.
Each floor has multiple Slots.
There are three types of vehicles: TRUCK, CAR, and BIKE.
Slots are specific to vehicle types (e.g., a Car cannot park in a Bike slot).
When a vehicle enters, it must be assigned the lowest available floor and the lowest available slot on that floor.
A Ticket is generated upon entry.
When a vehicle leaves, the slot becomes free again.

Requirements:

Initialize the Parking Lot with N floors and M slots per floor.
(Assume Slot 1 is for Trucks, Slots 2-3 for Bikes, and Slots 4-M for Cars).
parkVehicle(VehicleType, RegistrationNumber, Color) -> Returns a Ticket ID or "Lot Full".
unparkVehicle(TicketId) -> Frees the slot.
displayFreeCount(VehicleType) -> Shows how many slots are free per floor.
 */

import java.util.*;

// --- ENUMS ---
enum VehicleType {
    TRUCK, BIKE, CAR
}

// --- ENTITIES ---

// Using standard classes (though Java Records are great here too)
class Vehicle {
    String regNo;
    String color;
    VehicleType type;

    public Vehicle(String regNo, String color, VehicleType type) {
        this.regNo = regNo;
        this.color = color;
        this.type = type;
    }
}

// Slot MUST implement Comparable so the TreeSet knows how to sort them!
class Slot implements Comparable<Slot> {
    int floorNo;
    int slotNo;
    VehicleType allowedType;
    Vehicle vehicle; // null means the slot is free

    public Slot(int floorNo, int slotNo, VehicleType allowedType) {
        this.floorNo = floorNo;
        this.slotNo = slotNo;
        this.allowedType = allowedType;
        this.vehicle = null;
    }

    @Override
    public int compareTo(Slot other) {
        // Sorts slots in ascending order (Slot 1, then Slot 2, etc.)
        return Integer.compare(this.slotNo, other.slotNo);
    }
}

// --- THE SERVICE ---
class ParkingLotService {
    // Database 1: The physical representation -> Map<FloorNo, Map<SlotNo, Slot>>
    private Map<Integer, Map<Integer, Slot>> physicalLot;

    // Database 2: The Fast-Access Tracker -> Map<FloorNo, Map<VehicleType, TreeSet<Slot>>>
    private Map<Integer, Map<VehicleType, TreeSet<Slot>>> freeSlotsTracker;

    private int numFloors;
    private int slotsPerFloor;

    public ParkingLotService(int numFloors, int slotsPerFloor) {
        this.numFloors = numFloors;
        this.slotsPerFloor = slotsPerFloor;
        this.physicalLot = new HashMap<>();
        this.freeSlotsTracker = new HashMap<>();

        // Initialize the Parking Lot
        for (int i = 1; i <= numFloors; i++) {
            physicalLot.put(i, new HashMap<>());
            
            freeSlotsTracker.put(i, new HashMap<>());
            freeSlotsTracker.get(i).put(VehicleType.TRUCK, new TreeSet<>());
            freeSlotsTracker.get(i).put(VehicleType.BIKE, new TreeSet<>());
            freeSlotsTracker.get(i).put(VehicleType.CAR, new TreeSet<>());

            for (int j = 1; j <= slotsPerFloor; j++) {
                // Rule: Slot 1 is Truck, 2-3 are Bikes, 4-M are Cars
                VehicleType type;
                if (j == 1) {
                    type = VehicleType.TRUCK;
                } else if (j == 2 || j == 3) {
                    type = VehicleType.BIKE;
                } else {
                    type = VehicleType.CAR;
                }

                Slot slot = new Slot(i, j, type);
                
                // Add to physical lot
                physicalLot.get(i).put(j, slot);
                // Add to tracker
                freeSlotsTracker.get(i).get(type).add(slot);
            }
        }
        System.out.println("Created parking lot with " + numFloors + " floors and " + slotsPerFloor + " slots per floor");
    }

    // Requirement: parkVehicle
    public String parkVehicle(VehicleType type, String regNo, String color) {
        // Find the lowest available floor
        for (int i = 1; i <= numFloors; i++) {
            TreeSet<Slot> availableSlots = freeSlotsTracker.get(i).get(type);

            if (!availableSlots.isEmpty()) {
                // Get the lowest available slot on this floor in O(1) time
                Slot slotToPark = availableSlots.first();
                
                // 1. Remove it from the free tracker (O(log N))
                availableSlots.remove(slotToPark);
                
                // 2. Park the vehicle in the physical slot
                slotToPark.vehicle = new Vehicle(regNo, color, type);
                
                // 3. Generate and return Ticket ID
                String ticketId = "PRK-" + slotToPark.floorNo + "-" + slotToPark.slotNo;
                System.out.println("Parked vehicle. Ticket ID: " + ticketId);
                return ticketId;
            }
        }
        System.out.println("Lot Full");
        return "Lot Full";
    }

    // Requirement: unparkVehicle
    public void unparkVehicle(String ticketId) {
        // Parse the ticket ID (Format: PRK-floorNo-slotNo)
        String[] parts = ticketId.split("-");
        if (parts.length != 3) {
            System.out.println("Invalid Ticket ID");
            return;
        }

        int floorNo = Integer.parseInt(parts[1]);
        int slotNo = Integer.parseInt(parts[2]);

        // Validation
        if (!physicalLot.containsKey(floorNo) || !physicalLot.get(floorNo).containsKey(slotNo)) {
            System.out.println("Invalid Floor or Slot number.");
            return;
        }

        Slot slot = physicalLot.get(floorNo).get(slotNo);

        if (slot.vehicle == null) {
            System.out.println("Slot is already free. Nothing is parked here.");
            return;
        }

        // 1. Save vehicle type so we know which tracker to update
        VehicleType type = slot.allowedType;

        // 2. Clear the vehicle out of the physical slot
        slot.vehicle = null;

        // 3. Put the slot back into the free tracker so the guard knows it's open
        freeSlotsTracker.get(floorNo).get(type).add(slot);

        System.out.println("Unparked vehicle from Floor " + floorNo + ", Slot " + slotNo);
    }

    // Requirement: displayFreeCount
    public void displayFreeCount(VehicleType type) {
        for (int i = 1; i <= numFloors; i++) {
            int freeCount = freeSlotsTracker.get(i).get(type).size();
            System.out.println("No. of free slots for " + type + " on Floor " + i + ": " + freeCount);
        }
    }
}

// --- MAIN EXECUTION ---
public class Main {
    public static void main(String[] args) {
        // Initialize 2 floors, 5 slots per floor
        ParkingLotService lot = new ParkingLotService(2, 5);
        System.out.println("-------------------------------------------------");

        // Park some cars
        String ticket1 = lot.parkVehicle(VehicleType.CAR, "MH-12-1234", "White");
        String ticket2 = lot.parkVehicle(VehicleType.CAR, "MH-12-9999", "Black");
        
        // Park a bike
        String ticket3 = lot.parkVehicle(VehicleType.BIKE, "MH-14-5555", "Red");

        System.out.println("-------------------------------------------------");
        
        // Display free counts
        lot.displayFreeCount(VehicleType.CAR);
        lot.displayFreeCount(VehicleType.BIKE);

        System.out.println("-------------------------------------------------");
        
        // Unpark a car
        lot.unparkVehicle(ticket1);
        
        System.out.println("-------------------------------------------------");
        
        // Display free counts again to verify it was freed
        lot.displayFreeCount(VehicleType.CAR);
        
        // A new car arrives, it should take the lowest slot (the one we just freed!)
        lot.parkVehicle(VehicleType.CAR, "MH-01-7777", "Blue");
    }
}