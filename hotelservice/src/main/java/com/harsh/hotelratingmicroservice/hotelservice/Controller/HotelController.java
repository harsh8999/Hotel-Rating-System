package com.harsh.hotelratingmicroservice.hotelservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.hotelratingmicroservice.hotelservice.Dto.HotelDto;
import com.harsh.hotelratingmicroservice.hotelservice.Service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService; 

    @PostMapping
    public ResponseEntity<HotelDto> create(@RequestBody HotelDto hotelDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.create(hotelDto));
        // HotelDto createdHotelDto = this.hotelService.create(hotelDto);
        // return new ResponseEntity<>(createdHotelDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getHotels() {
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getHotels());
        // List<HotelDto> hotelDtos = this.hotelService.getHotels();
        // return new ResponseEntity<>(hotelDtos, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotel(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getHotel(hotelId));
        // HotelDto hotelDtos = this.hotelService.getHotel(hotelId);
        // return new ResponseEntity<>(hotelDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable String hotelId) {
        this.hotelService.delete(hotelId);
        return new ResponseEntity<>("Hotel delete with Hotel Id" + hotelId, HttpStatus.OK);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotel(@RequestBody HotelDto  hotelDto, @PathVariable String hotelId) {
        HotelDto updatedHotelDto = this.hotelService.updateHotel(hotelDto, hotelId);
        return new ResponseEntity<>(updatedHotelDto, HttpStatus.OK);
    }
}
