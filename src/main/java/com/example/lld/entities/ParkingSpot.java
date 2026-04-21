package com.example.lld.entities;

import com.example.lld.enums.SpotStatus;
import com.example.lld.enums.VehicleType;
import lombok.Getter;

@Getter
public class ParkingSpot {
    private final String id;
    private final VehicleType type;
    private SpotStatus status;
    private final ParkingFloor floor;

    public ParkingSpot(String id, VehicleType type, ParkingFloor floor) {
        this.id = id;
        this.type = type;
        this.floor = floor;
        this.status = SpotStatus.AVAILABLE;
    }

    public synchronized boolean reserve(){
        if(this.status == SpotStatus.AVAILABLE) {
            this.status = SpotStatus.OCCUPIED;
            return true;
        }
        return false;
    }

    public synchronized void release() {
        if (this.status == SpotStatus.OCCUPIED) {
            this.status = SpotStatus.AVAILABLE;
        }
    }

    @Override
    public String toString() {
        return "Spot{id=" + id + ", type=" + type + ", status=" + status + "}";
    }
}
