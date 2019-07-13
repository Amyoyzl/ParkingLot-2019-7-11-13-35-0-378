package com.thoughtworks.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingBoyTest {

    ParkingLot parkingLot = new ParkingLot(10);
    private ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

    @Test
    @DisplayName("should return a car when parkingBoy fetch car given parkingTicket")
    public void should_return_car_when_parkingBoy_fetch_car_given_parkingTicket() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
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
        ParkingTicket parkingTicket1 = parkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);
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
        ParkingTicket parkingTicket = new ParkingTicket(3);

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
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        Car fetchCar1 = parkingBoy.fetchCar(parkingTicket);
        Car fetchCar2 = parkingBoy.fetchCar(parkingTicket);
        String parkingMessage = parkingBoy.getParkingMessage();

        // then
        assertThat(fetchCar1, is(car));
        assertNull(fetchCar2);
        assertThat(parkingMessage, is("Unrecognized parking ticket."));
    }

    @Test
    @DisplayName("should return no parkingTicket when parkingBoy fetch car has no position in parkingLot")
    public void should_return_no_parkingTicket_when_parkingBoy_fetch_car_has_no_position_in_parkingLot() {
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();
        Car car7 = new Car();
        Car car8 = new Car();
        Car car9 = new Car();
        Car car10 = new Car();
        Car car11 = new Car();

        // when
        parkingBoy.parkCar(car1);
        parkingBoy.parkCar(car2);
        parkingBoy.parkCar(car3);
        parkingBoy.parkCar(car4);
        parkingBoy.parkCar(car5);
        parkingBoy.parkCar(car6);
        parkingBoy.parkCar(car7);
        parkingBoy.parkCar(car8);
        ParkingTicket parkingTicket9 = parkingBoy.parkCar(car9);
        ParkingTicket parkingTicket10 = parkingBoy.parkCar(car10);
        ParkingTicket parkingTicket11 = parkingBoy.parkCar(car11);
        Car fetchCar9 = parkingBoy.fetchCar(parkingTicket9);
        Car fetchCar10 = parkingBoy.fetchCar(parkingTicket10);

        // then
        assertThat(fetchCar9, is(car9));
        assertThat(fetchCar10, is(car10));
        assertNull(parkingTicket11);
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

}
