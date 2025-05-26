package com.axionlabs.arkive.service.impl;

import com.axionlabs.arkive.dto.user.UserDto;
import com.axionlabs.arkive.entity.User;
import com.axionlabs.arkive.mapper.UserMapper;
import com.axionlabs.arkive.repository.UserRepository;
import com.axionlabs.arkive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class IUserService implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public IUserService(UserRepository userRepository,UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public UserDto fetchUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            throw new UsernameNotFoundException("User not authenticated");
        }
        String username = authentication.getName();
        User user =  userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User not found"
                )
        );
        return userMapper.toUserDto(user);
    }

    @Override
    public boolean deleteUser() {
        return false;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(
                username
        ).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}
