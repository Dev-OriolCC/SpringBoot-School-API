package com.example.demo.repository;

import com.example.demo.entities.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    // Define specific methods
    List<Contact> findByStatus(String status);
    
}
