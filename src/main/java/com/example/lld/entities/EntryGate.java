package com.example.lld.entities;

import com.example.lld.enums.GateType;
import com.example.lld.service.SpotAllocationStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class EntryGate extends Gate {
    private final SpotAllocationStrategy spotAllocationStrategy;

    public EntryGate(String id, SpotAllocationStrategy spotAllocationStrategy) {
        super(id, GateType.Entry);
        this.spotAllocationStrategy = spotAllocationStrategy;
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingLot parkingLot) {

        ParkingSpot spot =  spotAllocationStrategy.assignSpot(vehicle, parkingLot);

        if(spot == null){
            throw new RuntimeException("No spot available");
        }
        return new Ticket(
                UUID.randomUUID().toString(),
                vehicle,
                LocalDateTime.now(),
                spot);
    }
}
