package com.axionlabs.accessa.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@NoArgsConstructor
public class BaseResponseDto {
    private HttpStatusCode statusCode;
    private String statusMessage;
    public BaseResponseDto(HttpStatusCode statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
