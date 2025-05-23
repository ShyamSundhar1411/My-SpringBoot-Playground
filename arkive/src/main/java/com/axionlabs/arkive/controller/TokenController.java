package com.axionlabs.arkive.controller;

import com.axionlabs.arkive.dto.token.TokenDto;
import com.axionlabs.arkive.dto.token.request.TokenRequestDto;
import com.axionlabs.arkive.dto.token.response.TokenResponseDto;
import com.axionlabs.arkive.service.impl.IJWTService;
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
@RequestMapping("/api/v1/token")
@Tag(
        name = "Token APIs - Arkive",
        description = "Manages token-related operations such as generating new access and refresh tokens using a valid refresh token."
)
public class TokenController {

    private final IJWTService ijwtService;

    @Autowired
    public TokenController(IJWTService ijwtService){
        this.ijwtService = ijwtService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> generateAccessTokenFromRefreshToken(@Valid @RequestBody TokenRequestDto tokenRequest){
        TokenDto tokens = ijwtService.generateJwtTokenFromRefreshToken(tokenRequest);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new TokenResponseDto(
                        HttpStatus.OK,
                        "Tokens generated successfully",
                        tokens
                )
        );
    }
}
