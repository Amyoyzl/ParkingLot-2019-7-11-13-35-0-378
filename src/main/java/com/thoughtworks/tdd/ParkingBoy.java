package com.thoughtworks.tdd;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) {
        return parkingLot.add(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            return null;
        }
        return parkingLot.push(parkingTicket);
    }
}
