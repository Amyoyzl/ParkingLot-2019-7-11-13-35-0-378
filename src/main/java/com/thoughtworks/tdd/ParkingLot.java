package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final int CAPACITY;
    private List<Car> cars = new ArrayList<>();

    public ParkingLot(int capacity) {
        this.CAPACITY = capacity;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public int add(Car car) {
        if (getAvailableCapacity() > 0) {
            if (cars.add(car)) {
                return cars.size() - 1;
            }
        }
        return -1;
    }

    public int getAvailableCapacity() {
        return CAPACITY - cars.size();
    }

    public Car push(int position) {
        if (isParkingTicketValid(position)) {
            return cars.get(position);
        }
        return null;
    }

    public boolean isParkingTicketValid (int position) {
        if (position >= 0 && position < cars.size()) {
            return true;
        }
        return false;
    }


}
