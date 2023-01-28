package com.harsh.hotelratingmicroservice.ratingservice.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.harsh.hotelratingmicroservice.ratingservice.Entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{
    // custom finder methods
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
