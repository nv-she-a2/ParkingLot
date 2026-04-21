package com.example.lld.service;

import com.example.lld.entities.*;
import com.example.lld.enums.GateStatus;
import com.example.lld.enums.GateType;
import com.example.lld.repository.ParkingLotRepository;
import com.example.lld.repository.TicketRepository;


public class ParkingLotManager {
    private final ParkingLotRepository parkingLotRepository;
    private final TicketRepository ticketRepository;


    public ParkingLotManager(ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public void createParkingLot(ParkingLot parkingLot){
        parkingLotRepository.addParkingLot(parkingLot);
    }

    public void addFloor(String parkingLotId, String floorId){
        ParkingLot parkingLot = getParkingLotById(parkingLotId);
        parkingLot.addParkingFloor(floorId);
    }

    public void addSpot(String parkingLotId, String floorId, ParkingSpot parkingSpot){
        ParkingLot parkingLot = getParkingLotById(parkingLotId);
        ParkingFloor parkingFloor = parkingLot.getParkingFloors().get(floorId);
        if(parkingFloor == null){
            throw new RuntimeException("Floor : " + floorId + "does not exist for parking lot: " + parkingLotId);
        }
        parkingFloor.addParkingSpot(parkingSpot);
    }

    public Ticket parkVehicle(String parkingLotId, String gateId, Vehicle vehicle) {
        ParkingLot parkingLot = getParkingLotById(parkingLotId);
        Gate gate = getGateById(parkingLot, gateId);
        if(gate.getGateType() == GateType.Exit) {
            throw new RuntimeException("Cannot park using exit gate: " + gateId);
        }
        Ticket ticket = ((EntryGate) gate).generateTicket(vehicle, parkingLot);
        if (ticket == null) {
            throw new RuntimeException("Parking Full");
        }
        ticketRepository.addTicket(ticket);
        return ticket;
    }

    public void exitVehicle(String parkingLotId, String gateId, String ticketId, PaymentStrategy paymentStrategy) {
        ParkingLot parkingLot = getParkingLotById(parkingLotId);
        Gate gate = getGateById(parkingLot, gateId);
        if(gate.getGateType() == GateType.Entry) {
            throw new RuntimeException("Cannot exit using entry gate: " + gateId);
        }
        Ticket ticket = ticketRepository.getTicket(ticketId);
        ((ExitGate)gate).processTicket(ticket, paymentStrategy);
    }

    private ParkingLot getParkingLotById(String parkingLotId){
        ParkingLot parkingLot = parkingLotRepository.getParkingLot(parkingLotId);
        if(parkingLot == null){
            throw new RuntimeException("ParkingLot : " + parkingLotId + " does not exist");
        }
        return parkingLot;
    }

    private Gate getGateById(ParkingLot parkingLot, String gateId) {
        Gate gate = parkingLot.getGates().get(gateId);
        if(gate == null || gate.getGateStatus() == GateStatus.CLOSED){
            throw new RuntimeException("Gate : " + gateId + "closed / does not exist for parking lot: " + parkingLot.getId());
        }
        return gate;
    }
}
