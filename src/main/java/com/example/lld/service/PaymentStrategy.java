package com.example.lld.service;

import com.example.lld.entities.Gate;
import com.example.lld.entities.Payment;
import com.example.lld.enums.PaymentStatus;
import com.example.lld.enums.PaymentType;

public interface PaymentStrategy {
    public PaymentStatus processPayment(Payment payment);

    public PaymentType getPaymentType();
}
