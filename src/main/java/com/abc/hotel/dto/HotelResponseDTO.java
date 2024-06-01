package com.abc.hotel.dto;

import lombok.Data;
import java.util.List;

@Data
public class HotelResponseDTO {
    private String name;
    private String city;
    List<RoomsRequestDTO> rooms;
}
