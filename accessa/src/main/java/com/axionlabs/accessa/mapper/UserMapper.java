package com.axionlabs.accessa.mapper;

import com.axionlabs.accessa.dto.token.TokenDto;
import com.axionlabs.accessa.dto.user.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.UserDto;
import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;
import com.axionlabs.accessa.entity.Role;
import com.axionlabs.accessa.entity.User;
import io.jsonwebtoken.security.Password;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

public class UserMapper {
    public static UserDto mapToUserDto(
            UserDto userDto, User user
    ){
        userDto.setUserName(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public static User mapToUser(
            UserDto userDto, User user
    ){
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;

    }

    public static User mapToUser(
            RegisterRequestDto userDto, User user, PasswordEncoder passwordEncoder
    ){

        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        return user;
    }
    public static TokenizedUserDto mapToTokenizedUserDto(
            TokenizedUserDto userDto, User user, TokenDto tokens
    ){
        userDto.setUserName(user.getUsername());
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAccessToken(tokens.getAccessToken());
        userDto.setRefreshToken(tokens.getRefreshToken());
        return userDto;
    }
}
