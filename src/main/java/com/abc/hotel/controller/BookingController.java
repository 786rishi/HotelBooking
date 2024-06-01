package com.abc.hotel.controller;

import com.abc.hotel.dto.BookingResponseDTO;
import com.abc.hotel.entity.Booking;
import com.abc.hotel.dto.BookingRequestDTO;
import com.abc.hotel.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/*
* This controller is responsible for Bookings
* Creating/Cancel and Fetch Booking details
* */
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/room")
    public ResponseEntity<BookingResponseDTO> bookRoom(@RequestBody BookingRequestDTO bookingRequestDTO) {
        Booking booking = objectMapper.convertValue(bookingRequestDTO, Booking.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.bookRoom(booking));
    }

    @GetMapping("/details")
    public ResponseEntity bookRoom(@RequestParam String bookingId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.getBookingDetails(bookingId));
    }


    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity cancelRoom(@PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of("message", "Booking canceled successfully"));
    }
}
