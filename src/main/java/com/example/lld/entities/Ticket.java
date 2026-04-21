package com.example.lld.entities;

import com.example.lld.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class Ticket {
    private final String id;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private ParkingSpot spot;
    private TicketStatus status;
    private Payment payment;

    public Ticket(String id, Vehicle vehicle, LocalDateTime entryTime, ParkingSpot spot) {
        this.id = id;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.spot = spot;
        this.status = TicketStatus.CREATED;
    }

    public void markExit(LocalDateTime exitTime) {
        this.exitTime = exitTime;
        this.status = TicketStatus.CLOSED;
    }

    public Duration getDuration(LocalDateTime exitTime) {
        return Duration.between(entryTime, exitTime);
    }

    public boolean isClosed() {
        return status == TicketStatus.CLOSED;
    }
}
