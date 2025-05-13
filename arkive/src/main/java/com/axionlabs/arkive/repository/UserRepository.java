package com.axionlabs.arkive.repository;

import com.axionlabs.arkive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository  extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String username);
}
