package com.axionlabs.arkive.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
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

}
