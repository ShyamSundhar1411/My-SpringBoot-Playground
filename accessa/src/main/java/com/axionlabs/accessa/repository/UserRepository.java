package com.axionlabs.accessa.repository;

import com.axionlabs.accessa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String username);
    @Query("SELECT u from User u where userName = :username OR email = :email")
    Optional<User> findByUserNameOrEmail(String username, String email);

}
