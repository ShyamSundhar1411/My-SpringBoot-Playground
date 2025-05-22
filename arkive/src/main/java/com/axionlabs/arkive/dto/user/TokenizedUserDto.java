package com.axionlabs.arkive.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TokenizedUserDto extends UserDto {
    private String accessToken;
    private String refreshToken;
}
