package com.thoughtworks.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Car fetchCar = parkingBoy.fetch(parkingTicket);

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
        Car fetchCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchCar2 = parkingBoy.fetch(parkingTicket2);

        // then
        assertThat(fetchCar1, is(car1));
        assertThat(fetchCar2, is(car2));
    }

}
