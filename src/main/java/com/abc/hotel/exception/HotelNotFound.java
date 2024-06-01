package com.abc.hotel.exception;

public class HotelNotFound extends RuntimeException {

    public HotelNotFound(String message) {
        super(message);
    }
}
