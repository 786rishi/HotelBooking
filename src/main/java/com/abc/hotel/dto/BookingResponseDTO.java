package com.abc.hotel.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingResponseDTO {
    private Date bookingStartDate;
    private Date bookingEndDate;
    private String roomNumber;
}
