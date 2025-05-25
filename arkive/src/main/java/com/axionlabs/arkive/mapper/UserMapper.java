package com.axionlabs.arkive.mapper;

import com.axionlabs.arkive.dto.token.TokenDto;
import com.axionlabs.arkive.dto.user.TokenizedUserDto;
import com.axionlabs.arkive.dto.user.UserDto;
import com.axionlabs.arkive.dto.user.request.RegisterRequestDto;
import com.axionlabs.arkive.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User fromUserDto(UserDto userDto);
    User fromRegisterDto(RegisterRequestDto registerRequestDto);

    default TokenizedUserDto toUserDto(User user, TokenDto tokenDto){
        TokenizedUserDto dto = new TokenizedUserDto();
        dto.setUserName(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUserId(user.getUserId());
        dto.setAccessToken(tokenDto.getAccessToken());
        dto.setRefreshToken(tokenDto.getRefreshToken());
        dto.setEmail(user.getEmail());
        return dto;
    }

}
