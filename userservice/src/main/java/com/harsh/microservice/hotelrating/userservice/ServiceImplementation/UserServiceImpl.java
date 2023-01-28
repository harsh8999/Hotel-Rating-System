package com.harsh.microservice.hotelrating.userservice.ServiceImplementation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.microservice.hotelrating.userservice.Dto.UserDto;
import com.harsh.microservice.hotelrating.userservice.Entity.User;
import com.harsh.microservice.hotelrating.userservice.Exception.ResourceNotFoundException;
import com.harsh.microservice.hotelrating.userservice.Repository.UserRepository;
import com.harsh.microservice.hotelrating.userservice.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // generate random userId
        String userUUID = UUID.randomUUID().toString();
        User userToBeSaved = this.modelMapper.map(userDto, User.class);
        userToBeSaved.setUserId(userUUID);
        User savedUser = this.userRepository.save(userToBeSaved);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                                        .map(user -> this.modelMapper.map(user, UserDto.class))
                                        .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getUser(String userId) {
        User user = this.userRepository.findById(userId)
                                        .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        User user = this.userRepository.findById(userId)
                        .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));

        this.userRepository.delete(user);
        
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = this.userRepository.findById(userId)
                                        .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepository.save(user);

        return this.modelMapper.map(updatedUser, UserDto.class);
    }

}
