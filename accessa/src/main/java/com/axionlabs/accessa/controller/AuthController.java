package com.axionlabs.accessa.controller;

import com.axionlabs.accessa.dto.user.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.request.LoginRequestDto;
import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;
import com.axionlabs.accessa.dto.user.response.UserResponseDto;
import com.axionlabs.accessa.service.impl.IAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    private final IAuthService iAuthService;

    public AuthController(IAuthService iAuthService) {
        this.iAuthService = iAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody RegisterRequestDto request
    ){
        TokenizedUserDto userDetails = iAuthService.registerUser(request);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                new UserResponseDto(
                        HttpStatus.CREATED,
                        "User registered successfully",
                        userDetails

                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody LoginRequestDto request){
        TokenizedUserDto userDetails = iAuthService.loginUser(request);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                new UserResponseDto(
                        HttpStatus.OK,
                        "User Logged in successfully",
                        userDetails
                )
        );
    }
}
