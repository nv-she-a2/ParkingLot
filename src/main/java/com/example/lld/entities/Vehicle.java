package com.example.lld.entities;

import com.example.lld.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Vehicle {
    String number;
    VehicleType type;
}
