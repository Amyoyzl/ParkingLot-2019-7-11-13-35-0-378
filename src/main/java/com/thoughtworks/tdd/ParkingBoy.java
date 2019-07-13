package com.thoughtworks.tdd;

import java.util.List;

public class ParkingBoy extends ParkingPerson {

    public ParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        for (int i = 0; i < getParkingLots().size(); i++) {
            int position = getParkingLots().get(i).add(car);
            if (position >= 0) {
                return new ParkingTicket(position, i);
            }
        }
        setParkingMessage("Not enough position.");
        return null;
    }

}
