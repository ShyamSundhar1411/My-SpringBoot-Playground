package com.axionlabs.todowoo.dto;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
public class BaseResponseDto {
    HttpStatusCode statusCode;
    String statusMessage;
}
