package com.harsh.hotelratingmicroservice.ratingservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.hotelratingmicroservice.ratingservice.Dto.RatingDto;
import com.harsh.hotelratingmicroservice.ratingservice.Service.RatingService;

@RestController
@RequestMapping("/ratings")

public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingDto> create(@RequestBody RatingDto ratingDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingService.create(ratingDto));
    }

    @GetMapping
    public ResponseEntity<List<RatingDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getRatings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDto>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getRatingsByUserId(userId));
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RatingDto>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getRatingByHotelId(hotelId));
    }

}
