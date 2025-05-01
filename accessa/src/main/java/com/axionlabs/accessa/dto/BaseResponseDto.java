package com.axionlabs.accessa.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data @AllArgsConstructor
public class BaseResponseDto {
    private HttpStatusCode statusCode;
    private String statusMessage;
}
