package com.axionlabs.accessa.service.impl;

import com.axionlabs.accessa.dto.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;
import com.axionlabs.accessa.repository.UserRepository;
import com.axionlabs.accessa.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class IAuthService implements AuthService {
    private UserRepository userRepository;
    public TokenizedUserDto registerUser(RegisterRequestDto userData){
        return null;
    }
}
