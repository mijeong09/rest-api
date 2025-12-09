package com.study.restapi.repository;

import com.study.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> finaByUsername(String username);
    boolean existsByUsername(String username);
}
