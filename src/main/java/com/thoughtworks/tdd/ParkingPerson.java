package com.thoughtworks.tdd;

import java.util.List;

public abstract class ParkingPerson {
    private List<ParkingLot> parkingLots;
    private String parkingMessage;

    public ParkingPerson(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract ParkingTicket parkCar(Car car);

    public String getParkingMessage() {
        return parkingMessage;
    }

    public void setParkingMessage(String parkingMessage) {
        this.parkingMessage = parkingMessage;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Car fetchCar() {
        parkingMessage = "Please provide your parking ticket.";
        return null;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (parkingTicket == null || !parkingLots.get(parkingTicket.getParkingLotId()).isParkingTicketValid(parkingTicket.getPosition())) {
            parkingMessage = "Unrecognized parking ticket.";
            return null;
        }
        if (!parkingTicket.isUse()) {
            parkingTicket.setUse(true);
            parkingMessage = "";
            return parkingLots.get(parkingTicket.getParkingLotId()).push(parkingTicket.getPosition());
        } else {
            parkingMessage = "Unrecognized parking ticket.";
            return null;
        }
    }
}
