package com.example.lld.repository;

import com.example.lld.entities.Ticket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketRepository {
    private Map<String, Ticket> map = new ConcurrentHashMap<>();

    public void addTicket(Ticket ticket) {
        map.put(ticket.getId(), ticket);
    }

    public Ticket getTicket(String id) {
        return map.get(id);
    }

    public void update(Ticket ticket) {
        map.put(ticket.getId(), ticket);
    }
}
