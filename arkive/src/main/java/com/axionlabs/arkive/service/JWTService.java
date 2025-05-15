package com.axionlabs.arkive.service;

import com.axionlabs.arkive.dto.token.TokenDto;
import com.axionlabs.arkive.dto.token.request.TokenRequestDto;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JWTService {
    String extractUserName(String jwtToken, boolean isAccess);
    <T> T extractClaim(String jwtToken, Function<Claims,T> claimsResolver, boolean isAccess);

    TokenDto generateJwtToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails, boolean isAccess);
    TokenDto generateJwtTokenFromRefreshToken(TokenRequestDto tokenRequest);
    String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
