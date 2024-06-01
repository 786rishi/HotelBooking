package com.abc.hotel.service;

import com.abc.hotel.dto.BookingResponseDTO;
import com.abc.hotel.entity.Booking;
import com.abc.hotel.entity.Hotel;
import com.abc.hotel.enums.BookingStatus;
import com.abc.hotel.exception.ActionNotPermitted;
import com.abc.hotel.exception.BookingNotFound;
import com.abc.hotel.exception.RoomNotAvailable;
import com.abc.hotel.exception.RoomNotFound;
import com.abc.hotel.repository.BookingRepository;
import com.abc.hotel.security.JwtUtil;
import com.abc.hotel.security.SecurityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    public BookingResponseDTO bookRoom(Booking booking) {
        booking.setUserId(SecurityUtils.getCurrentUserId());
        return objectMapper.convertValue(confirmBooking(booking), BookingResponseDTO.class);
    }

    public void cancelBooking(String bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if(bookingOptional.isEmpty()) {
            throw new BookingNotFound("No Booking Found for booking id - " + bookingId);
        }
        Booking booking = bookingOptional.get();
        booking.setStatus(BookingStatus.CANCELLED);
        booking.setId(bookingId);
        bookingRepository.save(booking);
    }

    private Booking confirmBooking(Booking booking) {
        Hotel hotel = hotelService.searchHotelById(booking.getHotelId());
        long roomMatched = hotel.getRooms().stream().filter(room -> room.getNumber() == booking.getRoomNumber()).count();
        if(roomMatched == 0) {
            throw new RoomNotFound("Room number not found - "+ booking.getRoomNumber());
        }
        List<Booking> bookingList = bookingRepository.getBookings(booking.getHotelId(), booking.getRoomNumber(), booking.getBookingStartDate(),
                booking.getBookingEndDate());
        if(CollectionUtils.isEmpty(bookingList)) {
            return bookingRepository.save(booking);
        }
        else {
            throw new RoomNotAvailable("Room already booked");
        }
    }

    public Booking getBookingDetails(String bookingId) {
        Optional<Booking> booking =  bookingRepository.findById(bookingId);
        if(booking.isEmpty()) {
            throw new BookingNotFound("Booking not found");
        }
        if(!booking.get().getUserId().equals(SecurityUtils.getCurrentUserId())) {
            throw new ActionNotPermitted("Not allowed to check details of other users");
        }
        return booking.get();
    }
}
