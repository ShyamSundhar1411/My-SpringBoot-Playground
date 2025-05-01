package com.axionlabs.accessa.dto.user.response;

import com.axionlabs.accessa.dto.user.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserResponseDto extends BaseResponseDto {

    private TokenizedUserDto data;
    public UserResponseDto(HttpStatusCode statusCode, String statusMessage, TokenizedUserDto userData){
        super(statusCode,statusMessage);
        this.data = userData;
    }
}
