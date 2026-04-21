package com.example.lld.service.impl;

import com.example.lld.entities.ParkingLot;
import com.example.lld.entities.ParkingSpot;
import com.example.lld.entities.Vehicle;
import com.example.lld.service.SpotAllocationStrategy;

public class NearestAllocation implements SpotAllocationStrategy {
    @Override
    public ParkingSpot assignSpot(Vehicle vehicle, ParkingLot parkingLot) {

        return null;
    }
}
