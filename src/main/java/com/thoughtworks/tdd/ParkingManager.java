package com.thoughtworks.tdd;

import java.util.List;

public class ParkingManager extends ParkingPerson {

    private List<ParkingPerson> parkingPeople;

    public ParkingManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingManager(List<ParkingLot> parkingLots, List<ParkingPerson> parkingPeople) {
        super(parkingLots);
        this.parkingPeople = parkingPeople;
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        int index = indexOfMaxCapacityRate();
        if (index < 0) {
            setParkingMessage("Not enough position.");
            return null;
        }
        return new ParkingTicket(getParkingLots().get(index).add(car), index);
    }

    public ParkingTicket makeParkingBoyParkCar(int index, Car car) {
        if (parkingPeople != null) {
            ParkingTicket parkingTicket = parkingPeople.get(index).parkCar(car);
            if(parkingTicket == null) {
                setParkingMessage(parkingPeople.get(index).getParkingMessage());
            }
            return parkingTicket;
        }
        return null;
    }

    public Car makeParkingBoyFetchCar(int index, ParkingTicket parkingTicket) {
        if (parkingPeople != null) {
            Car car = parkingPeople.get(index).fetchCar(parkingTicket);
            if(car == null) {
                setParkingMessage(parkingPeople.get(index).getParkingMessage());
            }
            return car;
        }
        return null;
    }

}
