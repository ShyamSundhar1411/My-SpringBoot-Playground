package com.axionlabs.accessa.service.impl;

import com.axionlabs.accessa.dto.user.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;
import com.axionlabs.accessa.entity.User;
import com.axionlabs.accessa.mapper.UserMapper;
import com.axionlabs.accessa.repository.UserRepository;
import com.axionlabs.accessa.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IAuthService implements AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private IJWTService ijwtService;
    public TokenizedUserDto registerUser(RegisterRequestDto userData){
        User user = UserMapper.mapToUser(userData, new User(), passwordEncoder);
        userRepository.save(user);
        var jwtToken = ijwtService.generateJwtToken(user);
        return UserMapper.mapToTokenizedUserDto(new TokenizedUserDto(), user, jwtToken);
    }
}
