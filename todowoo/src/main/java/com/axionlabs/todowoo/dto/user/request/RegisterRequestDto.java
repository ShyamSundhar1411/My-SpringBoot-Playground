package com.axionlabs.todowoo.dto.user.request;

import com.axionlabs.todowoo.utils.validators.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@PasswordMatches
@Data
public class RegisterRequestDto {
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

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password should be minimum of 8 characters")
    private String password;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password should be minimum of 8 characters")
    private String confirmPassword;

}
