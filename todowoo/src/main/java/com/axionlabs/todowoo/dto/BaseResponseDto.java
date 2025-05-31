package com.axionlabs.todowoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BaseResponseDto {
    HttpStatusCode statusCode;
    String statusMessage;
}
