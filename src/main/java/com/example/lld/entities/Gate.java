package com.example.lld.entities;

import com.example.lld.enums.GateStatus;
import com.example.lld.enums.GateType;
import lombok.Getter;

@Getter
public abstract class Gate {
    private final String id;
    private GateType gateType;
    private GateStatus gateStatus;

    protected Gate(String id, GateType gateType) {
        this.id = id;
        this.gateType = gateType;
    }

    public void openGate(){
        this.gateStatus = GateStatus.OPEN;
    };

    public void closeGate(){
        this.gateStatus = GateStatus.CLOSED;
    };
}
