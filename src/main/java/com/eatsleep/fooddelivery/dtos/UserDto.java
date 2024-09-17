package com.eatsleep.fooddelivery.dtos;

import com.eatsleep.fooddelivery.models.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
public class UserDto {

    private Long id;


    private String username;


    private String email;


    private String password;

    private String role;
}
