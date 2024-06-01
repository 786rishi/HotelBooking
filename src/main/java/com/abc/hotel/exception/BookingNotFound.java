package com.abc.hotel.exception;

public class BookingNotFound extends RuntimeException {

    public BookingNotFound(String message) {
        super(message);
    }
}
