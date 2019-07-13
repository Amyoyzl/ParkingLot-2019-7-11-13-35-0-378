package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int CAPACITY;
    private Car car;

    public ParkingLot(int capacity) {
        this.CAPACITY = capacity;
    }

    public ParkingTicket add(Car car) {
        this.car = car;
        return new ParkingTicket();
    }

    public Car push(ParkingTicket parkingTicket) {
        return this.car;
    }
}
