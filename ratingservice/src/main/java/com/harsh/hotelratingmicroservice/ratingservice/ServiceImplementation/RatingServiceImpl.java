package com.harsh.hotelratingmicroservice.ratingservice.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.hotelratingmicroservice.ratingservice.Dto.RatingDto;
import com.harsh.hotelratingmicroservice.ratingservice.Entity.Rating;
import com.harsh.hotelratingmicroservice.ratingservice.Repository.RatingRepository;
import com.harsh.hotelratingmicroservice.ratingservice.Service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RatingDto create(RatingDto ratingDto) {
        Rating rating = this.modelMapper.map(ratingDto, Rating.class);
        Rating savedRating = this.ratingRepository.save(rating);
        RatingDto createdRatingDto = this.modelMapper.map(savedRating, RatingDto.class);
        createdRatingDto.setRatingId(savedRating.getRatingId());
        return createdRatingDto;
    }

    @Override
    public List<RatingDto> getRatings() {
        List<Rating> ratings = this.ratingRepository.findAll();
        return ratings.stream().map(rating -> this.modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getRatingsByUserId(String userId) {
        List<Rating> ratings= this.ratingRepository.findByUserId(userId);
        return ratings.stream().map(rating -> this.modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getRatingByHotelId(String hotelId) {
        List<Rating> ratings= this.ratingRepository.findByHotelId(hotelId);
        return ratings.stream().map(rating -> this.modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

}
