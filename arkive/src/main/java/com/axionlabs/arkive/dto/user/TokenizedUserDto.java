package com.axionlabs.arkive.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter @Setter
public class TokenizedUserDto extends UserDto {
    private String accessToken;
    private String refreshToken;
}
