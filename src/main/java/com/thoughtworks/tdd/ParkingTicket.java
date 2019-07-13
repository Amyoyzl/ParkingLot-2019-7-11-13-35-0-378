package com.thoughtworks.tdd;

public class ParkingTicket {

    private int position;
    private int parkingLotId;
    private boolean use = false;

    public int getParkingLotId() {
        return parkingLotId;
    }

    public ParkingTicket(int position, int parkingLotId) {
        this.position = position;
        this.parkingLotId = parkingLotId;
    }

    public int getPosition() {
        return position;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }
}
