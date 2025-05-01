package com.axionlabs.accessa.dto;

import com.axionlabs.accessa.dto.user.UserDto;

public class TokenizedUserDto extends UserDto {
    private String accessToken;
    private String refreshToken;
}
