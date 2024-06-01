package com.abc.hotel.entity;

import com.abc.hotel.enums.BookingStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private Date bookingStartDate;
    private Date bookingEndDate;
    private String hotelId;
    private Integer roomNumber;
    private BookingStatus status;
    private String userId;
}
