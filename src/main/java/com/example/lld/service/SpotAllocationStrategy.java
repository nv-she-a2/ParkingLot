package com.example.lld.service;

import com.example.lld.entities.ParkingLot;
import com.example.lld.entities.ParkingSpot;
import com.example.lld.entities.Vehicle;

import java.util.List;

public interface SpotAllocationStrategy {
    public ParkingSpot assignSpot(Vehicle vehicle, ParkingLot parkingLot);
}
