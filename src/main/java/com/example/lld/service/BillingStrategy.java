package com.example.lld.service;

import com.example.lld.entities.Ticket;

import java.time.LocalDateTime;

public interface BillingStrategy {
    public double calculatePrice(Ticket ticket, LocalDateTime exitTime);
}
