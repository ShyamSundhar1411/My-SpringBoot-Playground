package com.axionlabs.accessa.dto.user.response;

import com.axionlabs.accessa.dto.user.BaseResponseDto;
import com.axionlabs.accessa.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserProfileResponseDto extends BaseResponseDto {
    private UserDto data;
    public UserProfileResponseDto(HttpStatusCode statusCode, String statusMessage, UserDto userData){
        super(statusCode,statusMessage);
        this.data = userData;
    }
}
