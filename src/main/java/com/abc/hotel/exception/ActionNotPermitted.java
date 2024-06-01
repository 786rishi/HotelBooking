package com.abc.hotel.exception;

public class ActionNotPermitted extends RuntimeException {

    public ActionNotPermitted(String message) {
        super(message);
    }
}
