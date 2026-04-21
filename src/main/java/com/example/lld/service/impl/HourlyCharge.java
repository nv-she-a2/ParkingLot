package com.example.lld.service.impl;

import com.example.lld.entities.Ticket;
import com.example.lld.enums.VehicleType;
import com.example.lld.service.BillingStrategy;

import java.time.LocalDateTime;
import java.util.Map;

public class HourlyCharge implements BillingStrategy {
    private final Map<VehicleType, Double> chargesPerHour;

    public HourlyCharge(Map<VehicleType, Double> chargesPerHour) {
        this.chargesPerHour = chargesPerHour;
    }

    @Override
    public double calculatePrice(Ticket ticket, LocalDateTime exitTime) {
        long duration = ticket.getDuration(exitTime).toHours();
        VehicleType type = ticket.getVehicle().getType();
        return (duration + 1) * chargesPerHour.get(type);
    }
}
