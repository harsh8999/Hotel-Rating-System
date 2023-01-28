package com.harsh.hotelratingmicroservice.hotelservice.Service;

import java.util.List;

import com.harsh.hotelratingmicroservice.hotelservice.Dto.HotelDto;

public interface HotelService {
    // create
    HotelDto create(HotelDto hotelDto);

    // getall
    List<HotelDto> getHotels();

    // get single
    HotelDto getHotel(String hotelId);

    // update hotel
    HotelDto updateHotel(HotelDto hotelDto, String hotelId);

    // delete 
    void delete(String hotelId);
}
