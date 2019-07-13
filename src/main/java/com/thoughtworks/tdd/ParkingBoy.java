package com.thoughtworks.tdd;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) {
        return parkingLot.add(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.push(parkingTicket);
    }
}
