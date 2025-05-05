package com.axionlabs.accessa.service.impl;

import com.axionlabs.accessa.dto.profile.ProfileDto;
import com.axionlabs.accessa.dto.user.UserDto;
import com.axionlabs.accessa.entity.Profile;
import com.axionlabs.accessa.entity.User;
import com.axionlabs.accessa.mapper.ProfileMapper;
import com.axionlabs.accessa.mapper.UserMapper;
import com.axionlabs.accessa.repository.UserRepository;
import com.axionlabs.accessa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IUserService implements UserService {
    private final UserRepository userRepository;
    private final IProfileService profileService;
    @Autowired
    public IUserService(UserRepository userRepository, IProfileService profileService){
        this.userRepository = userRepository;
        this.profileService = profileService;
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
        return userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User not found"
                )
        );
    }

    public UserDto fetchUserDetails(){
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
        Optional<Profile> profile = profileService.fetchProfileFromUser(user);
        return profile.map(value -> UserMapper.mapToUserDto(new UserDto(), user, ProfileMapper.mapToProfileDto(new ProfileDto(), value))).orElseGet(() -> UserMapper.mapToUserDto(new UserDto(), user));
    }
    public UserDetailsService userDetails(){
        return username -> userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}
