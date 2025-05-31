package com.axionlabs.todowoo.dto.user.response;

import com.axionlabs.todowoo.dto.BaseResponseDto;
import com.axionlabs.todowoo.dto.user.TokenizedUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserResponseDto extends BaseResponseDto {
    private TokenizedUserDto data;

    public UserResponseDto(HttpStatusCode statusCode, String statusMessage, TokenizedUserDto userDto){
        super(statusCode,statusMessage);
        this.data  = userDto;
    }
}
