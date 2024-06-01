package com.abc.hotel.exception;

public class RoomNotFound extends RuntimeException {

    public RoomNotFound(String message) {
        super(message);
    }
}
