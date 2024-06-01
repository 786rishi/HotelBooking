package com.abc.hotel.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingRequestDTO {
    private Date bookingStartDate;
    private Date bookingEndDate;
    private String hotelId;
    private String roomNumber;
}
