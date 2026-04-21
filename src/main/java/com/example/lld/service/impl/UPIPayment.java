package com.example.lld.service.impl;

import com.example.lld.entities.Payment;
import com.example.lld.enums.PaymentStatus;
import com.example.lld.enums.PaymentType;
import com.example.lld.service.PaymentStrategy;

public class UPIPayment implements PaymentStrategy {
    @Override
    public PaymentStatus processPayment(Payment payment) {
        System.out.println("payment done by UPI for price: " + payment.getPrice());
        payment.markPaymentDone();
        return payment.getStatus();
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.UPI;
    }
}
