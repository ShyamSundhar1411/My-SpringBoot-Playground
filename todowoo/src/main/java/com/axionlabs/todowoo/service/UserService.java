package com.axionlabs.todowoo.service;

import com.axionlabs.todowoo.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto fetchUserDetails();
}
