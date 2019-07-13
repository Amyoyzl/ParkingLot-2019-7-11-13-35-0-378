package com.thoughtworks.tdd;

public class ParkingTicket {

    private int id;
    private boolean use = false;

    public ParkingTicket(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }
}
