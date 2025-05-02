package com.axionlabs.accessa.service;

import com.axionlabs.accessa.dto.user.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.request.LoginRequestDto;
import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;

public interface AuthService {
    TokenizedUserDto registerUser(RegisterRequestDto userData);
    TokenizedUserDto loginUser(LoginRequestDto userData);
    boolean checkUserExists(String userName, String email);
}
