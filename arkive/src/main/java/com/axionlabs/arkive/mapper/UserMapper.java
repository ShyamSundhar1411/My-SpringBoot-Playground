package com.axionlabs.arkive.mapper;

import com.axionlabs.arkive.dto.user.UserDto;
import com.axionlabs.arkive.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUserEntity(UserDto userDto);
}
