package com.abc.hotel.exception;

public class RoomNotAvailable extends RuntimeException {

    public RoomNotAvailable(String message) {
        super(message);
    }
}
