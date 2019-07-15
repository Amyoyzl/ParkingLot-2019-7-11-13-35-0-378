package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.NotEnoughPositionException;
import com.thoughtworks.tdd.excception.TicketMissingException;
import com.thoughtworks.tdd.excception.UnrecognizedTicketException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {

    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public Ticket park(Car car) {
        boolean isAllFull = parkingLots.stream().allMatch(parkingLot -> parkingLot.getAvailablePosition() == 0);
        if (isAllFull) {
            throw new NotEnoughPositionException();
        }
        ParkingLot firstAvailableParkingLot = parkingLots.stream().max(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                return o1.getAvailablePosition() - o2.getAvailablePosition();
            }
        }).get();
        return firstAvailableParkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            throw new TicketMissingException();
        }
        boolean isContainTicket = parkingLots.stream().allMatch(parkingLot -> parkingLot.isContainTicket(ticket));
        if (isContainTicket) {
            ParkingLot firstAvailableParkingLot = parkingLots.stream().filter(parkingLot -> parkingLot.isContainTicket(ticket)).findFirst().get();
            return firstAvailableParkingLot.fetch(ticket);
        }
        throw new UnrecognizedTicketException();
    }

}
