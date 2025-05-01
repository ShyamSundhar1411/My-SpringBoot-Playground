package com.axionlabs.accessa.service;

import com.axionlabs.accessa.dto.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;

public interface AuthService {
    TokenizedUserDto registerUser(RegisterRequestDto userData);

}
