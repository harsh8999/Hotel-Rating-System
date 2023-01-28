package com.harsh.hotelratingmicroservice.hotelservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.hotelratingmicroservice.hotelservice.Entity.Hotel;



public interface HotelRepository extends JpaRepository<Hotel, String> {
    
}
