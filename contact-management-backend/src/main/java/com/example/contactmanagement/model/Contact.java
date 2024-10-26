package com.example.contactmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabets")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only alphabets")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address format")
    @Column(unique = true, nullable = false)
    private String emailId;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    @Column(unique = true, nullable = false)
    private String mobile;

    @Pattern(regexp = "^[a-zA-Z0-9#.,\\-\\s]{5,100}$", message = "Invalid Address")
    private String address;
}
