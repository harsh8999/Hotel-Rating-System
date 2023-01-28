package com.harsh.hotelratingmicroservice.ratingservice.Service;

import java.util.List;

import com.harsh.hotelratingmicroservice.ratingservice.Dto.RatingDto;

public interface RatingService {
    // create
    RatingDto create(RatingDto ratingDto);

    // get all ratings
    List<RatingDto> getRatings();

    // get all by userId
    List<RatingDto> getRatingsByUserId(String userId);

    // get all by hotelId
    List<RatingDto> getRatingByHotelId(String hotelId);
}
