package com.axionlabs.accessa.service.impl;

import com.axionlabs.accessa.dto.token.TokenDto;
import com.axionlabs.accessa.dto.token.request.TokenRequestDto;
import com.axionlabs.accessa.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class IJWTService implements JWTService {
    private static final long REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 7;
    private static final long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 15;
    private static final String ACCESS_SECRET_KEY = "F2eFeBST6J3UJZZuGgsHQV3Q0SwZvHgMBZPE0TIV1hQ=";
    private static final String REFRESH_SECRET_KEY = "9w2v/1YYHBiRUp0YXN6SNGJNkG1FxgTQnbb9ixeww9M=";
    private final IUserService iUserService;
    @Autowired
    public IJWTService(IUserService iUserService){
        this.iUserService = iUserService;
    }
    public String extractUserName(String jwtToken, boolean isAccess) {
        return extractClaim(jwtToken, Claims::getSubject, isAccess);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims,T> claimsResolver, boolean isAccess){
        final Claims claims = extractAllClaims(jwtToken, isAccess);
        return claimsResolver.apply(claims);
    }
    public TokenDto generateJwtToken(UserDetails userDetails){
        TokenDto tokens = new TokenDto();
        String accessToken = generateAccessToken(new HashMap<>(),userDetails);
        String refreshToken = generateRefreshToken(new HashMap<>(),userDetails);
        tokens.setAccessToken(accessToken);
        tokens.setRefreshToken(refreshToken);
        return tokens;
    }
    public boolean isTokenValid(String token, UserDetails userDetails, boolean isAccess){
        final String username = extractUserName(token,isAccess);
        return (username.equals(userDetails.getUsername())) && isTokenExpired(token, isAccess);
    }

    public TokenDto generateJwtTokenFromRefreshToken(TokenRequestDto tokenRequest){
        String refreshToken = tokenRequest.getRefreshToken();
        if(isTokenExpired(refreshToken, false)){
            String username = extractUserName(refreshToken,false);
            UserDetails userDetails = iUserService.loadUserByUsername(username);
            if(userDetails != null){
                return generateJwtToken(userDetails);
            }
        }
        throw new RuntimeException("Invalid or expired refresh token");

    }
    private boolean isTokenExpired(String token, boolean isAccess) {
        return !extractExpiration(token, isAccess).before(new Date());
    }

    private Date extractExpiration(String token, boolean isAccess) {
        return extractClaim(token,Claims::getExpiration,isAccess);
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
    private Claims extractAllClaims(String jwtToken, boolean isAccess){

        return Jwts.parser()
                .verifyWith(isAccess ? getAccessSignInKey() : getRefreshSignInKey())
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
