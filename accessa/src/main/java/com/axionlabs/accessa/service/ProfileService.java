package com.axionlabs.accessa.service;

import com.axionlabs.accessa.entity.Profile;
import com.axionlabs.accessa.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ProfileService {
    Optional<Profile> fetchProfileFromUser(User user);
}
