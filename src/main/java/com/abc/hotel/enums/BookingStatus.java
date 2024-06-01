package com.abc.hotel.enums;

public enum BookingStatus {

    BOOKED("Booked"),
    CANCELLED("Canceled");

    private String status;

    private BookingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
