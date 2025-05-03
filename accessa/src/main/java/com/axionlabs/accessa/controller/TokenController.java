package com.axionlabs.accessa.controller;

import com.axionlabs.accessa.dto.ErrorResponseDto;
import com.axionlabs.accessa.dto.token.TokenDto;
import com.axionlabs.accessa.dto.token.request.TokenRequestDto;
import com.axionlabs.accessa.dto.token.response.TokenResponseDto;
import com.axionlabs.accessa.service.impl.IJWTService;
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
@RequestMapping("/api/v1/token")
@Tag(
        name = "Token APIs - Accessa",
        description = "Manages token-related operations such as generating new access and refresh tokens using a valid refresh token."
)
public class TokenController {

    private final IJWTService ijwtService;

    @Autowired
    public TokenController(IJWTService ijwtService) {
        this.ijwtService = ijwtService;
    }

    @PostMapping("/refresh")
    @Operation(
            summary = "Refresh JWT Tokens",
            description = "Generates new access and refresh tokens using a valid refresh token. This helps maintain user sessions without requiring re-login."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tokens generated successfully.",
                    content = @Content(schema = @Schema(implementation = TokenResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid or missing token provided.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Refresh token is expired or tampered.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error while generating new tokens.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<TokenResponseDto> generateTokensFromRefreshToken(@Valid @RequestBody TokenRequestDto request) {
        TokenDto tokens = ijwtService.generateJwtTokenFromRefreshToken(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                new TokenResponseDto(
                        HttpStatus.OK,
                        "Tokens generated successfully",
                        tokens
                )
        );
    }
}

