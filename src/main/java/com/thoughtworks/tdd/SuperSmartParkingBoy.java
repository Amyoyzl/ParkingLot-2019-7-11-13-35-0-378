package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.NotEnoughPositionException;
import com.thoughtworks.tdd.excception.TicketMissingException;
import com.thoughtworks.tdd.excception.UnrecognizedTicketException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy {

    private List<ParkingLot> parkingLots;

    public SuperSmartParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public Ticket parkCar(Car car) {
        boolean isAllFull = parkingLots.stream().allMatch(parkingLot -> parkingLot.getAvailablePosition() == 0);
        if (isAllFull) {
            throw new NotEnoughPositionException();
        }
        ParkingLot firstAvailableParkingLot = parkingLots.stream().max(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                return o1.getAvailablePositionRate() > o2.getAvailablePositionRate() ? 1 : -1;
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
