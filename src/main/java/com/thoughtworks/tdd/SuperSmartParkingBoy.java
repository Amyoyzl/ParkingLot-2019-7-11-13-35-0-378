package com.thoughtworks.tdd;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingPerson {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        int index = indexOfMaxCapacityRate();
        if(index < 0) {
            setParkingMessage("Not enough position.");
            return null;
        }
        return new ParkingTicket(getParkingLots().get(index).add(car), index);
    }

}
