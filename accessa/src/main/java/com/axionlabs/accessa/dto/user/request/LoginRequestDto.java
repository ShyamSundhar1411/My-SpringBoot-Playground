package com.axionlabs.accessa.dto.user.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotEmpty(message = "Username cannot be empty")
    private String userName;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
