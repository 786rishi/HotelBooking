package com.abc.hotel.controller;

import com.abc.hotel.dto.HotelRequestDTO;
import com.abc.hotel.dto.HotelResponseDTO;
import com.abc.hotel.dto.RoomsRequestDTO;
import com.abc.hotel.entity.Hotel;
import com.abc.hotel.entity.Room;
import com.abc.hotel.service.HotelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
* This controller is responsible to add hotel and rooms
* Search hotels based on city
*/
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/onboard")
    public ResponseEntity<HotelResponseDTO> addHotel(@RequestBody HotelRequestDTO hotelRequestDTO) {
        Hotel hotel = objectMapper.convertValue(hotelRequestDTO, Hotel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotel));
    }

    @PostMapping("/add_rooms/{hotelId}")
    public ResponseEntity addRooms(@RequestBody RoomsRequestDTO roomsRequestDTO, @PathVariable String hotelId) {
        hotelService.addRooms(hotelId, objectMapper.convertValue(roomsRequestDTO, Room.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Room added successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity searchHotels(@RequestParam String city) {
        List<Hotel> hotels = hotelService.searchHotelByCity(city);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(hotels);
    }
}
