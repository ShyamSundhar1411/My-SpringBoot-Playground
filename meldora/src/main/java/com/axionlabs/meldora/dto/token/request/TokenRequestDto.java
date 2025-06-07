package com.axionlabs.meldora.dto.token.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class TokenRequestDto {
    private String refreshToken;
}
