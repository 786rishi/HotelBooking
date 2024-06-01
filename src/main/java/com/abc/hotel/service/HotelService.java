package com.abc.hotel.service;

import com.abc.hotel.dto.HotelResponseDTO;
import com.abc.hotel.exception.HotelNotFound;
import com.abc.hotel.repository.HotelRepository;
import com.abc.hotel.entity.Room;
import com.abc.hotel.entity.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ObjectMapper objectMapper;


    public HotelResponseDTO saveHotel(Hotel hotel) {
        return objectMapper.convertValue(hotelRepository.save(hotel), HotelResponseDTO.class);
    }

    public void updateHotel(String hotelId, Hotel hotel) {
        Hotel fetchedHotel = hotelRepository.findById(hotelId).get();
        fetchedHotel.builder().name(hotel.getName()).city(hotel.getCity());
        hotelRepository.save(fetchedHotel);
    }

    public void addRooms(String hotelId, Room room) {
        Optional<Hotel> fetchedHotel = hotelRepository.findById(hotelId);
        if(fetchedHotel.isEmpty()) {
            throw new HotelNotFound("Hotel not found for id - "+ hotelId);
        }
        Hotel hotel = fetchedHotel.get();
        hotel.getRooms().add(room);
        room.setHotel(hotel);
        hotelRepository.save(hotel);
    }

    public Hotel searchHotelById(String hotelId) {
         Optional<Hotel> hotel = hotelRepository.findById(hotelId);
         return hotel.orElseThrow(() -> new HotelNotFound("Hotel not found for id " + hotelId));
    }

    public List<Hotel> searchHotelByCity(String city) {
        return hotelRepository.findByCity(city);
    }
}
