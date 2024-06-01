package com.abc.hotel.repository;

import com.abc.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    public List<Hotel> findByCity(String city);
}
