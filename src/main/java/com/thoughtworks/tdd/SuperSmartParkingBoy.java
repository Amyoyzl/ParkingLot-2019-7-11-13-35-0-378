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

    public int indexOfMaxCapacityRate() {
        double max = 0;
        int index = -1;
        for (int i = 0; i < getParkingLots().size(); i++) {
            double capacityRate = (double)getParkingLots().get(i).getAvailableCapacity() / getParkingLots().get(i).getCAPACITY();
            if(capacityRate > max) {
                max = capacityRate;
                index = i;
            }
        }
        return index;
    }
}
