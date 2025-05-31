package com.axionlabs.todowoo.mapper;

import com.axionlabs.todowoo.dto.user.UserDto;
import com.axionlabs.todowoo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserDto(UserDto userDto);
    UserDto toUserDto(User user);
}
