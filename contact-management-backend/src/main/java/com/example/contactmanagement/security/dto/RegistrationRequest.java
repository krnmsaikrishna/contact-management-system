package com.example.contactmanagement.security.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.contactmanagement.security.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();
}