package com.example.lld.service.impl;

import com.example.lld.entities.ParkingLot;
import com.example.lld.entities.ParkingSpot;
import com.example.lld.entities.Vehicle;
import com.example.lld.service.SpotAllocationStrategy;

import java.util.List;

public class FirstAvailableAllocation implements SpotAllocationStrategy {

    @Override
    public ParkingSpot assignSpot(Vehicle vehicle, ParkingLot parkingLot) {
        List<ParkingSpot> availableSpots = parkingLot.getAvailableSpots(vehicle.getType());
        for(ParkingSpot spot : availableSpots) {
            if(spot.reserve()) {
                spot.getFloor().removeParkingSpot(spot);
                return spot;
            }
        }
        return null;
    }
}
