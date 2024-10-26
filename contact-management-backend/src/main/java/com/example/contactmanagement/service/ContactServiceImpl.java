package com.example.contactmanagement.service;

import com.example.contactmanagement.dto.ContactDTO;
import com.example.contactmanagement.exception.ContactNotFoundException;
import com.example.contactmanagement.exception.DuplicateResourceException;
import com.example.contactmanagement.model.Contact;
import com.example.contactmanagement.repository.ContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactDTO createContact(ContactDTO contactDTO) {
    	
//        if (contactRepository.existsByEmailId(contactDTO.getEmailId())) {
//            throw new DuplicateResourceException("Email already exists");
//        }
        
        if (contactRepository.existsByMobile(contactDTO.getMobile())) {
            throw new DuplicateResourceException("Mobile number already exists");
        }

        Contact contact = new Contact();
        BeanUtils.copyProperties(contactDTO, contact);
        Contact savedContact = contactRepository.save(contact);
        BeanUtils.copyProperties(savedContact, contactDTO);
        return contactDTO;
    }

    @Override
    public ContactDTO getContact(Long id) {
        Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new ContactNotFoundException("Contact not found"));
        ContactDTO contactDTO = new ContactDTO();
        BeanUtils.copyProperties(contact, contactDTO);
        return contactDTO;
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream()
            .map(contact -> {
                ContactDTO dto = new ContactDTO();
                BeanUtils.copyProperties(contact, dto);
                return dto;
            })
            .collect(Collectors.toList());
    }

    @Override
    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new ContactNotFoundException("Contact not found"));

        if (!contact.getEmailId().equals(contactDTO.getEmailId()) &&
            contactRepository.existsByEmailId(contactDTO.getEmailId())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (!contact.getMobile().equals(contactDTO.getMobile()) &&
            contactRepository.existsByMobile(contactDTO.getMobile())) {
            throw new DuplicateResourceException("Mobile number already exists");
        }

        BeanUtils.copyProperties(contactDTO, contact, "id");
        Contact updatedContact = contactRepository.save(contact);
        BeanUtils.copyProperties(updatedContact, contactDTO);
        return contactDTO;
    }

    @Override
    public boolean deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            //throw new ContactNotFoundException("Contact not found");
            return false;
        }
        contactRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public void mergeDuplicateContacts(String mergeBy) {
    		
        List<Contact> duplicates = contactRepository.findDuplicateContacts();
        
        if(mergeBy.equals("email")) {
   
        duplicates.stream()
            .collect(Collectors.groupingBy(Contact::getEmailId))
            .forEach((email, contacts) -> {
                if (contacts.size() > 1) {
                    Contact primaryContact = contacts.get(0);
                    contacts.stream()
                        .skip(1)
                        .forEach(contact -> contactRepository.deleteById(contact.getId()));
                }
            });
        
       }

        if(mergeBy.equals("mobile")) {
        	
        duplicates.stream()
            .collect(Collectors.groupingBy(Contact::getMobile))
            .forEach((mobile, contacts) -> {
                if (contacts.size() > 1) {
                    Contact primaryContact = contacts.get(0);
                    contacts.stream()
                        .skip(1)
                        .forEach(contact -> contactRepository.deleteById(contact.getId()));
                }
            });
        }
    }
}
