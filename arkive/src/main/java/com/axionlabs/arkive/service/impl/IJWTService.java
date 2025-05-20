package com.axionlabs.arkive.service.impl;

import com.axionlabs.arkive.config.properties.JWTConfigProperties;
import com.axionlabs.arkive.dto.token.TokenDto;
import com.axionlabs.arkive.dto.token.request.TokenRequestDto;
import com.axionlabs.arkive.service.JWTService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
public class IJWTService implements JWTService {
    private final JWTConfigProperties jwtConfigProperties;

    @Autowired
    public IJWTService(JWTConfigProperties jwtConfigProperties){
        this.jwtConfigProperties = jwtConfigProperties;
    }

    @Override
    public String extractUserName(String jwtToken, boolean isAccess) {
        return "";
    }

    @Override
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver, boolean isAccess) {
        return null;
    }

    @Override
    public TokenDto generateJwtToken(UserDetails userDetails) {
        return null;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails, boolean isAccess) {
        return false;
    }

    @Override
    public TokenDto generateJwtTokenFromRefreshToken(TokenRequestDto tokenRequest) {
        return null;
    }

    @Override
    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return "";
    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return "";
    }
}
