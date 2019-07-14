package com.thoughtworks.tdd;

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
        List<ParkingLot> parkingLots1 = new ArrayList<>();
        parkingLots1.add(new ParkingLot(10));
        parkingLots1.add(new ParkingLot(4));
        parkingLots1.add(new ParkingLot(6));
        List<ParkingLot> parkingLots2 = new ArrayList<>();
        parkingLots2.add(new ParkingLot(8));
        parkingLots2.add(new ParkingLot(4));
        parkingLots2.add(new ParkingLot(8));
        List<ParkingLot> parkingLots3 = new ArrayList<>();
        parkingLots3.add(new ParkingLot(6));
        parkingLots3.add(new ParkingLot(8));
        parkingLots3.add(new ParkingLot(6));
        parkingLots.addAll(parkingLots1);
        parkingLots.addAll(parkingLots2);
        parkingLots.addAll(parkingLots3);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots3);
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
        Car fetchCar = manager.makeParkingBoyFetchCar(0, parkingTicket);

        // then
        assertThat(fetchCar, is(car));
    }

    @Test
    public void should_return_no_position_message_when_parkingBoy_park_car_has_no_position_in_parkingLot() {
        // given
        for (int i = 0; i < 19; i++) {
            manager.makeParkingBoyParkCar(1, new Car());
        }
        Car car20 = new Car();
        Car car21 = new Car();

        // when
        ParkingTicket parkingTicket20 = manager.makeParkingBoyParkCar(1, car20);
        ParkingTicket parkingTicket21 = manager.makeParkingBoyParkCar(1, car21);
        String parkingMessage = manager.getParkingMessage();
        Car fetchCar20 = manager.makeParkingBoyFetchCar(1, parkingTicket20);

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
        for (int i = 0; i < 59; i++) {
            manager.parkCar(new Car());
        }
        Car car60 = new Car();
        Car car61 = new Car();

        // when
        ParkingTicket parkingTicket60 = manager.parkCar(car60);
        ParkingTicket parkingTicket61 = manager.parkCar(car61);
        Car fetchCar60 = manager.fetchCar(parkingTicket60);

        // then
        assertThat(fetchCar60, is(car60));
        assertNull(parkingTicket61);
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
        for (int i = 0; i < 59; i++) {
            manager.parkCar(new Car());
        }
        Car car60 = new Car();
        Car car61 = new Car();

        // when
        ParkingTicket parkingTicket60 = manager.parkCar(car60);
        ParkingTicket parkingTicket61 = manager.parkCar(car61);
        String parkingMessage = manager.getParkingMessage();
        Car fetchCar60 = manager.fetchCar(parkingTicket60);

        // then
        assertThat(fetchCar60, is(car60));
        assertNull(parkingTicket61);
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
