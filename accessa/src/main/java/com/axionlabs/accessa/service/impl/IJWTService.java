package com.axionlabs.accessa.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class IJWTService {
    private static final long REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 7;
    private static final long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 15;
    private static final String ACCESS_SECRET_KEY = "F2eFeBST6J3UJZZuGgsHQV3Q0SwZvHgMBZPE0TIV1hQ=";
    private static final String REFRESH_SECRET_KEY = "9w2v/1YYHBiRUp0YXN6SNGJNkG1FxgTQnbb9ixeww9M=";
    public String extractUserName(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }
    public <T> T extractClaim(String jwtToken, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }
    public Map<String, String> generateJwtToken(UserDetails userDetails){
        Map<String, String> tokenMap = new HashMap<>();
        String accessToken = generateAccessToken(new HashMap<>(),userDetails);
        String refreshToken = generateRefreshToken(new HashMap<>(),userDetails);
        tokenMap.put("accessToken",accessToken);
        tokenMap.put("refreshToken",refreshToken);
        return tokenMap;
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateAccessToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY) )
                .signWith(getAccessSignInKey())
                .compact();


    }
    public String generateRefreshToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(getRefreshSignInKey())
                .compact();
    }
    private Claims extractAllClaims(String jwtToken){
        return Jwts.parser()
                .verifyWith(getAccessSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }
    private SecretKey getRefreshSignInKey(){
        byte [] keyBytes = Decoders.BASE64.decode(REFRESH_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private SecretKey getAccessSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ACCESS_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
