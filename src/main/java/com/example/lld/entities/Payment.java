package com.example.lld.entities;

import com.example.lld.enums.PaymentStatus;
import lombok.Getter;

@Getter
public class Payment {
    private final String id;
    private final Double price;
    private PaymentStatus status;

    public Payment(String id, Double price) {
        this.id = id;
        this.price = price;
        this.status = PaymentStatus.Pending;
    }

    public void markPaymentDone() {
        this.status = PaymentStatus.Paid;
    }
}
