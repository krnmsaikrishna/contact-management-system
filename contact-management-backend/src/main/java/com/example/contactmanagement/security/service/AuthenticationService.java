package com.example.contactmanagement.security.service;

import com.example.contactmanagement.security.config.JwtService;
import com.example.contactmanagement.security.dto.AuthenticationRequest;
import com.example.contactmanagement.security.dto.AuthenticationResponse;
import com.example.contactmanagement.security.repository.UserRepository;
import com.example.contactmanagement.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // First authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        
        // Find the user in the database
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // Convert User to UserDetails
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList()))
                .build();
        
        // Generate JWT token
        String jwtToken = jwtService.generateToken(userDetails);
        
        // Return the response
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}


//    private UserDetails convertToUserDetails(User user) {
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .authorities(user.getRoles().stream()
//                        .map(role -> new SimpleGrantedAuthority(role.name()))
//                        .collect(Collectors.toList()))
//                .build();
//    }

