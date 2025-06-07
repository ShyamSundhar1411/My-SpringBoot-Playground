package com.axionlabs.meldora.dto.token;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
