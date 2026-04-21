package com.example.lld.repository;

import com.example.lld.entities.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    Map<String, ParkingLot> map = new HashMap<>();

    public void addParkingLot(ParkingLot parkingLot){
        map.put(parkingLot.getId(), parkingLot);
    }

    public ParkingLot getParkingLot(String parkingLotId){
        if(!map.containsKey(parkingLotId))
            return null;
        return map.get(parkingLotId);
    }
}
