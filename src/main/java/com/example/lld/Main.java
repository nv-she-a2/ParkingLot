package com.example.lld;


import com.example.lld.entities.*;
import com.example.lld.enums.VehicleType;
import com.example.lld.repository.ParkingLotRepository;
import com.example.lld.repository.TicketRepository;
import com.example.lld.service.ParkingLotManager;
import com.example.lld.service.PaymentStrategy;
import com.example.lld.service.impl.FirstAvailableAllocation;
import com.example.lld.service.impl.HourlyCharge;
import com.example.lld.service.impl.NearestAllocation;
import com.example.lld.service.impl.UPIPayment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ParkingLotRepository lotRepo = new ParkingLotRepository();
        TicketRepository ticketRepo = new TicketRepository();

        ParkingLotManager manager = new ParkingLotManager(lotRepo, ticketRepo);

        ParkingLot parkingLot = new ParkingLot("P1");
        manager.createParkingLot(parkingLot);

        manager.addFloor("P1", "F1");

        ParkingFloor floor = parkingLot.getParkingFloors().get("F1");

        ParkingSpot s1 = new ParkingSpot("S1", VehicleType.CAR, floor);
        ParkingSpot s2 = new ParkingSpot("S2", VehicleType.CAR, floor);
        ParkingSpot s3 = new ParkingSpot("S3", VehicleType.BIKE, floor);

        manager.addSpot("P1", "F1", s1);
        manager.addSpot("P1", "F1", s2);
        manager.addSpot("P1", "F1", s3);

        EntryGate entryGate = new EntryGate("G1", new FirstAvailableAllocation());
        Map<VehicleType, Double> charges = new HashMap<VehicleType, Double>();
        charges.put(VehicleType.BIKE, 20.0);
        charges.put(VehicleType.CAR, 30.0);
        ExitGate exitGate = new ExitGate("G2", new HourlyCharge(charges));

        parkingLot.addGate(entryGate);
        parkingLot.addGate(exitGate);

//        Vehicle car1 = new Vehicle("KA01AB1234", VehicleType.CAR);
//        Vehicle car2 = new Vehicle("KA01AB5678", VehicleType.CAR);
//        Vehicle bike1 = new Vehicle("KA01XY1111", VehicleType.BIKE);

        System.out.println("=== Parking Vehicles ===");

//        Ticket t1 = manager.parkVehicle("P1", entryGate.getId(), car1);
//        System.out.println("Ticket1: " + t1.getId());
//
//        Ticket t2 = manager.parkVehicle("P1", entryGate.getId(), car2);
//        System.out.println("Ticket2: " + t2.getId());
//
//        Ticket t3 = manager.parkVehicle("P1", entryGate.getId(), bike1);
//        System.out.println("Ticket3: " + t3.getId());

        // Display Board
        System.out.println("\n=== Display Board After Parking ===");
        floor.getDisplayBoard().show();

        // Exit one vehicle
//        System.out.println("\n=== Exiting Vehicle ===");
//        manager.exitVehicle("P1", exitGate.getId(), t1.getId(), new UPIPayment());
//
//        // Display Board after exit
//        System.out.println("\n=== Display Board After Exit ===");
//        floor.getDisplayBoard().show();

        System.out.println("\n=== Concurrency Test ===");

        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            tasks.add(() -> {
                Vehicle vehicle = new Vehicle("CAR-" + finalI, VehicleType.CAR);
                try {
                    Ticket ticket = manager.parkVehicle("P1", entryGate.getId(), vehicle);
                    System.out.println(Thread.currentThread().getName() +
                            " SUCCESS: " + ticket.getId() +
                            " Spot: " + ticket.getSpot().getId());
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() +
                            " FAILED: " + e.getMessage());
                }
                return null;
            });
        }

        executor.invokeAll(tasks);
        executor.shutdown();
    }
}