package com.example.lld.entities;

import com.example.lld.enums.VehicleType;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class DisplayBoard {
    private final String floorId;
    private final Map<VehicleType, Integer> availableCount;

    public DisplayBoard(String floorId) {
        this.floorId = floorId;
        this.availableCount = new ConcurrentHashMap<>();
    }

    public void updateCount(VehicleType type, int count) {
        availableCount.put(type, count);
    }

    public void increment(VehicleType type) {
        availableCount.merge(type, 1, Integer::sum);
    }

    public void decrement(VehicleType type) {
        availableCount.computeIfPresent(type, (k, v) -> v > 0 ? v - 1 : 0);
    }

    public void show() {
        System.out.println("DisplayBoard for Floor: " + floorId);
        for (Map.Entry<VehicleType, Integer> entry : availableCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
