package com.axionlabs.meldora.service;

import com.axionlabs.meldora.dto.token.TokenDto;
import com.axionlabs.meldora.dto.token.request.TokenRequestDto;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JWTService {
    String extractUsername(String jwtToken,boolean isAccess);
    <T>  T extractClaim(String jwtToken, Function<Claims,T> claimResolver, boolean isAccess);
    TokenDto generateJwtTokenFromRefreshToken(TokenRequestDto tokenRequest);
    String generateAccessToken(Map<String,Object> extraClaims,UserDetails userDetails);
    String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails);
    TokenDto generateJwtToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails, boolean isAccess);

}
