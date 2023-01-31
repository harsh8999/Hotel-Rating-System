package com.harsh.microservice.hotelrating.userservice.External.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.harsh.microservice.hotelrating.userservice.Entity.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    
    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);

}
