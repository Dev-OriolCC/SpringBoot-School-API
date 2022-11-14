package com.example.demo.service;

import com.example.demo.dto.requestDto.ContactRequestDto;
import com.example.demo.entities.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> displayContactMessages() {
        List<Contact> contacts = (List<Contact>) contactRepository.findAll();
        return contacts;
    }

    @Override
    public Contact addContactMessages(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        contact.setMessage(contactRequestDto.getMessage());
        contact.setName(contactRequestDto.getName());
        contact.setEmail(contactRequestDto.getEmail());
        contact.setStatus(contactRequestDto.getStatus());
        contact.setMobileNum(contactRequestDto.getMobileNum());
        contact.setSubject(contactRequestDto.getSubject());
        contactRepository.save(contact);
        return contact;
    }

    @Override
    public Contact editContactMessage(Integer contactId) {
        Contact contact = contactRepository.findById(contactId).get();
        contact.setStatus(!contact.getStatus());
        contactRepository.save(contact);
        return contact;
    }

    @Override
    public Contact deleteContactMessage(Integer contactId) {
        Contact contact = contactRepository.findById(contactId).get();
        contactRepository.delete(contact);
        return contact;
    }


}
