package com.example.contactmanagement.security.service;

import com.example.contactmanagement.security.dto.RegistrationRequest;
import com.example.contactmanagement.security.model.Role;
import com.example.contactmanagement.security.model.User;
import com.example.contactmanagement.security.repository.UserRepository;
import com.example.contactmanagement.security.dto.RegistrationRequest;
import com.example.contactmanagement.security.dto.AuthenticationRequest;
import com.example.contactmanagement.security.dto.AuthenticationResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @Transactional
    public AuthenticationResponse register(RegistrationRequest request) {
        
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

//        Set<Role> roles = new HashSet<>();
//        roles.add(Role.ROLE_USER);

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .build();

        userRepository.save(user);

        return authenticationService.authenticate(
                new AuthenticationRequest(request.getUsername(), request.getPassword())
        );
    }
}