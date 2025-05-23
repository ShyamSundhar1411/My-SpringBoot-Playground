package com.axionlabs.accessa.mapper;

import com.axionlabs.accessa.dto.profile.ProfileDto;
import com.axionlabs.accessa.dto.profile.request.ProfileUpdateRequestDto;
import com.axionlabs.accessa.dto.token.TokenDto;
import com.axionlabs.accessa.dto.user.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.UserDto;
import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;
import com.axionlabs.accessa.entity.Role;
import com.axionlabs.accessa.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public static TokenizedUserDto mapToTokenizedUserDto(
            TokenizedUserDto userDto, User user, TokenDto tokens, ProfileDto profileData
    ){
        userDto.setUserName(user.getUsername());
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserProfile(profileData);
        userDto.setAccessToken(tokens.getAccessToken());
        userDto.setRefreshToken(tokens.getRefreshToken());
        return userDto;
    }
    public static UserDto mapToUserDto(
            UserDto userDto, User user, ProfileDto profileDto
    ){
        userDto.setUserName(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setUserProfile(profileDto);
        return userDto;
    }
    public static void mapToUser(
            ProfileUpdateRequestDto profileData, User user
    ){
        user.setEmail(profileData.getEmail());
        user.setFirstName(profileData.getFirstName());
        user.setLastName(profileData.getLastName());
    }
}
