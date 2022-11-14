package com.example.demo.service;

import com.example.demo.dto.requestDto.ContactRequestDto;
import com.example.demo.entities.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    public List<Contact> displayContactMessages();
    public Contact addContactMessages(ContactRequestDto contactRequestDto);
    public Contact editContactMessage(Integer contactId);
    public Contact deleteContactMessage(Integer contactId);

}
