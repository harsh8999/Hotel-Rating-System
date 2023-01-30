package com.harsh.microservice.hotelrating.userservice.ServiceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.harsh.microservice.hotelrating.userservice.Dto.UserDto;
import com.harsh.microservice.hotelrating.userservice.Entity.Rating;
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

    @Autowired
    private RestTemplate restTemplate;


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
        
        // fetch ratings from Rating Service
        // ExecutorService executor = Executors.newFixedThreadPool(10);
        
        // for(User user: users) {
        //     executor.execute(() -> {
        //         ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/user/" + user.getUserId(), ArrayList.class);
        //         user.setRatings(ratingsOfUser);
        //     });
        // }
        // users.stream()..map(user -> {
        //     ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/user/" + user.getUserId(), ArrayList.class);
        //     user.setRatings(ratingsOfUser); 
        //     this.modelMapper.map(user, UserDto.class);
        // }).collect(Collectors.toList());
        List<UserDto> userDtos = new ArrayList<>();

        for(User user: users) {
            ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/user/" + user.getUserId(), ArrayList.class);
            user.setRatings(ratingsOfUser); 
            userDtos.add(this.modelMapper.map(user, UserDto.class));
        }

        // List<UserDto> userDtos = users.stream()
        //                                 .map(user -> this.modelMapper.map(user, UserDto.class))
        //                                 .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getUser(String userId) {
        User user = this.userRepository.findById(userId)
                                        .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        
        // fetch ratings from Rating Service
        // URL: http://localhost:8083/ratings/user/{userId}
        String url = "http://localhost:8083/ratings/user/" + userId;
        ArrayList<Rating> ratingsOfUser = restTemplate.getForObject(url, ArrayList.class);
        user.setRatings(ratingsOfUser);
        
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
