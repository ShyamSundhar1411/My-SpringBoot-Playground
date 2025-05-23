package com.axionlabs.accessa.repository;

import com.axionlabs.accessa.entity.Profile;
import com.axionlabs.accessa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Optional<Profile> findByUser(User user);
}
