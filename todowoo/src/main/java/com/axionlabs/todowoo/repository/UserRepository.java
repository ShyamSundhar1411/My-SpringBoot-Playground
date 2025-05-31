package com.axionlabs.todowoo.repository;

import com.axionlabs.todowoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String username);
    Optional<User> findByUserNameOrEmail(String userName, String email);
}
