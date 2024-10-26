package com.example.contactmanagement.service;

import com.example.contactmanagement.dto.ContactDTO;
import java.util.List;

public interface ContactService {
    ContactDTO createContact(ContactDTO contactDTO);
    ContactDTO getContact(Long id);
    List<ContactDTO> getAllContacts();
    ContactDTO updateContact(Long id, ContactDTO contactDTO);
    boolean deleteContact(Long id);
    void mergeDuplicateContacts(String mergeBy);
}
