package com.axionlabs.accessa.dto.user.response;

import com.axionlabs.accessa.dto.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.BaseResponseDto;
import com.axionlabs.accessa.dto.user.UserDto;
import lombok.Data;
import org.springframework.http.HttpStatusCode;


public class UserResponseDto extends BaseResponseDto {

    public UserResponseDto(HttpStatusCode statusCode, String statusMessage, TokenizedUserDto userData) {
        super(statusCode, statusMessage);

    }
    private TokenizedUserDto data;
}
