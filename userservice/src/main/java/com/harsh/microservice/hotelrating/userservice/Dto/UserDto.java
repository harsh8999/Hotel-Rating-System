package com.harsh.microservice.hotelrating.userservice.Dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.harsh.microservice.hotelrating.userservice.Entity.Rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String userId;

    @NotEmpty
    private String name;

    @Email(message = "Email address is not valid !!!")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Password must be greater than 8 characters")
    private String password;

    @NotEmpty
    private String about;

    private List<Rating> ratings;
}
