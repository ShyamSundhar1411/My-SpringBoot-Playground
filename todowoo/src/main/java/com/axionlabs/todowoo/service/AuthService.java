package com.axionlabs.todowoo.service;


import com.axionlabs.todowoo.dto.user.TokenizedUserDto;
import com.axionlabs.todowoo.dto.user.request.LoginRequestDto;
import com.axionlabs.todowoo.dto.user.request.RegisterRequestDto;

public interface AuthService {


    TokenizedUserDto registerUser(RegisterRequestDto userData);

    TokenizedUserDto loginUser(LoginRequestDto userDto);

    boolean checkUserExists(String userName, String email);
}
