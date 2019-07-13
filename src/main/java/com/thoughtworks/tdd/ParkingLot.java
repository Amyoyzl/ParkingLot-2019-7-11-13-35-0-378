package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int CAPACITY;
    private List<Car> cars = new ArrayList<>();

    public ParkingLot(int capacity) {
        this.CAPACITY = capacity;
    }

    public ParkingTicket add(Car car) {
        if (cars.size() < CAPACITY) {
            if (cars.add(car)) {
                return new ParkingTicket(cars.size() - 1);
            }
        }
        return null;
    }

    public Car push(ParkingTicket parkingTicket) {
        if (isParkingTicketValid(parkingTicket)) {
            return cars.get(parkingTicket.getId());
        }
        return null;
    }

    public boolean isParkingTicketValid (ParkingTicket parkingTicket) {
        int id = parkingTicket.getId();
        if (id >= 0 && id < cars.size()) {
            return true;
        }
        return false;
    }
}
