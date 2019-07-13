package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;
    private String parkingMessage;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket parkCar(Car car) {
        for (int i = 0; i < parkingLots.size(); i++) {
            int position = parkingLots.get(i).add(car);
            if (position >= 0) {
                return new ParkingTicket(position, i);
            }
        }
        parkingMessage = "Not enough position.";
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

    public String getParkingMessage() {
        return parkingMessage;
    }

    public Car fetchCar() {
        parkingMessage = "Please provide your parking ticket.";
        return null;
    }
}
