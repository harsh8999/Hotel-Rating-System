package com.harsh.microservice.hotelrating.userservice.Controller;

import java.util.List;

import javax.validation.Valid;

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

import com.harsh.microservice.hotelrating.userservice.Dto.UserDto;
import com.harsh.microservice.hotelrating.userservice.Service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        UserDto userDto = this.userService.getUser(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // fall back methods
    public ResponseEntity<UserDto> ratingHotelFallback(String userId, Exception ex) {
        System.out.println("Fallback is executed because service is down: "+ ex.getMessage());
        UserDto userDto = UserDto.builder()
                                    .userId("12345")
                                    .email("dummy@gmail.com")
                                    .about("This is dummy")
                                    .name("Dummy")
                                    .build();
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = this.userService.getUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>("User delete with User Id" + userId, HttpStatus.OK);
    }

    // PUT
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId) {
        UserDto updatedDto = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedDto);
    }
}
