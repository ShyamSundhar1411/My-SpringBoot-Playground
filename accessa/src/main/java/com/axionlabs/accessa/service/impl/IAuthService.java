package com.axionlabs.accessa.service.impl;

import com.axionlabs.accessa.dto.profile.ProfileDto;
import com.axionlabs.accessa.dto.user.TokenizedUserDto;
import com.axionlabs.accessa.dto.user.request.LoginRequestDto;
import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;
import com.axionlabs.accessa.entity.Profile;
import com.axionlabs.accessa.entity.User;
import com.axionlabs.accessa.exception.UserAlreadyExistsException;
import com.axionlabs.accessa.mapper.ProfileMapper;
import com.axionlabs.accessa.mapper.UserMapper;
import com.axionlabs.accessa.repository.ProfileRepository;
import com.axionlabs.accessa.repository.UserRepository;
import com.axionlabs.accessa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IAuthService implements AuthService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJWTService ijwtService;
    private final AuthenticationManager authenticationManager;

    public IAuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            IJWTService ijwtService,
            AuthenticationManager authenticationManager,
            ProfileRepository profileRepository
            ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.ijwtService = ijwtService;
        this.authenticationManager = authenticationManager;
        this.profileRepository = profileRepository;
    }


    public TokenizedUserDto registerUser(RegisterRequestDto userData){
        if(checkUserExists(userData.getUserName(),userData.getEmail())){
            throw new UserAlreadyExistsException("A user with this username or email already exists.");
        }
        User user = UserMapper.mapToUser(userData, new User(), passwordEncoder);
        userRepository.save(user);


        var jwtToken = ijwtService.generateJwtToken(user);
        return UserMapper.mapToTokenizedUserDto(new TokenizedUserDto(), user, jwtToken);
    }

    @Override
    public TokenizedUserDto loginUser(LoginRequestDto userData) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken( userData.getUserName(),
                userData.getPassword()
            )
        );
        var user = userRepository.findByUserName(
                userData.getUserName()
        ).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        var jwtToken = ijwtService.generateJwtToken(user);
        return UserMapper.mapToTokenizedUserDto(new TokenizedUserDto(), user, jwtToken);
    }

    @Override
    public boolean checkUserExists(String userName, String email) {
        return userRepository.findByUserNameOrEmail(userName,email).isPresent();
    }
}
