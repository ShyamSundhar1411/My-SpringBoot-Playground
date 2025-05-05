package com.axionlabs.accessa.service.impl;

import com.axionlabs.accessa.entity.Profile;
import com.axionlabs.accessa.entity.User;
import com.axionlabs.accessa.exception.ResourceNotFoundException;
import com.axionlabs.accessa.repository.ProfileRepository;
import com.axionlabs.accessa.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Service
public class IProfileService implements ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public IProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }
    @Override
    public Optional<Profile> fetchProfileFromUser(User user) {
        return profileRepository.findByUser(user);
    }

}
