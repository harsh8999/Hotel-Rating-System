package com.harsh.microservice.hotelrating.userservice.Service;

import java.util.List;

import com.harsh.microservice.hotelrating.userservice.Dto.UserDto;

public interface UserService {
    // create user
    UserDto createUser(UserDto userDto);

    // get all user
    List<UserDto> getUsers();

    //get single user
    UserDto getUser(String userId);

    // delete
    void deleteUser(String userId);

    // update
    UserDto updateUser(UserDto userDto, String userId);
}
