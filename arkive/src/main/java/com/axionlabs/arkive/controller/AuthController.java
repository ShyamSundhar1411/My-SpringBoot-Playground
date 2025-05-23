package com.axionlabs.arkive.controller;

import com.axionlabs.arkive.dto.ErrorResponseDto;
import com.axionlabs.arkive.dto.user.TokenizedUserDto;
import com.axionlabs.arkive.dto.user.request.LoginRequestDto;
import com.axionlabs.arkive.dto.user.request.RegisterRequestDto;
import com.axionlabs.arkive.dto.user.response.UserResponseDto;
import com.axionlabs.arkive.service.impl.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Authentication APIs - Arkive",
        description = "Handles user authentication, registration, and session management for the Arkive application."
)
public class AuthController {
    private final IAuthService iAuthService;

    @Autowired
    public AuthController(IAuthService iAuthService){
        this.iAuthService = iAuthService;
    }

    @Operation(
            summary = "Login a user",
            description = "Logs in a user using provided credentials and returns a tokenized response if successful."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User logged in successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data provided.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access (invalid credentials).",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@Valid @RequestBody LoginRequestDto request){
        TokenizedUserDto userDetails = iAuthService.loginUser(request);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new UserResponseDto(
                HttpStatus.OK,
                "User Logged in successfully",
                userDetails
                )
        );

    }
    @Operation(
            summary = "Register a new user",
            description = "Registers a new user into the system with the provided details. Returns user information and a tokenized response upon successful registration."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data provided.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegisterRequestDto request){
        TokenizedUserDto userDetails = iAuthService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new UserResponseDto(
                        HttpStatus.CREATED,
                        "User registered successfully",
                        userDetails
                )
        );
    }
}
