package com.harsh.microservice.hotelrating.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.microservice.hotelrating.userservice.Entity.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
