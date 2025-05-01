package com.axionlabs.accessa.controller;

import com.axionlabs.accessa.dto.user.request.LoginRequestDto;
import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;
import com.axionlabs.accessa.dto.user.response.UserResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Authentication APIs - Accessa",
        description = "Handles user authentication, registration, and session management for the Accesa application."
)

public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody RegisterRequestDto request
    ){
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody LoginRequestDto request){
        return null;
    }
}
