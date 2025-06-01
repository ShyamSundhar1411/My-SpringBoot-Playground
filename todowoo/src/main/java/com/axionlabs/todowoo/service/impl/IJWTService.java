package com.axionlabs.todowoo.service.impl;

import com.axionlabs.todowoo.config.properties.JWTConfigProperties;
import com.axionlabs.todowoo.dto.token.TokenDto;
import com.axionlabs.todowoo.dto.token.request.TokenRequestDto;
import com.axionlabs.todowoo.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class IJWTService implements JWTService {
    @Autowired
    private JWTConfigProperties jwtConfigProperties;

    @Autowired
    private IUserService iUserService;


    @Override
    public String extractUserName(String jwtToken, boolean isAccess) {
        return extractClaim(jwtToken,Claims::getSubject,isAccess);
    }

    @Override
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver, boolean isAccess) {
        final Claims claims = extractAllClaims(jwtToken,isAccess);
        return claimsResolver.apply(claims);
    }


    @Override
    public TokenDto generateJwtToken(UserDetails userDetails) {
        TokenDto tokenDto = new TokenDto();
        String accessToken = generateAccessToken(new HashMap<>(),userDetails);
        String refreshToken = generateRefreshToken(new HashMap<>(),userDetails);
        tokenDto.setAccessToken(accessToken);
        tokenDto.setRefreshToken(refreshToken);
        return tokenDto;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails, boolean isAccess) {
        final String username = extractUserName(token,isAccess);
        return (username.equals(userDetails.getUsername()) && isTokenExpired(token,isAccess));
    }

    @Override
    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .signWith(getAccessSigningKey())
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+jwtConfigProperties.getAccessSecretKeyExpiration()))
                .compact()
                ;
    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .signWith(getRefreshSigningKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+jwtConfigProperties.getRefreshSecretKeyExpiration()))
                .compact();
    }

    @Override
    public TokenDto generateJwtTokenFromRefreshToken(TokenRequestDto tokenRequest) {
        String refreshToken = tokenRequest.getRefreshToken();
        if(isTokenExpired(refreshToken,false)){
            String username = extractUserName(refreshToken,false);
            UserDetails userDetails = iUserService.loadUserByUsername(username);
            if(isTokenValid(refreshToken,userDetails,false)){
                return generateJwtToken(userDetails);
            }
        }
        throw new RuntimeException("Invalid or expired refresh token");
    }

    private boolean isTokenExpired(String token, boolean isAccess){
        return !extractExpiration(token, isAccess).before(new Date());
    }

    private Date extractExpiration(String token, boolean isAccess) {
        return extractClaim(token,Claims::getExpiration,isAccess);
    }

    private Claims extractAllClaims(String jwtToken, boolean isAccess) {
        return Jwts.parser()
                .verifyWith(isAccess?getAccessSigningKey():getRefreshSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }
    private SecretKey getAccessSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperties.getAccessSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);

    }
    private SecretKey getRefreshSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperties.getRefreshSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
