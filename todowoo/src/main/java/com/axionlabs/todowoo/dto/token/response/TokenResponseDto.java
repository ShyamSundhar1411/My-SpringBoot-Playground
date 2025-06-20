package com.axionlabs.todowoo.dto.token.response;

import com.axionlabs.todowoo.dto.BaseResponseDto;
import com.axionlabs.todowoo.dto.token.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TokenResponseDto extends BaseResponseDto {
    private String accessToken;
    private String refreshToken;
    public TokenResponseDto(HttpStatusCode statusCode, String statusMessage, TokenDto tokenDto){
        super(statusCode,statusMessage);
        this.accessToken = tokenDto.getAccessToken();
        this.refreshToken = tokenDto.getRefreshToken();
    }
}
