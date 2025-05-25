package com.axionlabs.arkive.service.impl;

import com.axionlabs.arkive.config.properties.JWTConfigProperties;
import com.axionlabs.arkive.dto.token.TokenDto;
import com.axionlabs.arkive.dto.token.request.TokenRequestDto;
import com.axionlabs.arkive.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class IJWTService implements JWTService {
    private final JWTConfigProperties jwtConfigProperties;
    private final IUserService iUserService;

    @Autowired
    public IJWTService(JWTConfigProperties jwtConfigProperties, IUserService iUserService){
        this.jwtConfigProperties = jwtConfigProperties;
        this.iUserService = iUserService;
    }

    @Override
    public String extractUserName(String jwtToken, boolean isAccess) {
        return extractClaim(jwtToken,Claims::getSubject, isAccess);
    }

    @Override
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver, boolean isAccess) {
        final Claims claims = extractAllClaims(jwtToken,isAccess);
        return claimsResolver.apply(claims);
    }

    @Override
    public TokenDto generateJwtToken(UserDetails userDetails) {
        TokenDto tokens = new TokenDto();
        String accessToken = generateAccessToken(new HashMap<>(),userDetails);
        String refreshToken = generateRefreshToken(new HashMap<>(),userDetails);
        tokens.setAccessToken(accessToken);
        tokens.setRefreshToken(refreshToken);
        return tokens;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails, boolean isAccess) {
        final String username = extractUserName(token,isAccess);
        return (username.equals(userDetails.getUsername()) && isTokenExpired(token,isAccess));
    }


    private boolean isTokenExpired(String token, boolean isAccess){
        return !extractExpiration(token, isAccess).before(new Date());
    }

    private Date extractExpiration(String token, boolean isAccess){
        return extractClaim(token, Claims::getExpiration, isAccess);
    }
    @Override
    public TokenDto generateJwtTokenFromRefreshToken(TokenRequestDto tokenRequest) {
        return null;
    }

    @Override
    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtConfigProperties.getAccessSecretKeyExpiration()))
                .signWith(getAccessSignInKey())
                .compact();

    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+jwtConfigProperties.getRefreshSecretKeyExpiration()))
                .signWith(getRefreshSignInKey())
                .compact();

    }
    private Claims extractAllClaims(String jwtToken,boolean isAccess){
        return Jwts.parser()
                .verifyWith(isAccess?getAccessSignInKey():getRefreshSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private SecretKey getRefreshSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperties.getRefreshSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private SecretKey getAccessSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperties.getAccessSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
