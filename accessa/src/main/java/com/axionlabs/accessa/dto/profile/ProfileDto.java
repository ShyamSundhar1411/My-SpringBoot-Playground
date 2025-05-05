package com.axionlabs.accessa.dto.profile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProfileDto {
    private UUID profileId;
    @NotEmpty(message = "Mobile Number cannot be empty")
    @Pattern(regexp =  "/^(\\+\\d{1,3}[- ]?)?\\d{10}$/", message = "Invalid mobile number format")
    private String mobileNumber;

    @URL
    @NotEmpty(message = "Profile Picture cannot be empty")
    private String profilePicture;

    @NotEmpty(message = "Date of Birth cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "About cannot be empty")
    private String about;
}
