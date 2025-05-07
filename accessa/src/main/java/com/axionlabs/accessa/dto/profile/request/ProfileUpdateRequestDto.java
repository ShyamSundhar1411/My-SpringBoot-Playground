package com.axionlabs.accessa.dto.profile.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
@Data
public class ProfileUpdateRequestDto {
    @Pattern(regexp =  "/^(\\+\\d{1,3}[- ]?)?\\d{10}$/", message = "Invalid mobile number format")
    private String mobileNumber;
    @URL
    private String profilePicture;
    @NotEmpty(message = "Date of Birth cannot be empty")
    private LocalDate dateOfBirth;
    @NotEmpty(message = "About cannot be empty")
    private String about;
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
