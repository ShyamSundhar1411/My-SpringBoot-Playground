package com.axionlabs.arkive.service.impl;


import com.axionlabs.arkive.dto.user.TokenizedUserDto;
import com.axionlabs.arkive.dto.user.request.LoginRequestDto;
import com.axionlabs.arkive.dto.user.request.RegisterRequestDto;
import com.axionlabs.arkive.entity.User;
import com.axionlabs.arkive.exception.UserAlreadyExistsException;
import com.axionlabs.arkive.mapper.UserMapper;
import com.axionlabs.arkive.repository.UserRepository;
import com.axionlabs.arkive.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IAuthService implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJWTService ijwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    @Autowired
    public IAuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, IJWTService ijwtService, AuthenticationManager authenticationManager,UserMapper userMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.ijwtService = ijwtService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }
    @Override
    public TokenizedUserDto registerUser(RegisterRequestDto userData) {
        if(checkUserExists(userData.getUserName(),userData.getEmail())){
            throw new UserAlreadyExistsException("A user with this username or email already exists.");

        }
        User user = userMapper.fromRegisterDto(userData, passwordEncoder);
        userRepository.save(user);
        var jwtToken = ijwtService.generateJwtToken(user);
        return userMapper.toUserDto(user,jwtToken);


    }

    @Override
    public TokenizedUserDto loginUser(LoginRequestDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUserName(),userDto.getPassword()
                )
        );
        User user = userRepository.findByUserName(
                userDto.getUserName()
        ).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        var jwtToken = ijwtService.generateJwtToken(user);
        return userMapper.toUserDto(user,jwtToken);
    }

    @Override
    public boolean checkUserExists(String userName, String email) {
        return userRepository.findByUserNameOrEmail(userName,email).isPresent();
    }
}
