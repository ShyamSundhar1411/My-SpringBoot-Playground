package com.axionlabs.accessa.dto.user.response;

import com.axionlabs.accessa.dto.user.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.BaseResponseDto;
import org.springframework.http.HttpStatusCode;


public class UserResponseDto extends BaseResponseDto {

    public UserResponseDto(HttpStatusCode statusCode, String statusMessage, TokenizedUserDto userData) {
        super(statusCode, statusMessage);

    }
    private TokenizedUserDto data;
}
