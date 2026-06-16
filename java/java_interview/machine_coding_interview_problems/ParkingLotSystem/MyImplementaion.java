package java_interview.machine_coding_interview_problems.ParkingLotSystem;

/*
- because we need to store the floors and slots for each floor and 
each vehicleType, map as key for floor and then nested map for each of the
vehicle type and then value to the inner map is slots withing queue

- when we peek from a queue we see if it is taken if yes then we print 
that we look for another floor, if not then we allot it, poll it and then 
again add in the back of the queue
*/

import java.util.*;

enum VehicleType {
  CAR, BIKE, TRUCK
}

class Slot {
  int slotNo;
  boolean taken;

  public Slot(int slotNo) {
    this.slotNo = slotNo;
    this.taken = false;
  }
}

class ParkingService {
  Map<Integer, Map<VehicleType, Deque<Slot>>> lotDB;
  Map<Integer, Map<VehicleType, Deque<Slot>>> takenDB;

  int nFloors;
  int nSlots;

  public ParkingService(int nFloors, int nSlots) {
    this.nFloors = nFloors;
    this.nSlots = nSlots;

    this.lotDB = new HashMap<>();
    this.takenDB = new HashMap<>();

    for(int i = 1; i <= nFloors; i++) {
      lotDB.put(i, new HashMap<>());
      takenDB.put(i, new HashMap<>());

      for(VehicleType vType : VehicleType.values()) {
        lotDB.get(i).put(vType, new ArrayDeque<>());
        takenDB.get(i).put(vType, new ArrayDeque<>());
      }
      
      for(int j = 1; j <= nSlots; j++) {
        VehicleType type;
        if(j == 1) type = VehicleType.TRUCK;
        else if(j == 2 || j == 3) type = VehicleType.BIKE;
        else type = VehicleType.CAR;

        lotDB.get(i).get(type).offer(new Slot(j));
      }
    }
  }

  public Slot parkVehicle(VehicleType type) {
    for(int i = 1; i <= nFloors; i++) {
      Deque<Slot> freeSlots = lotDB.get(i).get(type);

      if(!freeSlots.isEmpty()) {
        Slot allotedSlot = freeSlots.poll();
        takenDB.get(i).get(type).offer(allotedSlot);
        System.out.println("Slot alloted Successfully!");
        return allotedSlot;
      } 
    }

    System.out.println("Parking Lot Full!");
    return null;
  }

  public void unparkVehicle(VehicleType type, int floorNo) {
    Deque<Slot> slots = takenDB.get(floorNo).get(type);

    if(!slots.isEmpty()) {
      Slot allotedSlot = slots.poll();
      lotDB.get(floorNo).get(type).offer(allotedSlot);
      
      System.out.println("Vehicle unparked Successfully!");
      return;
    }
  }

  public Map<Integer, Integer> showSlotsAvailablePerFloor(VehicleType type) {
    Map<Integer, Integer> mp =new HashMap<>();
    for(int i =1; i <= nFloors; i++) {
      List<Slot> freeSlots = lotDB.get(i).get(type).stream().toList();
      mp.put(i, freeSlots.size());
    }

    return mp;
  }
}

public class MyImplementaion {
  public static void main(String[] args) {
    ParkingService service = new ParkingService(1, 10);

    service.parkVehicle(VehicleType.CAR);
    System.out.println(service.showSlotsAvailablePerFloor(VehicleType.CAR));
  }
}