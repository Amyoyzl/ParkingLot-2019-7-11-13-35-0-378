package com.thoughtworks.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingManagerTest {

    ParkingManager manager;

    public ParkingManagerTest() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(4));
        parkingLots.add(new ParkingLot(6));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        List<ParkingPerson> parkingPeople = new ArrayList<>();
        parkingPeople.add(parkingBoy);
        parkingPeople.add(smartParkingBoy);
        parkingPeople.add(superSmartParkingBoy);
        manager = new ParkingManager(parkingLots, parkingPeople);
    }

    @Test
    public void should_return_car_when_manager_make_parkingBoy_fetch_car_given_parkingTicket() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = manager.makeParkingBoyParkCar(0, car);
        Car fetchCar = manager.makeParkingBoyFetchCar(1, parkingTicket);

        // then
        assertThat(fetchCar, is(car));
    }

    @Test
    public void should_return_no_position_message_when_parkingBoy_park_car_has_no_position_in_parkingLot() {
        // given
        for (int i = 0; i < 19; i++) {
            manager.makeParkingBoyParkCar(0, new Car());
        }
        Car car20 = new Car();
        Car car21 = new Car();

        // when
        ParkingTicket parkingTicket20 = manager.makeParkingBoyParkCar(1, car20);
        ParkingTicket parkingTicket21 = manager.makeParkingBoyParkCar(2, car21);
        String parkingMessage = manager.getParkingMessage();
        Car fetchCar20 = manager.makeParkingBoyFetchCar(0, parkingTicket20);

        // then
        assertThat(fetchCar20, is(car20));
        assertNull(parkingTicket21);
        assertThat(parkingMessage, is("Not enough position."));

    }

    @Test
    public void should_return_no_car_when_parkingBoy_fetch_car_given_wrong_parkingTicket() {
        // given
        ParkingTicket parkingTicket = new ParkingTicket(3, 0);

        // when
        Car fetchCar = manager.makeParkingBoyFetchCar(1, parkingTicket);
        String parkingMessage = manager.getParkingMessage();

        // then
        assertNull(fetchCar);
        assertThat(parkingMessage, is("Unrecognized parking ticket."));
    }


    @Test
    public void should_return_car_when_manager_fetch_car_given_parkingTicket() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = manager.parkCar(car);
        Car fetchCar = manager.fetchCar(parkingTicket);

        // then
        assertThat(fetchCar, is(car));

    }

    @Test
    public void should_return_cars_when_manager_fetch_cars_given_parkingTickets() {
        // given
        Car car1 = new Car();
        Car car2 = new Car();

        // when
        ParkingTicket parkingTicket1 = manager.parkCar(car1);
        ParkingTicket parkingTicket2 = manager.parkCar(car2);
        Car fetchCar1 = manager.fetchCar(parkingTicket1);
        Car fetchCar2 = manager.fetchCar(parkingTicket2);

        // then
        assertThat(fetchCar1, is(car1));
        assertThat(fetchCar2, is(car2));
    }

    @Test
    public void should_return_no_car_when_manager_fetch_car_given_wrong_parkingTicket() {
        // given
        ParkingTicket parkingTicket = new ParkingTicket(3, 0);

        // when
        Car fetchCar = manager.fetchCar(parkingTicket);
        String parkingMessage = manager.getParkingMessage();

        // then
        assertNull(fetchCar);
        assertThat(parkingMessage, is("Unrecognized parking ticket."));
    }

    @Test
    public void should_return_no_car_when_manager_fetch_car_given_used_parkingTicket() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = manager.parkCar(car);
        Car fetchCar1 = manager.fetchCar(parkingTicket);
        Car fetchCar2 = manager.fetchCar(parkingTicket);
        String parkingMessage = manager.getParkingMessage();

        // then
        assertThat(fetchCar1, is(car));
        assertNull(fetchCar2);
        assertThat(parkingMessage, is("Unrecognized parking ticket."));
    }

    @Test
    public void should_return_no_parkingTicket_when_manager_park_car_has_no_position_in_parkingLot() {
        // given
        for (int i = 0; i < 19; i++) {
            manager.parkCar(new Car());
        }
        Car car20 = new Car();
        Car car21 = new Car();

        // when
        ParkingTicket parkingTicket20 = manager.parkCar(car20);
        ParkingTicket parkingTicket21 = manager.parkCar(car21);
        Car fetchCar20 = manager.fetchCar(parkingTicket20);

        // then
        assertThat(fetchCar20, is(car20));
        assertNull(parkingTicket21);
    }

    @Test
    public void should_return_provide_ticket_when_manager_fetch_car_given_no_parkingTicket() {
        // given
        // when
        Car fetchCar = manager.fetchCar();
        String parkingMessage = manager.getParkingMessage();

        // then
        assertNull(fetchCar);
        assertThat(parkingMessage, is("Please provide your parking ticket."));
    }

    @Test
    public void should_return_no_position_message_when_manager_park_car_has_no_position_in_parkingLot() {
        // given
        for (int i = 0; i < 19; i++) {
            manager.parkCar(new Car());
        }
        Car car20 = new Car();
        Car car21 = new Car();

        // when
        ParkingTicket parkingTicket20 = manager.parkCar(car20);
        ParkingTicket parkingTicket21 = manager.parkCar(car21);
        String parkingMessage = manager.getParkingMessage();
        Car fetchCar20 = manager.fetchCar(parkingTicket20);

        // then
        assertThat(fetchCar20, is(car20));
        assertNull(parkingTicket21);
        assertThat(parkingMessage, is("Not enough position."));

    }

    @Test
    public void should_return_cars_when_manager_fetch_cars_in_parkingLots_given_parkingTicket() {
        // given
        for (int i = 0; i < 9; i++) {
            manager.parkCar(new Car());
        }
        Car car10 = new Car();
        Car car11 = new Car();

        // when
        ParkingTicket parkingTicket10 = manager.parkCar(car10);
        ParkingTicket parkingTicket11 = manager.parkCar(car11);
        Car fetchCar10 = manager.fetchCar(parkingTicket10);
        Car fetchCar11 = manager.fetchCar(parkingTicket11);

        // then
        assertThat(fetchCar10, is(car10));
        assertThat(fetchCar11, is(car11));
    }

}
