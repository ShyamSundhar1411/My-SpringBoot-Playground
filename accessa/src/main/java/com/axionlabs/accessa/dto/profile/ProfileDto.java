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

    @Pattern(regexp =  "/^(\\+\\d{1,3}[- ]?)?\\d{10}$/", message = "Invalid mobile number format")
    private String mobileNumber;

    @URL

    private String profilePicture;


    private LocalDate dateOfBirth;


    private String about;
}
