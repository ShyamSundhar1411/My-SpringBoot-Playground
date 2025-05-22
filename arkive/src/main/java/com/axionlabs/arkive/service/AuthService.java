package com.axionlabs.arkive.service;


import com.axionlabs.arkive.dto.user.TokenizedUserDto;
import com.axionlabs.arkive.dto.user.request.LoginRequestDto;
import com.axionlabs.arkive.dto.user.request.RegisterRequestDto;

public interface AuthService {
    TokenizedUserDto registerUser(RegisterRequestDto userData);
    TokenizedUserDto loginUser(LoginRequestDto userDto);

    TokenizedUserDto registerUser(RegisterRequestDto userData);

    TokenizedUserDto loginUser(LoginRequestDto userDto);

    boolean checkUserExists(String userName, String email);
}
