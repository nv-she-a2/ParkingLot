package com.example.lld.entities;

import com.example.lld.enums.VehicleType;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ParkingFloor {
    private final String id;
    private final Map<VehicleType, Set<ParkingSpot>> availableSpots;
    private final DisplayBoard displayBoard;

    public ParkingFloor(String id) {
        this.id = id;
        this.displayBoard = new DisplayBoard(id);;
        this.availableSpots = new ConcurrentHashMap<>();
    }

    public void addParkingSpot(ParkingSpot parkingSpot){
        availableSpots.computeIfAbsent(parkingSpot.getType(), k -> new HashSet<>()).add(parkingSpot);
        displayBoard.increment(parkingSpot.getType());
    }

    public List<ParkingSpot> getAvailableSpots(VehicleType vehicle) {
        return new ArrayList<>(availableSpots.getOrDefault(vehicle, Collections.emptySet()));
    }

    public void removeParkingSpot(ParkingSpot parkingSpot){
        Set<ParkingSpot> spots = availableSpots.get(parkingSpot.getType());
        if(spots != null){
            spots.remove(parkingSpot);
            displayBoard.decrement(parkingSpot.getType());
        }
    }
}
