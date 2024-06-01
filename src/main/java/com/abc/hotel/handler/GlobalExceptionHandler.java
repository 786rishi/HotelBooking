package com.abc.hotel.handler;

import com.abc.hotel.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BookingNotFound.class, UserNotFound.class, HotelNotFound.class, RoomNotFound.class})
    public ResponseEntity handleNotFoundExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse(exception.getMessage()));
    }

    @ExceptionHandler({RoomNotAvailable.class})
    public ResponseEntity handleConflictsExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse(exception.getMessage()));
    }

    @ExceptionHandler({ActionNotPermitted.class})
    public ResponseEntity handleNotAllowedExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse(exception.getMessage()));
    }

    private Map<String, String> errorResponse(String response) {
        return Map.of("message", response);
    }

}
