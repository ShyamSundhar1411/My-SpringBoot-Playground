package com.axionlabs.todowoo.dto.token;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {
    String accessToken;
    String refreshToken;
}
