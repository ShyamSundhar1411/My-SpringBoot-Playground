package com.axionlabs.accessa.dto.token.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class TokenRequestDto {
    @NotEmpty(message = "Refresh Token cannot be empty")
    private String refreshToken;
}
