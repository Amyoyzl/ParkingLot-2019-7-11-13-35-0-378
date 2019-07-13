package com.thoughtworks.tdd;

import java.util.List;

public class SmartParkingBoy extends ParkingPerson {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        int index = indexOfMaxCapacity();
        if(index < 0) {
            setParkingMessage("Not enough position.");
            return null;
        }
        return new ParkingTicket(getParkingLots().get(index).add(car), index);
    }

    private int indexOfMaxCapacity() {
        int max = 0;
        int index = -1;
        for (int i = 0; i < getParkingLots().size(); i++) {
            int capacity = getParkingLots().get(i).getAvailableCapacity();
            if(capacity > max) {
                max = capacity;
                index = i;
            }
        }
        return index;
    }
}
