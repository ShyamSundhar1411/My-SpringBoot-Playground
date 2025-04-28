package com.axionlabs.bookwiz.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data @AllArgsConstructor
public class BookResponseDto<T> {
    private HttpStatus statusCode;
    private String statusMessage;
    private T data;
}
