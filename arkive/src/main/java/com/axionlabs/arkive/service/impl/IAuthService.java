package com.axionlabs.arkive.service.impl;

import com.axionlabs.arkive.dto.user.TokenizedUserDto;
import com.axionlabs.arkive.dto.user.request.LoginRequestDto;
import com.axionlabs.arkive.dto.user.request.RegisterRequestDto;
import com.axionlabs.arkive.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class IAuthService implements AuthService {
    @Override
    public TokenizedUserDto registerUser(RegisterRequestDto userData) {
        return null;
    }

    @Override
    public TokenizedUserDto loginUser(LoginRequestDto userDto) {
        return null;
    }

    @Override
    public boolean checkUserExists(String userName, String email) {
        return false;
    }
}
