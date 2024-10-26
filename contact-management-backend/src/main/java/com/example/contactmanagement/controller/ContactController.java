package com.example.contactmanagement.controller;

import com.example.contactmanagement.dto.ContactDTO;
import com.example.contactmanagement.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
	
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/createContact")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO contactDTO) {
        return new ResponseEntity<>(contactService.createContact(contactDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getContact/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ContactDTO> getContact(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @GetMapping("/getAllContacts")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @PutMapping("/updateContact/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Long id, @Valid @RequestBody ContactDTO contactDTO) {
        return ResponseEntity.ok(contactService.updateContact(id, contactDTO));
    }

    
    @DeleteMapping("/deleteContact/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        
    	if(contactService.deleteContact(id)) {
    		return ResponseEntity.ok("Deleted Successfully!");
    	} else {
    		return ResponseEntity.ok("Contact Not Found!");
    	}
        
    }

    @GetMapping("/mergeContacts/{mergeBy}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> mergeDuplicateContacts(@PathVariable String mergeBy) {
        contactService.mergeDuplicateContacts(mergeBy);
        return ResponseEntity.ok("Merging");
    }
}
