package com.example.lld.entities;

import com.example.lld.enums.GateType;
import com.example.lld.enums.PaymentStatus;
import com.example.lld.repository.TicketRepository;
import com.example.lld.service.BillingStrategy;
import com.example.lld.service.PaymentStrategy;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExitGate extends Gate {
    private final BillingStrategy billingStrategy;
    private TicketRepository ticketRepository;

    public ExitGate(String id, BillingStrategy billingStrategy) {
        super(id, GateType.Exit);
        this.billingStrategy = billingStrategy;
    }

    public void processTicket(Ticket ticket, PaymentStrategy paymentStrategy) {

        if (ticket.isClosed()) return;

        LocalDateTime now = LocalDateTime.now();
        Double price = billingStrategy.calculatePrice(ticket, now);

        Payment payment = new Payment(UUID.randomUUID().toString(), price);
        PaymentStatus status = paymentStrategy.processPayment(payment);

        if(status != PaymentStatus.Paid) {
            throw new RuntimeException("Payment failed for ticket: " + ticket.getId());
        }
        ticket.markExit(now);
        ticket.setPayment(payment);

        ParkingSpot spot = ticket.getSpot();
        spot.release();
        spot.getFloor().addParkingSpot(spot);
    }

}
