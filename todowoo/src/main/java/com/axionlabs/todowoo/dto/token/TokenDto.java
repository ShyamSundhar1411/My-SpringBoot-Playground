package com.axionlabs.todowoo.dto.token;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class TokenDto {
    String accessToken;
    String refreshToken;
}
