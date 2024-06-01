package com.abc.hotel.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class RoomsRequestDTO {

    private Integer number;
    private Time checkInTime;
    private Time checkOutTime;
    private Integer maxGuests;
    private Double price;

}
