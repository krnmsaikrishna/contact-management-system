package com.example.contactmanagement.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contactmanagement.security.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}