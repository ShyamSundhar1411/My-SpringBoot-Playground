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
    @Mapping(source = "tokenDto.accessToken", target = "accessToken")
    @Mapping(source = "tokenDto.refreshToken", target = "refreshToken")
    TokenizedUserDto toUserDto(User user, TokenDto tokenDto);

}
