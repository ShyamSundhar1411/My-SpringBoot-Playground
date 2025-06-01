package com.axionlabs.todowoo.mapper;

import com.axionlabs.todowoo.dto.token.TokenDto;
import com.axionlabs.todowoo.dto.user.TokenizedUserDto;
import com.axionlabs.todowoo.dto.user.UserDto;
import com.axionlabs.todowoo.dto.user.request.RegisterRequestDto;
import com.axionlabs.todowoo.entity.Role;
import com.axionlabs.todowoo.entity.User;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserDto(UserDto userDto);
    UserDto toUserDto(User user);
    default User fromRegisterDto(RegisterRequestDto registerRequestDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUserName(registerRequestDto.getUserName());
        user.setEmail(registerRequestDto.getEmail());
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRole(Role.USER);

        return user;
    }
    default TokenizedUserDto toUserDto(User user, TokenDto tokenDto){
        TokenizedUserDto dto = new TokenizedUserDto();
        dto.setUserName(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUserId(user.getUserId());
        dto.setTokens(tokenDto);
        dto.setEmail(user.getEmail());
        return dto;
    }
}
