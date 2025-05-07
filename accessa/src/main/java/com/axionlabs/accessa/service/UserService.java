package com.axionlabs.accessa.service;

import com.axionlabs.accessa.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto fetchUserDetails();
    boolean deleteUser();
}
