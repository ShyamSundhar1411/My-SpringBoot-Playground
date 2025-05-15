package com.axionlabs.arkive.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@NoArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private HttpStatusCode errorCode;
    private String errorMessage;
    private String errorTime;
    public String toJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            return "{\"error\":\"Failed to serialize error response\"}";
        }
    }
}
