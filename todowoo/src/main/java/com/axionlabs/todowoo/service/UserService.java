package com.axionlabs.todowoo.service;

import com.axionlabs.todowoo.dto.user.UserDto;
import com.axionlabs.todowoo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserDto fetchUserDetails();
    Optional<User> getAuthenticatedUser();
}
