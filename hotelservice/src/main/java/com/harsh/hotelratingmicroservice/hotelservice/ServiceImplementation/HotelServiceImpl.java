package com.harsh.hotelratingmicroservice.hotelservice.ServiceImplementation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.hotelratingmicroservice.hotelservice.Dto.HotelDto;
import com.harsh.hotelratingmicroservice.hotelservice.Entity.Hotel;
import com.harsh.hotelratingmicroservice.hotelservice.Exception.ResourceNotFoundException;
import com.harsh.hotelratingmicroservice.hotelservice.Repository.HotelRepository;
import com.harsh.hotelratingmicroservice.hotelservice.Service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelDto create(HotelDto hotelDto) {
        Hotel hotel = this.modelMapper.map(hotelDto, Hotel.class);
        // generate random id
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        Hotel savedHotel = this.hotelRepository.save(hotel);
        return this.modelMapper.map(savedHotel, HotelDto.class);
    }

    @Override
    public List<HotelDto> getHotels() {
        List<Hotel> hotels = this.hotelRepository.findAll();
        return hotels.stream()
                        .map(hotel -> this.modelMapper.map(hotel, HotelDto.class))
                        .collect(Collectors.toList());
    }

    @Override
    public HotelDto getHotel(String hotelId) {
            Hotel hotel = this.hotelRepository.findById(hotelId)
                                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "Id", hotelId));
        return this.modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotel(HotelDto hotelDto, String hotelId) {
        Hotel hotel = this.hotelRepository.findById(hotelId)
                                        .orElseThrow(() -> new ResourceNotFoundException("Hotel", "Id", hotelId));
        hotel.setName(hotelDto.getName());
        hotel.setLocation(hotelDto.getLocation());
        hotel.setAbout(hotelDto.getAbout());

        Hotel updatedHotel = this.hotelRepository.save(hotel);

        return this.modelMapper.map(updatedHotel, HotelDto.class);
    }

    @Override
    public void delete(String hotelId) {
        Hotel hotel = this.hotelRepository.findById(hotelId)
                        .orElseThrow(()-> new ResourceNotFoundException("Hotel", "Id", hotelId));

        this.hotelRepository.delete(hotel);
        
    }

}
