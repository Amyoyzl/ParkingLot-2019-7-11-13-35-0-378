package com.thoughtworks.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingBoyTest {

    ParkingBoy parkingBoy;
    ParkingBoy smartParkingBoy;

    public ParkingBoyTest () {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        parkingBoy = new ParkingBoy(parkingLots);
        List<ParkingLot> parkingLots1 = new ArrayList<>();
        parkingLots1.add(new ParkingLot(2));
        parkingLots1.add(new ParkingLot(2));
        parkingLots1.add(new ParkingLot(1));
        smartParkingBoy = new ParkingBoy(parkingLots1);
    }

    @Test
    @DisplayName("should return a car when parkingBoy fetch car given parkingTicket")
    public void should_return_car_when_parkingBoy_fetch_car_given_parkingTicket() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = parkingBoy.parkCarByOrder(car);
        Car fetchCar = parkingBoy.fetchCar(parkingTicket);

        // then
        assertThat(fetchCar, is(car));

    }

    @Test
    @DisplayName("should return cars when parkingBoy fetch cars given parkingTickets")
    public void should_return_cars_when_parkingBoy_fetch_cars_given_parkingTickets() {
        // given
        Car car1 = new Car();
        Car car2 = new Car();

        // when
        ParkingTicket parkingTicket1 = parkingBoy.parkCarByOrder(car1);
        ParkingTicket parkingTicket2 = parkingBoy.parkCarByOrder(car2);
        Car fetchCar1 = parkingBoy.fetchCar(parkingTicket1);
        Car fetchCar2 = parkingBoy.fetchCar(parkingTicket2);

        // then
        assertThat(fetchCar1, is(car1));
        assertThat(fetchCar2, is(car2));
    }

    @Test
    @DisplayName("should return no car when parkingBoy fetch car given wrong parkingTicket")
    public void should_return_no_car_when_parkingBoy_fetch_car_given_wrong_parkingTicket() {
        // given
        ParkingTicket parkingTicket = new ParkingTicket(3, 0);

        // when
        Car fetchCar = parkingBoy.fetchCar(parkingTicket);
        String parkingMessage = parkingBoy.getParkingMessage();

        // then
        assertNull(fetchCar);
        assertThat(parkingMessage, is("Unrecognized parking ticket."));
    }

    @Test
    @DisplayName("should return no car when parkingBoy fetch car given used parkingTicket")
    public void should_return_no_car_when_parkingBoy_fetch_car_given_used_parkingTicket() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = parkingBoy.parkCarByOrder(car);
        Car fetchCar1 = parkingBoy.fetchCar(parkingTicket);
        Car fetchCar2 = parkingBoy.fetchCar(parkingTicket);
        String parkingMessage = parkingBoy.getParkingMessage();

        // then
        assertThat(fetchCar1, is(car));
        assertNull(fetchCar2);
        assertThat(parkingMessage, is("Unrecognized parking ticket."));
    }

    @Test
    @DisplayName("should return no parkingTicket when parkingBoy park car has no position in parkingLot")
    public void should_return_no_parkingTicket_when_parkingBoy_park_car_has_no_position_in_parkingLot() {
        // given
        for (int i = 0; i < 19; i++) {
            parkingBoy.parkCarByOrder(new Car());
        }
        Car car20 = new Car();
        Car car21 = new Car();

        // when
        ParkingTicket parkingTicket20 = parkingBoy.parkCarByOrder(car20);
        ParkingTicket parkingTicket21 = parkingBoy.parkCarByOrder(car21);
        Car fetchCar20 = parkingBoy.fetchCar(parkingTicket20);

        // then
        assertThat(fetchCar20, is(car20));
        assertNull(parkingTicket21);
    }

    @Test
    @DisplayName("should return provide ticket message when parkingBoy fetch car given no parkingTicket")
    public void should_return_provide_ticket_when_parkingBoy_fetch_car_given_no_parkingTicket() {
        // given
        // when
        Car fetchCar = parkingBoy.fetchCar();
        String parkingMessage = parkingBoy.getParkingMessage();

        // then
        assertNull(fetchCar);
        assertThat(parkingMessage, is("Please provide your parking ticket."));
    }

    @Test
    @DisplayName("should return no position message ticket message when parkingBoy park car has no position in parkingLot")
    public void should_return_no_position_message_when_parkingBoy_park_car_has_no_position_in_parkingLot() {
        // given
        for (int i = 0; i < 19; i++) {
            parkingBoy.parkCarByOrder(new Car());
        }
        Car car20 = new Car();
        Car car21 = new Car();

        // when
        ParkingTicket parkingTicket20 = parkingBoy.parkCarByOrder(car20);
        ParkingTicket parkingTicket21 = parkingBoy.parkCarByOrder(car21);
        String parkingMessage = parkingBoy.getParkingMessage();
        Car fetchCar20 = parkingBoy.fetchCar(parkingTicket20);

        // then
        assertThat(fetchCar20, is(car20));
        assertNull(parkingTicket21);
        assertThat(parkingMessage, is("Not enough position."));

    }

    @Test
    public void should_return_cars_when_parkingBoy_fetch_cars_in_parkingLots_given_parkingTicket() {
        // given
        for (int i = 0; i < 9; i++) {
            parkingBoy.parkCarByOrder(new Car());
        }
        Car car10 = new Car();
        Car car11 = new Car();

        // when
        ParkingTicket parkingTicket10 = parkingBoy.parkCarByOrder(car10);
        ParkingTicket parkingTicket11 = parkingBoy.parkCarByOrder(car11);
        Car fetchCar10 = parkingBoy.fetchCar(parkingTicket10);
        Car fetchCar11 = parkingBoy.fetchCar(parkingTicket11);

        // then
        assertThat(fetchCar10, is(car10));
        assertThat(fetchCar11, is(car11));
    }

    @Test
    public void should_return_car_when_smartParkingBoy_fetch_car_given_parkingTicket() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = smartParkingBoy.parkCarByCapacity(car);
        Car fetchCar = smartParkingBoy.fetchCar(parkingTicket);

        // then
        assertThat(fetchCar, is(car));
    }

    @Test
    public void should_return_cars_when_smartParkingBoy_fetch_cars_given_parkingTickets() {
        // given
        Car car1 = new Car();
        Car car2 = new Car();

        // when
        ParkingTicket parkingTicket1 = smartParkingBoy.parkCarByCapacity(car1);
        ParkingTicket parkingTicket2 = smartParkingBoy.parkCarByCapacity(car2);
        Car fetchCar1 = smartParkingBoy.fetchCar(parkingTicket1);
        Car fetchCar2 = smartParkingBoy.fetchCar(parkingTicket2);

        // then
        assertThat(fetchCar1, is(car1));
        assertThat(fetchCar2, is(car2));
    }

    @Test
    public void should_return_no_car_when_smartParkingBoy_fetch_car_given_wrong_parkingTicket() {
        // given
        ParkingTicket parkingTicket = new ParkingTicket(3, 0);

        // when
        Car fetchCar = smartParkingBoy.fetchCar(parkingTicket);
        String parkingMessage = smartParkingBoy.getParkingMessage();

        // then
        assertNull(fetchCar);
        assertThat(parkingMessage, is("Unrecognized parking ticket."));
    }

    @Test
    public void should_return_no_car_when_smartParkingBoy_fetch_car_given_used_parkingTicket() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = smartParkingBoy.parkCarByCapacity(car);
        Car fetchCar1 = smartParkingBoy.fetchCar(parkingTicket);
        Car fetchCar2 = smartParkingBoy.fetchCar(parkingTicket);
        String parkingMessage = smartParkingBoy.getParkingMessage();

        // then
        assertThat(fetchCar1, is(car));
        assertNull(fetchCar2);
        assertThat(parkingMessage, is("Unrecognized parking ticket."));
    }

    @Test
    public void should_return_no_parkingTicket_when_smartParkingBoy_park_car_has_no_position_in_parkingLot() {
        // given
        for (int i = 0; i < 4; i++) {
            smartParkingBoy.parkCarByCapacity(new Car());
        }
        Car car5 = new Car();
        Car car6 = new Car();

        // when
        ParkingTicket parkingTicket5 = smartParkingBoy.parkCarByCapacity(car5);
        ParkingTicket parkingTicket6 = smartParkingBoy.parkCarByCapacity(car6);
        Car fetchCar5 = smartParkingBoy.fetchCar(parkingTicket5);

        // then
        assertThat(fetchCar5, is(car5));
        assertNull(parkingTicket6);
    }

    @Test
    public void should_return_provide_ticket_when_smartParkingBoy_fetch_car_given_no_parkingTicket() {
        // given
        // when
        Car fetchCar = smartParkingBoy.fetchCar();
        String parkingMessage = smartParkingBoy.getParkingMessage();

        // then
        assertNull(fetchCar);
        assertThat(parkingMessage, is("Please provide your parking ticket."));
    }

    @Test
    public void should_return_no_position_message_when_smartParkingBoy_park_car_has_no_position_in_parkingLot() {
        // given
        for (int i = 0; i < 4; i++) {
            smartParkingBoy.parkCarByCapacity(new Car());
        }
        Car car5 = new Car();
        Car car6 = new Car();

        // when
        ParkingTicket parkingTicket5 = smartParkingBoy.parkCarByCapacity(car5);
        ParkingTicket parkingTicket6 = smartParkingBoy.parkCarByCapacity(car6);
        String parkingMessage = smartParkingBoy.getParkingMessage();
        Car fetchCar5 = smartParkingBoy.fetchCar(parkingTicket5);

        // then
        assertThat(fetchCar5, is(car5));
        assertNull(parkingTicket6);
        assertThat(parkingMessage, is("Not enough position."));

    }



}
