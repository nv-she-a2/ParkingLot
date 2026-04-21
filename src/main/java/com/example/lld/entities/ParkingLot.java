package com.example.lld.entities;

import com.example.lld.enums.VehicleType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ParkingLot {
    private final String id;
    private final ConcurrentHashMap<String, ParkingFloor> parkingFloors;
    private final ConcurrentHashMap<String, Gate> gates;

    public ParkingLot(String id) {
        this.id = id;
        this.parkingFloors = new ConcurrentHashMap<>();
        this.gates = new ConcurrentHashMap<>();
    }

    public void addParkingFloor(String name) {
        ParkingFloor parkingFloor = new ParkingFloor(name);
        parkingFloors.put(name, parkingFloor);
    }

    public void addGate(Gate gate) {
        gates.put(gate.getId(), gate);
    }

    public List<ParkingSpot> getAvailableSpots(VehicleType vehicleType) {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for(ParkingFloor parkingFloor: parkingFloors.values()) {
            availableSpots.addAll(parkingFloor.getAvailableSpots(vehicleType));
        }
        return availableSpots;
    }
}
