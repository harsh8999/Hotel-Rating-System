package com.harsh.microservice.hotelrating.userservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String about;
}
