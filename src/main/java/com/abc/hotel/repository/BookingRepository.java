package com.abc.hotel.repository;

import com.abc.hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {

    @Query("SELECT b FROM Booking b WHERE b.hotelId = ?1 AND b.roomNumber = ?2 AND b.bookingStartDate NOT BETWEEN ?3 AND ?4")
    List<Booking> getBookings(String hotelId, Integer roomNumber, Date startDate, Date endDate);
}