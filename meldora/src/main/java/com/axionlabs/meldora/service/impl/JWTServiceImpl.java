package com.axionlabs.meldora.service.impl;

import com.axionlabs.meldora.config.properties.JWTConfigProperties;
import com.axionlabs.meldora.dto.token.TokenDto;
import com.axionlabs.meldora.dto.token.request.TokenRequestDto;
import com.axionlabs.meldora.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
    private JWTConfigProperties jwtConfigProperties;
    @Override
    public String extractUsername(String jwtToken, boolean isAccess) {
        return extractClaim(jwtToken,Claims::getSubject,isAccess);
    }

    @Override
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver, boolean isAccess) {
        final Claims claims = extractAllClaims(jwtToken,isAccess);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken, boolean isAccess) {
        return Jwts.parser()
                .verifyWith(isAccess?getAccessSigningKey():getRefreshSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

    }

    private SecretKey getRefreshSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperties.getRefreshSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private SecretKey getAccessSigningKey() {
        byte[] keyBytes =  Decoders.BASE64.decode(jwtConfigProperties.getAccessSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public TokenDto generateJwtTokenFromRefreshToken(TokenRequestDto tokenRequest) {
        return null;
    }

    @Override
    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .signWith(getAccessSigningKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+jwtConfigProperties.getAccessSecretKeyExpiration()))
                .compact();
    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .signWith(getRefreshSigningKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+jwtConfigProperties.getRefreshSecretKeyExpiration()))
                .compact();
    }

    @Override
    public TokenDto generateJwtToken(UserDetails userDetails) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(generateAccessToken(new HashMap<>(),userDetails));
        tokenDto.setRefreshToken(generateRefreshToken(new HashMap<>(),userDetails));
        return tokenDto;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails, boolean isAccess) {
        String username = extractUsername(token,isAccess);
        return (username.equals(userDetails.getUsername()) && isTokenExpired(token,isAccess));
    }
    private boolean isTokenExpired(String token, boolean isAccess){
        return !extractExpiration(token, isAccess).before(new Date());
    }

    private Date extractExpiration(String token, boolean isAccess){
        return extractClaim(token, Claims::getExpiration, isAccess);
    }
}
