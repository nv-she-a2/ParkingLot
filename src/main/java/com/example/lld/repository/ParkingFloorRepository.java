package com.example.lld.repository;

import com.example.lld.entities.ParkingFloor;

import java.util.HashMap;
import java.util.Map;

public class ParkingFloorRepository {
    Map<String, ParkingFloor> map = new HashMap<>();

    public void addFloor(ParkingFloor floor){
        map.put(floor.getId(),floor);
    }

    public ParkingFloor getFloorById(String id){
        return map.get(id);
    }
}
