package com.axionlabs.accessa.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID userId;
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, message = "The length of the username should be atleast 4")
    private String userName;

    @NotEmpty(message = "First Name cannot be empty")
    @Size(min = 2, message = "The length of the first name should be atleast 2")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private  String email;



}
