package com.harsh.microservice.hotelrating.userservice.External.Service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.harsh.microservice.hotelrating.userservice.Entity.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/user/{userId}")
    List<Rating> getRating(@PathVariable("userId") String userId);
    
}
