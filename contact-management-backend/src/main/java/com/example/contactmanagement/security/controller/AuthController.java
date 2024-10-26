package com.example.contactmanagement.security.controller;

import com.example.contactmanagement.security.dto.AuthenticationRequest;
import com.example.contactmanagement.security.dto.AuthenticationResponse;
import com.example.contactmanagement.security.dto.RegistrationRequest;
import com.example.contactmanagement.security.service.AuthenticationService;
import com.example.contactmanagement.security.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(registrationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
