package com.axionlabs.arkive.service;

import com.axionlabs.arkive.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto fetchUserDetails();
    boolean deleteUser();

}
