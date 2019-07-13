package com.thoughtworks.tdd;

public class ParkingBoy {

    private ParkingLot parkingLot;
    private String parkingMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) {
        return parkingLot.add(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (parkingTicket == null || !parkingLot.isParkingTicketValid(parkingTicket)) {
            parkingMessage = "Unrecognized parking ticket.";
            return null;
        }
        if (!parkingTicket.isUse()) {
            parkingTicket.setUse(true);
            parkingMessage = "";
            return parkingLot.push(parkingTicket);
        }
        else {
            parkingMessage = "Unrecognized parking ticket.";
            return null;
        }
    }

    public String getParkingMessage() {
        return parkingMessage;
    }
}
