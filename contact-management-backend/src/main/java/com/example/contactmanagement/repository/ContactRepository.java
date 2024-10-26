package com.example.contactmanagement.repository;

import com.example.contactmanagement.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
    boolean existsByEmailId(String emailId);
    boolean existsByMobile(String mobile);
    
    Optional<Contact> findByEmailId(String emailId);
    Optional<Contact> findByMobile(String mobile);
    
    @Query("SELECT c FROM Contact c WHERE c.emailId IN " +
           "(SELECT c2.emailId FROM Contact c2 GROUP BY c2.emailId HAVING COUNT(c2) > 1) " +
           "OR c.mobile IN (SELECT c2.mobile FROM Contact c2 GROUP BY c2.mobile HAVING COUNT(c2) > 1)")
    List<Contact> findDuplicateContacts();
}
